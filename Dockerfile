<<<<<<< HEAD
<<<<<<< HEAD
FROM openjdk:21-ea-30
=======
=======
>>>>>>> parent of 576c20e (deployment again)
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
>>>>>>> parent of 576c20e (deployment again)
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080