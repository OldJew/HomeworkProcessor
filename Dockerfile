FROM maven:3.8.3-openjdk-17
COPY target/homework-processor-1.0-SNAPSHOT.war /app/app.war
ENTRYPOINT ["java", "-jar", "/app/app.war"]
