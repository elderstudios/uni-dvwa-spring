node {
	stage("Uuer"){
		sh ("whomai && pwd")
	}
	stage("List file"){
		sh ("ls -alh")
	}
	stage("Image List"){
		sh ("sudo docker images")
	}
	stage("Spotbugs FinSecBugs"){
		sh ("echo Spotbugs")
	}
	stage("Dependency-check"){
		sh ("echo Spotbugs")
	}
	stage("Postman Nemwan"){
		sh ("echo Spotbugs")
	}
	stage("Anchore-cli"){
		sh ("echo Spotbugs")
	}
	stage("Pull code"){
		checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/DevSecOpsAt/dvwa-spring.git']]])
	}
	stage("Build .war file"){
		withEnv(['PATH+maven=/home/vagrant/apache-maven-3.5.3/bin']) {
			sh ("mvn -B -f pom.xml install")
		}
	}

	stage('Spotbugs'){
		sh 'docker run --rm -t -v `pwd`:/dvwa-spring docker.io/devsecopsat/spotbugs -textui -low -nested:false -quiet -exclude /dvwa-spring/spotbugs-customExcludeFilter.xml -pluginList /findsecbugs-plugin-1.7.1.jar -output /dvwa-spring/spotbugs-dvwa-spring-result.html -html:fancy-hist.xsl  /dvwa-spring/target/www-0.0.1-SNAPSHOT.jar'
	}
}
