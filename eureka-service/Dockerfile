FROM openjdk:21
ARG JAR_FILE=build/libs/eureka-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} eureka-service.jar
ENTRYPOINT ["java", "-jar", "/eureka-service.jar"]
