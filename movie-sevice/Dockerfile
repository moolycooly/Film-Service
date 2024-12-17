FROM openjdk:21
ARG JAR_FILE=build/libs/movie-sevice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} movie-service.jar
ENTRYPOINT ["java","-jar","/movie-service.jar"]