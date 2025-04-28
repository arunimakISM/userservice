#!/bin/bash

# build.sh
# A Smart Build and Run Script for user-service

set -e  # Exit immediately if a command exits with a non-zero status

APP_NAME="user-service"
JAR_NAME="$APP_NAME-0.0.1-SNAPSHOT.jar"
DOCKER_IMAGE_NAME="user-service:latest"

echo "🚀 Starting build process for $APP_NAME..."

# Step 1: Build the JAR if not present
if [ ! -f "target/$JAR_NAME" ]; then
  echo "📦 JAR not found. Building project with Maven..."
  ./mvnw clean package -DskipTests
else
  echo "✅ JAR already exists. Skipping Maven build."
fi

# Step 2: Build Docker Image
echo "🐳 Building Docker image..."
docker build -t $DOCKER_IMAGE_NAME .

# Step 3: Run Docker Compose
echo "🛠️ Starting services using docker-compose..."
docker-compose down  # Stop any running services cleanly
docker-compose up --build

echo "🎉 Build and run complete!"
