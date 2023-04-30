# syntax=docker/dockerfile:1

FROM node:16-alpine AS build
WORKDIR /app
COPY ./ZPI-AMS-UI .
RUN npm ci
RUN npm run build

FROM gradle:7.2.0-jdk11-alpine AS jar
COPY . /app
WORKDIR /app
RUN mkdir -p src/main/resources/static
RUN rm -rf src/main/resources/static
COPY --from=build /app/build/ src/main/resources/static/
RUN gradle bootJar --no-daemon --parallel

FROM openjdk:11-jre-slim
EXPOSE 8080
COPY --from=jar /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]