FROM openjdk:21-jdk-slim
WORKDIR /app

EXPOSE 8080
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

RUN chmod +x mvnw && ./mvnw clean package -DskipTests  # Tek bir satırda yap!

ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app/target/safir.jar"]