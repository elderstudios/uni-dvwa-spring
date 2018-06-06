node {
	stage('Pull code'){
		checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/DevSecOpsAt/dvwa-spring.git']]])
	}
	stage("Build .war file"){
		withEnv(['PATH+maven=/home/ec2-user/apache-maven-3.5.3/bin/']) {
			sh ("mvn -B -f pom.xml install")
		}
	}
	stage('Spotbugs + FinSecBugs'){
		sh 'docker run --rm -t -v `pwd`:/dvwa-spring devsecopsat/spotbugs -textui -low -nested:false -quiet -exclude /dvwa-spring/spotbugs/spotbugs-customExcludeFilter.xml -pluginList /findsecbugs-plugin-1.7.1.jar -output /dvwa-spring/reports/spotbugs-dvwa-spring-result.xml  /dvwa-spring/target/www-0.0.1-SNAPSHOT.jar'
		findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', isRankActivated: true, pattern: 'spotbugs-dvwa-spring-result.xml', unHealthy: ''
	}
	stage('Dependency-check'){
		sh "docker run --rm -t -v `pwd`:/dvwa-spring devsecopsat/dependency-check --disableCentral --noupdate --project DVWA-Spring --format ALL  --out /dvwa-spring/reports/. --suppression /dvwa-spring/dependency-check/suppressed-cves.xml --scan /dvwa-spring/target/www-0.0.1-SNAPSHOT.jar";
		dependencyCheckPublisher canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '**/reports/dependency-check-report.xml', unHealthy: ''
	}
	stage('Arachni Publish to dojo'){
		def engagement = "/api/v1/engagements/3/"
		def type = "arachni"
		sh "jq -r '.item[0].item[2].request.body.formdata[1].value=\"${engagement}\"' postman/DefectDojo.postman_environment.json > postman/${type}.DefectDojo.postman_collection.json"
		sh ("docker run --rm -v `pwd`:/dvwa-spring postman/newman_ubuntu1404 run /dvwa-spring/postman/${type}.DefectDojo.postman_collection.json -e /dvwa-spring/postman/Defectdojo.postman_environment.json -k")
	}
	stage('Spotbugs Publish to dojo'){
		def engagement = "/api/v1/engagements/1/"
		def type = "spotbugs"
		sh "jq -r '.values[2].value=\"${engagement}\"' postman/DefectDojo.postman_environment.json > postman/${type}.DefectDojo.postman_collection.json"
		sh ("docker run --rm -v `pwd`:/dvwa-spring postman/newman_ubuntu1404 run /dvwa-spring/postman/${type}.DefectDojo.postman_collection.json -e /dvwa-spring/postman/Defectdojo.postman_environment.json -k")
	}
}
