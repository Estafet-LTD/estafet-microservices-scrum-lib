node('maven') {

	def developmentVersion;
	def releaseVersion
	
	stage("checkout") {
		git branch: "master", url: "https://github.com/Estafet-LTD/estafet-microservices-scrum-lib"
	}
	
	stage("increment version") {
		def pom = readFile('pom.xml');
		def matcher = new XmlSlurper().parseText(pom).version =~ /(\d+\.\d+\.)(\d+)(\-SNAPSHOT)/
		developmentVersion = "${matcher[0][1]}${matcher[0][2].toInteger()+1}-SNAPSHOT"
		releaseVersion = "${matcher[0][1]}${matcher[0][2]}"
	}
	
	stage("perform release") {
		 withCredentials([usernamePassword(credentialsId: 'microservices-scrum', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
		 	def credentialsFile = new File("~/.microservices-scrum-credentials")
		 	def credentials = new Properties()
			credentials.setProperty('username', env.USERNAME)
			credentials.setProperty('password', env.PASSWORD)
			credentialsFile.withWriterAppend( 'UTF-8' ) { fileWriter ->
			    fileWriter.writeLine ''
			    credentials.each { key, value ->
			        fileWriter.writeLine "$key=$value"
			    }
			}
            sh "git config --global user.email \"jenkins@estafet.com\""
            sh "git config --global user.name \"jenkins\""
            sh "git config --global credential.helper 'store --file ~/.microservices-scrum-credentials'"
            withMaven(mavenSettingsConfig: 'microservices-scrum') {
 				sh "mvn release:clean release:prepare release:perform -DreleaseVersion=${releaseVersion} -DdevelopmentVersion=${developmentVersion}"
			} 
        }
	}	
	
}





