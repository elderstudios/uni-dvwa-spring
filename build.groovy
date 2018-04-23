node {
	stage('Pull code'){
		checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/DevSecOpsAt/dvwa-spring.git']]])
	}
	stage('List file'){
		sh 'whoami && ls -alh'
	}
	stage("Build .war file"){
		withEnv(['PATH+maven=/home/vagrant/apache-maven-3.5.3/bin']) {
			sh ("mvn -B -f pom.xml install")
		}
	}
	stage('Image List'){
		sh ("sudo docker images")
	}
	stage('Spotbugs+FinSecBugs'){
		sh ("echo Spotbugs")
	}
	stage('Dependency-check'){
		sh ("echo Spotbugs")
	}
	stage('Anchore-cli'){
		sh ("echo Spotbugs")
	}
	stage('Postman Nemwan'){
		sh ("echo Spotbugs")
	}
