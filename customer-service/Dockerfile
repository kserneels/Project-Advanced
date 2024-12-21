FROM openjdk:17-jdk-alpine

# Expose the port your application will run on
EXPOSE 8082

# Add the JAR file to the image
ADD target/*.jar app.jar

# Set the environment variable for MySQL (if needed)
ENV MYSQL_DB_HOST=mysql-customer
ENV MYSQL_DB_PORT=3306

# Directly run the app when the container starts
ENTRYPOINT ["java", "-jar", "/app.jar"]
