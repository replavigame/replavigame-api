FROM openjdk:17-alpine
EXPOSE 8086
RUN mkdir -p /app/
ADD build/libs/admin-service-0.0.1-SNAPSHOT.jar /app/admin-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/admin-service-0.0.1-SNAPSHOT.jar"]