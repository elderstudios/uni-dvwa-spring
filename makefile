jar:
	mvn -B -f pom.xml install
build:
	docker build -t devsecopsat/dvwa-spring .
start:
	java -jar www-0.0.1-SNAPSHOT.jar
copy:
	cp target/www-0.0.1-SNAPSHOT.jar .
push:
	docker push devsecopsat/dvwa-spring
