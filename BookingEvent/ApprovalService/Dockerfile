# Step 1: Use an official OpenJDK image as the base image
FROM openjdk:21-jdk-slim

# Step 2: Set a working directory inside the container
WORKDIR /app

# Step 3: Add a volume pointing to /tmp (optional, but can help with caching)
VOLUME /tmp

# Step 4: Expose the port that the Spring Boot application will run on
EXPOSE 8089

# Step 5: Specify the path of the JAR file created by Gradle
ARG JAR_FILE=build/libs/*.jar

# Step 6: Copy the JAR file into the container at the specified location
COPY ${JAR_FILE} app.jar

# Step 7: Run the application using the Java command
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
