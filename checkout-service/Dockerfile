FROM openjdk:17-alpine
EXPOSE 8111
RUN mkdir -p /app/
ADD build/libs/checkout-service-0.0.1-SNAPSHOT.jar /app/checkout-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/checkout-service-0.0.1-SNAPSHOT.jar"]