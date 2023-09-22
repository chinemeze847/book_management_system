FROM amazoncorretto:17.0.3-alpine
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","/book-app.jar"]