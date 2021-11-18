FROM openjdk:11-alpine
EXPOSE 9090
ARG JAR_FILE=target/OceanBlu-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
USER user01
ENTRYPOINT ["java","-jar","/app.jar"]