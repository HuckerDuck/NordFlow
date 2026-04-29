# Build it

FROM gradle:8.13-jdk21 AS builder

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle
RUN gradle dependencies --no-daemon || true

COPY src ./src
RUN gradle bootJar --no-daemon -x test

# Run it
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S nordflow && adduser -S nordflow -G nordflow
USER nordflow

COPY --from=builder /app/build/libs/auth-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]