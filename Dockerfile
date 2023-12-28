FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY src /app/src
COPY . /app/
COPY pom.xml /app
RUN chmod +x mvnw
RUN ./mvnw clean package

ENTRYPOINT ["java", "-jar", "target/your-application.jar"]
