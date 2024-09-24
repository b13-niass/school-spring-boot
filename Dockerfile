# Builder stage
FROM bellsoft/liberica-openjre-debian:21-cds AS builder
WORKDIR /builder
# Copy the JAR file to the working directory
COPY ./school-spring-boot-0.0.1-SNAPSHOT.jar application.jar
# Extract layers for optimization
RUN java -Djarmode=layertools -jar application.jar extract

# Runtime stage
FROM bellsoft/liberica-openjre-debian:21-cds
WORKDIR /application
# Copy the layers from the builder stage
COPY --from=builder /builder/dependencies/ ./
COPY --from=builder /builder/spring-boot-loader/ ./
COPY --from=builder /builder/snapshot-dependencies/ ./
COPY --from=builder /builder/application/ ./
# Run the Spring Boot app

EXPOSE 8140:8084

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
