FROM openjdk:17-alpine
COPY target/homework-processor-1.0-SNAPSHOT.war /app/app.war
ENTRYPOINT ["java", "-jar", "/app/app.war"]
