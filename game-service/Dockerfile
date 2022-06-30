FROM openjdk:17-alpine
EXPOSE 8119
RUN mkdir -p /app/
ADD build/libs/game-service-0.0.1-SNAPSHOT.jar /app/game-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/game-service-0.0.1-SNAPSHOT.jar"]