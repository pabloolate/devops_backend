# Etapa 1: build con Maven y Java 21
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests clean package && cp target/*.jar /app/app.jar

# Etapa 2: runtime liviano con Java 21
FROM eclipse-temurin:21-jre-alpine AS runtime

RUN addgroup -S ep2 && adduser -S ep2 -G ep2
WORKDIR /app

COPY --from=build /app/app.jar /app/app.jar
RUN chown -R ep2:ep2 /app

USER ep2
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
