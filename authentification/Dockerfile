FROM openjdk:18-jdk-alpine3.14

ADD crm-proxy-webapp/target/spring-boot-docker.jar spring-boot-docker.jar
ARG -Dspring.profiles.active=persistence
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=persistence","/spring-boot-docker.jar"]

VOLUME  logs

EXPOSE 9012