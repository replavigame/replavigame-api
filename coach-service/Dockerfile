FROM openjdk:17-alpine
EXPOSE 8092
RUN mkdir -p /app/
ADD build/libs/coach-service-0.0.1-SNAPSHOT.jar /app/coach-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/coach-service-0.0.1-SNAPSHOT.jar"]