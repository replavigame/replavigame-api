FROM openjdk:17-alpine
EXPOSE 8099
RUN mkdir -p /registry-microservice/
ADD build/libs/registry-service-0.0.1-SNAPSHOT.jar /registry-microservice/registry-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/registry-microservice/registry-service-0.0.1-SNAPSHOT.jar"]