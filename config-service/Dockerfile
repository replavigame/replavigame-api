FROM openjdk:17-alpine
EXPOSE 8081
RUN mkdir -p /config-microservice/
ADD build/libs/config-service-0.0.1-SNAPSHOT.jar /config-microservice/config-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/config-microservice/config-service-0.0.1-SNAPSHOT.jar"]