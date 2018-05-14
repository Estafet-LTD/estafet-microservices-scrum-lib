node('maven') {

	def developmentVersion;
	def releaseVersion
	
	stage("checkout") {
		git branch: "master", url: "https://github.com/Estafet-LTD/estafet-microservices-scrum-lib", credentialsId: 'microservices-scrum'
	}
	
	stage("increment version") {
		def pom = readFile('pom.xml');
		def matcher = new XmlSlurper().parseText(pom).version =~ /(\d+\.\d+\.)(\d+)(\-SNAPSHOT)/
		developmentVersion = "${matcher[0][1]}${matcher[0][2].toInteger()+1}-SNAPSHOT"
		releaseVersion = "${matcher[0][1]}${matcher[0][2]}"
	}
	
	stage("perform release") {
        sh "git config --global user.email \"jenkins@estafet.com\""
        sh "git config --global user.name \"jenkins\""
        withMaven(mavenSettingsConfig: 'microservices-scrum') {
			sh "mvn release:clean release:prepare release:perform -DreleaseVersion=${releaseVersion} -DdevelopmentVersion=${developmentVersion}"
		} 
	}	
	
}





