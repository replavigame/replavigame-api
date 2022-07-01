FROM openjdk:17-alpine
EXPOSE 8096
RUN mkdir -p /app/
ADD build/libs/order-game-coin-service-0.0.1-SNAPSHOT.jar /app/order-game-coin-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/order-game-coin-service-0.0.1-SNAPSHOT.jar"]