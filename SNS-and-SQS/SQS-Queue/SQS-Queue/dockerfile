# ---- Stage 1: Build ----
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy Maven files first to leverage Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Package application without tests (optional)
RUN mvn clean package -DskipTests

# ---- Stage 2: Runtime ----
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy the packaged JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
