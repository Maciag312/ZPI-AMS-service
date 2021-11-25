# syntax=docker/dockerfile:1
FROM alpine/git AS git
WORKDIR /app
ADD "https://api.github.com/repos/Maciag312/ZPI-AMS-UI/commits?per_page=1" latest_commit
RUN git clone --depth 1 https://github.com/Maciag312/ZPI-AMS-UI ui

FROM node:16-alpine AS build
COPY --from=git /app/ui/ /app
WORKDIR /app
RUN npm ci
RUN npm run build

FROM gradle:7.2.0-jdk11-alpine AS jar
COPY . /app
WORKDIR /app
RUN mkdir -p src/main/resources/static
RUN rm -rf src/main/resources/static
COPY --from=build /app/build/ src/main/resources/static/
RUN gradle bootJar

FROM openjdk:11-jre-slim
EXPOSE 8080
COPY --from=jar /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]