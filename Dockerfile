#No tenia el Docler instalado en mi ordenador personal, por lo que no pude probarlo, pero creo que esta bien el Dockerfile para la creacion de la imagen.

# Use a base image with Java pre-installed
FROM amazoncorretto:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from your local machine to the container
COPY target/sonicat.jar app.jar

# Expose the port that your application is running on
EXPOSE 8080

# Set the command to run when the container starts
CMD ["java", "-jar", "app.jar"]