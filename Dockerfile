FROM maven:3.8.1-openjdk-11-slim AS build
COPY . /app
WORKDIR /app
RUN mvn clean install -DskipTests


FROM openjdk:11-jre-slim
COPY --from=build /app/target/DiscountApplication.jar /app.jar
ENV SPRING_PROFILES_ACTIVE=docker
CMD  ["java", "-jar", "/app.jar"]
