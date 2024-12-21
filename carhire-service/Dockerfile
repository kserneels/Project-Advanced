FROM openjdk:17-jdk-alpine

# Expose the port your application will run on
EXPOSE 8081

# Copy the wait-for-it.sh script from the project root to the Docker image
COPY ../../wait-for-it.sh /wait-for-it.sh

# Make the script executable
RUN chmod +x /wait-for-it.sh

# Add the JAR file to the image
ADD target/*.jar app.jar

# Use the wait-for-it.sh script to wait for MySQL to be ready, then start the app
ENTRYPOINT ["/wait-for-it.sh", "mysql-carhire:3306", "--", "java", "-jar", "/app.jar"]
