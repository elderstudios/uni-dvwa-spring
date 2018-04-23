FROM openjdk:8-jdk
ADD . /dvwa-spring
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/dvwa-spring/target/www-0.0.1-SNAPSHOT.jar"]
