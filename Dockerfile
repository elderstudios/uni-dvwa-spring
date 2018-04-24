FROM alpine:latest
RUN apk --update add openjdk8-jre
ADD . /dvwa-spring
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/dvwa-spring/target/www-0.0.1-SNAPSHOT.jar"]
