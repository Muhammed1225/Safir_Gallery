FROM openjdk:17-jdk-slim
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw && ./mvnw clean package

COPY src ./src
RUN ./mvnw package -DskipTests

ENTRYPOINT ["java", "-jar", "/app/target/safir.jar"]