FROM maven:4.0.0-openjdk-21 as build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:21-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]