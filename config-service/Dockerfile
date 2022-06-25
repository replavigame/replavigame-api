FROM openjdk:17-alpine
EXPOSE 8081
RUN mkdir -p /app/
ADD build/libs/config-service-0.0.1-SNAPSHOT.jar /app/config-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/config-service-0.0.1-SNAPSHOT.jar"]