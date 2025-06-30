# Stage 1: Build dengan Maven dan JDK 21
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime hanya dengan JRE 21 lebih ringan
FROM eclipse-temurin:21-jre
WORKDIR /
COPY --from=build /target/project250630-0.0.1-SNAPSHOT.jar project250630-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "project250630-0.0.1-SNAPSHOT.jar"]