FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY ./src /src
COPY pom.xml .
RUN mvn -f ./pom.xml clean package

# Run
FROM openjdk:17
COPY ./target/Backend-Spring_Boot-0.0.1-SNAPSHOT.jar ./target/Backend-Spring_Boot-0.0.1-SNAPSHOT.jar

EXPOSE 8080
USER root
CMD ["java", "-jar", "./target/Backend-Spring_Boot-0.0.1-SNAPSHOT.jar"]