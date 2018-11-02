FROM java:latest
VOLUME /logs/spring-cloud
ADD eureka-provider-0.0.1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8771