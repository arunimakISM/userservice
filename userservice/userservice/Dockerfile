# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set a working directory inside container
WORKDIR /app

# Copy the built jar file
COPY target/userservice-0.0.1-SNAPSHOT.jar app.jar

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=prod

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
