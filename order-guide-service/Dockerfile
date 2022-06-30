FROM openjdk:17-alpine
EXPOSE 8097
RUN mkdir -p /app/
ADD build/libs/order-guide-service-0.0.1-SNAPSHOT.jar /app/order-guide-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/order-guide-service-0.0.1-SNAPSHOT.jar"]