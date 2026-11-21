FROM eclipse-temurin:25-jdk-jammy AS build
WORKDIR /app
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
COPY src src
RUN ./mvnw --batch-mode package

FROM eclipse-temurin:25-jre-jammy
WORKDIR /app
COPY --from=build /app/target/securedocs-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=70.0"
USER 10001
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "app.jar"]
