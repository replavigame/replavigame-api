FROM openjdk:17-alpine
EXPOSE 8102
RUN mkdir -p /app/
ADD build/libs/coaching-session-service-0.0.1-SNAPSHOT.jar /app/coaching-session-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/coaching-session-service-0.0.1-SNAPSHOT.jar"]