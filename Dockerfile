# Builder stage
FROM maven:3.8.8-sapmachine-21 AS builder
WORKDIR /builder

COPY settings.xml /root/.m2/settings.xml
# Copy the Maven project files to the container
COPY pom.xml .
COPY src ./src

# Build the application and skip tests
RUN mvn clean package -DskipTests

# Extract layers for optimization using the built JAR file
RUN #java -Djarmode=layertools -jar target/*.jar extract

# Runtime stage
FROM bellsoft/liberica-openjre-debian:21-cds
WORKDIR /application

# Copy the layers from the builder stage
#COPY --from=builder /builder/dependencies/ ./
#COPY --from=builder /builder/spring-boot-loader/ ./
#COPY --from=builder /builder/snapshot-dependencies/ ./
#COPY --from=builder /builder/application/ ./
COPY --from=builder /builder/target/school-spring-boot-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the application port (map it to host port 8140)
EXPOSE 8140:8084

# Run the Spring Boot application
#ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
ENTRYPOINT ["java", "-jar", "app.jar"]
