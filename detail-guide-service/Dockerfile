FROM openjdk:17-alpine
EXPOSE 8093
RUN mkdir -p /app/
ADD build/libs/detail-guide-service-0.0.1-SNAPSHOT.jar /app/detail-guide-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/detail-guide-service-0.0.1-SNAPSHOT.jar"]