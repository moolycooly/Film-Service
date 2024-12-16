FROM openjdk:21
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} -service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]