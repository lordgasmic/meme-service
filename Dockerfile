FROM lordgasmic/jre14
WORKDIR /vol
WORKDIR /app

COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
