FROM adoptopenjdk:8-jre-hotspot-bionic
COPY build/libs/Example-1.0-SNAPSHOT.jar /app/example.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/example.jar"]