FROM greyfoxit/alpine-openjdk8
MAINTAINER KEFI Mohamed <kefimo@gmail.com>
ADD target/registry-service.jar registry-service.jar
ENTRYPOINT ["java", "-jar", "/registry-service.jar"]
EXPOSE 9000