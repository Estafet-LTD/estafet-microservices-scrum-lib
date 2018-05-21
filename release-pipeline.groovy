def username() {
    withCredentials([usernamePassword(credentialsId: 'microservices-scrum', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        return USERNAME
    }
}

def password() {
    withCredentials([usernamePassword(credentialsId: 'microservices-scrum', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        return PASSWORD
    }
}

node('maven') {

	def developmentVersion;
	def releaseVersion
	def releaseTag
	
	stage("checkout") {
		git branch: "master", url: "https://${username()}:${password()}@github.com/Estafet-LTD/estafet-microservices-scrum-lib"
	}
	
	stage("increment version") {
		def pom = readFile('pom.xml');
		def matcher = new XmlSlurper().parseText(pom).version =~ /(\d+\.\d+\.)(\d+)(\-SNAPSHOT)/
		developmentVersion = "${matcher[0][1]}${matcher[0][2].toInteger()+1}-SNAPSHOT"
		releaseVersion = "${matcher[0][1]}${matcher[0][2]}"
		releaseTag = "v${releaseVersion}"
	}
	
	stage("perform release") {
        sh "git config --global user.email \"jenkins@estafet.com\""
        sh "git config --global user.name \"jenkins\""
        withMaven(mavenSettingsConfig: 'microservices-scrum') {
			sh "mvn release:clean release:prepare release:perform -DreleaseVersion=${releaseVersion} -DdevelopmentVersion=${developmentVersion} -DpushChanges=false -DlocalCheckout=true -DpreparationGoals=initialize -B"
			sh "git push origin master"
			sh "git tag ${releaseTag}"
			sh "git push origin ${releaseTag}"
		} 
	}	
	
}





