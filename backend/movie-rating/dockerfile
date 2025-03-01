# Use the official OpenJDK 21 image as the base image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the Gradle build output
COPY build/libs/movie-rating-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
