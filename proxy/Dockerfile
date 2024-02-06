FROM greyfoxit/alpine-openjdk8
MAINTAINER KEFI Mohamed <kefimo@gmail.com>
ADD target/proxy-service.jar proxy-service.jar
ENTRYPOINT ["java", "-jar", "/proxy-service.jar"]
EXPOSE 9001