FROM eclipse-temurin:21-jre-alpine
ARG JAR_FILE=TaskManager-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY target/${JAR_FILE} /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]