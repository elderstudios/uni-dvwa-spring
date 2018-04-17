FROM openjdk:8-jdk
ADD . /oke
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/oke/www-0.0.1-SNAPSHOT.jar"]
