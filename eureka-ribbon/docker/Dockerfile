FROM java:latest
VOLUME /tmp
ADD eureka-ribbon-0.0.1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8781