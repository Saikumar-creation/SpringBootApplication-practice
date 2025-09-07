# === Build stage ===
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copy Maven descriptor and download dependencies
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Copy source and build WAR
COPY src ./src
RUN mvn -q -DskipTests clean package

# === Runtime stage ===
FROM eclipse-temurin:17-jre
WORKDIR /app

# Non-root user
RUN useradd -r -s /bin/false spring && mkdir -p /app && chown -R spring:spring /app

# Copy WAR file instead of JAR
COPY --from=build /workspace/target/*.war /app/app.war

EXPOSE 8080

USER spring

ENTRYPOINT ["java","-XX:+UseContainerSupport","-XX:MaxRAMPercentage=75.0","-jar","/app/app.war"]
