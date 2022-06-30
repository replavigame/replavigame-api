FROM openjdk:17-alpine
EXPOSE 8101
RUN mkdir -p /app/
ADD build/libs/coach-report-service-0.0.1-SNAPSHOT.jar /app/coach-report-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/coach-report-service-0.0.1-SNAPSHOT.jar"]