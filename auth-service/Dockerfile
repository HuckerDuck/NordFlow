# Build it
# uses the gradle wrapper so docker gets the same gradle version as the project

FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

COPY gradlew build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon > /dev/null

COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test

# Run it
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S nordflow && adduser -S nordflow -G nordflow
USER nordflow

COPY --from=builder /app/build/libs/auth-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
