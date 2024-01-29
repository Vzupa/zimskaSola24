# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 as build

# Copy the project files to the container
COPY . /usr/src/app

# Set the working directory
WORKDIR /usr/src/app

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17

# Copy the built application from the build stage
COPY --from=build /usr/src/app/target/*-runner.jar /usr/app/app.jar

# Set the working directory
WORKDIR /usr/app

# Command to run the application
CMD ["java", "-jar", "app.jar"]