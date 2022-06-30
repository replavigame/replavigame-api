FROM openjdk:17-alpine
EXPOSE 8099
RUN mkdir -p /app/
ADD build/libs/registry-service-0.0.1-SNAPSHOT.jar /app/registry-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/registry-service-0.0.1-SNAPSHOT.jar"]