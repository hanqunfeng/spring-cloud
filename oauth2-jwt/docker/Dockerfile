FROM java:latest
VOLUME /tmp
ADD oauth2-jwt-0.0.1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 9091