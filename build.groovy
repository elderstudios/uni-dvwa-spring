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
			sh 'docker run --rm -t -v `pwd`:/dvwa-spring devsecopsat/spotbugs -textui -low -nested:false -quiet -exclude /dvwa-spring/spotbugs-customExcludeFilter.xml -pluginList /findsecbugs-plugin-1.7.1.jar -output /dvwa-spring/spotbugs-dvwa-spring-result.xml  /dvwa-spring/target/www-0.0.1-SNAPSHOT.jar'
			 findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', isRankActivated: true, pattern: 'spotbugs-dvwa-spring-result.xml', unHealthy: ''
	}
	stage('Dependency-check'){
			sh "docker run --rm -t -v `pwd`:/dvwa-spring devsecopsat/dependency-check --disableCentral --noupdate --project DVWA-Spring --format ALL  --out /dvwa-spring/. --suppression /dvwa-spring/suppressed-cves.xml --scan /dvwa-spring/target/www-0.0.1-SNAPSHOT.jar";
			dependencyCheckPublisher canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '**/dependency-check-report.xml', unHealthy: ''
	}
	/*
	stage('Anchore-cli'){
			 sh "docker run -d -v /var/run/docker.sock:/var/run/docker.sock --name anchore-${BUILD_NUMBER} devsecopsat/anchore-cli:latest";
			 exec on: "anchore-${BUILD_NUMBER}", script: "bash -c 'anchore analyze --image devsecopsat/dvwa-spring:latest --imagetype base'"
			 exec on: "anchore-${BUILD_NUMBER}", script: "bash -c 'anchore toolbox images'"
			 exec on: "anchore-${BUILD_NUMBER}", script: "bash -c 'anchore --html query --image devsecopsat/dvwa-spring:latest cve-scan High' > ${WORKSPACE}/anchore-cve-scan-results.html"
			 publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '', reportFiles: 'anchore-cve-scan-results.html', reportName: 'Anchore-cli Report', reportTitles: ''])
			 end session: "anchore-${BUILD_NUMBER}"
			 sh "docker ps -a"
	}
	*/
	stage('Postman Nemwan'){
		sh ("ls -alh")
	}
}
