FROM openjdk:17-alpine
EXPOSE 8098
RUN mkdir -p /app/
ADD build/libs/user-service-0.0.1-SNAPSHOT.jar /app/user-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/user-service-0.0.1-SNAPSHOT.jar"]