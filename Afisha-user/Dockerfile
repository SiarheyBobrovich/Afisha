FROM openjdk:11-jdk-slim
MAINTAINER ITAacademy

COPY target/Afisha-user-1.0.0-SNAPSHOT.jar Afisha-user-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=production", "/Afisha-user-1.0.0-SNAPSHOT.jar"]