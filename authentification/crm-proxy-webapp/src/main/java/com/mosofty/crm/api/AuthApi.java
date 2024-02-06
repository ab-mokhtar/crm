package com.mosofty.crm.api;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;


import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mosofty.crm.dto.AuthRequest;
import com.mosofty.crm.dto.CreateUserRequest;
import com.mosofty.crm.dto.UserView;
import com.mosofty.crm.mapper.UserViewMapper;
import com.mosofty.crm.model.User;
import com.mosofty.crm.service.UserService;
import org.springframework.web.client.RestTemplate;


@Tag(name = "Authentication")
@RestController
@RequestMapping(path = "api/public")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthApi {

  private final AuthenticationManager authenticationManager;
  private final JwtEncoder jwtEncoder;
  private final UserViewMapper userViewMapper;
  private final UserService userService;
  private final RestTemplate restTemplate;
  @PostMapping("login")
  @CrossOrigin(origins = "*")
  public ResponseEntity<UserView> login(@RequestBody @Valid AuthRequest request) {
    try {
      var authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(request.username(), request.password()));

      var user = (User) authentication.getPrincipal();

      var now = Instant.now();
      var expiry = 36000L;

      var scope =
          authentication.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(joining(" "));

      var claims =
          JwtClaimsSet.builder()
              .issuer("com.mosofty")
              .issuedAt(now)
              .expiresAt(now.plusSeconds(expiry))
              .subject(format("%s,%s", user.getId(), user.getUsername()))
              .claim("roles", scope)
              .build();

      var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
      addUserConnection(user);
      return ResponseEntity.ok()
          .header(HttpHeaders.AUTHORIZATION, token)
          .body(userViewMapper.toUserView(user,token));
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      
     // String loginPageUrl = "/login";
   //   return ResponseEntity.status(HttpStatus.SEE_OTHER)
          //    .header(HttpHeaders.LOCATION, loginPageUrl)
            //  .build();
    }
  }

  @PostMapping("register")
  @CrossOrigin
  public UserView register(@RequestBody @Valid CreateUserRequest request) {
    return userService.create(request);
  }
  public void addUserConnection(User user) {
    String endpoint = "http://localhost:9097/Connected/add";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

    ResponseEntity<String> responseEntity = restTemplate.postForEntity(endpoint, requestEntity, String.class);

    String result = responseEntity.getBody();
    System.err.println(result);
  }
}
