#FROM openjdk:11
FROM openjdk:11.0.12-jre
WORKDIR /vol
WORKDIR /app

COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]