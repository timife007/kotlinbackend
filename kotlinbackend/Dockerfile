# Stage 1: Build the application
FROM openjdk:17-jdk-alpine as buildup
WORKDIR /app
COPY src ./src
COPY gradle ./gradle
COPY build.gradle.kts .
COPY .gradle ./.gradle
COPY gradlew .
COPY gradlew.bat .
COPY settings.gradle.kts .
#ENV  POSTGRES_DB=kotlinbackend \
#     POSTGRES_PASSWORD=secret \
#     POSTGRES_USER=timife
RUN ./gradlew bootJar

# Stage 2: Run the application
FROM openjdk:17-jdk-alpine
WORKDIR /app
#COPY src/main/kotlin/com/timife/kotlinbackend/KotlinbackendApplication.kt .
COPY --from=buildup app/build/libs/kotlinbackend-0.0.1-SNAPSHOT.jar ./kotlin-backend.jar
EXPOSE 8081
CMD ["java", "-jar", "kotlin-backend.jar"]
