# Spring Framework: Damned Vunerable Web Application

A basic spring application, written by a lazy developer who forgot about security. If fixed up, it could be a good base application to work from...

## Getting Started

To compile and run this example, first install maven

    apt-get install maven

`cd` into the main directory and run

    mvn spring-boot:run

The application will boot up and make itself available on port 8080.

## Tips

- Patch Direct Object References using SQL, a user should only be able to see their own data!
- Think about adding the users to a database, and don't forget to hash the passwords for extra marks (https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d#.4xdfxyobl)
- Patch Missing function level access control (using @Secured, google it!)
