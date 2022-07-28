FROM openjdk:11-jdk-slim
MAINTAINER ITAacademy

COPY target/Afisha-events-1.0.0-SNAPSHOT.jar Afisha-events-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=production","/Afisha-events-1.0.0-SNAPSHOT.jar"]
