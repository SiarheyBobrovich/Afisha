FROM openjdk:11-jdk-slim
MAINTAINER ITAacademy

COPY target/Afisha-classifiers-1.0.0-SNAPSHOT.jar Afisha-classifiers-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=production","/Afisha-classifiers-1.0.0-SNAPSHOT.jar"]