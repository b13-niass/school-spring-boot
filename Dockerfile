# Use the official OpenJDK image as a base
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/school-spring-boot.jar app.jar

# Expose the application port
EXPOSE 8080:80

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
