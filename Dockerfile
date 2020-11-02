FROM openjdk:11.0.8-jdk-slim
VOLUME /tmp
ADD target/sample-api-service-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]