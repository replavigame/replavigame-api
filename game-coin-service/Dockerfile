FROM openjdk:17-alpine
EXPOSE 8094
RUN mkdir -p /app/
ADD build/libs/game-coin-service-0.0.1-SNAPSHOT.jar /app/game-coin-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/game-coin-service-0.0.1-SNAPSHOT.jar"]