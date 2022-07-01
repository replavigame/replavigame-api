FROM openjdk:17-alpine
EXPOSE 8112
RUN mkdir -p /app/
ADD build/libs/capture-service-0.0.1-SNAPSHOT.jar /app/capture-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/capture-service-0.0.1-SNAPSHOT.jar"]