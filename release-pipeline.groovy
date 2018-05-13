node {

	def nextSnapshotVersion;
	def releaseVersion

	stage("checkout") {
		git branch: "master", url: "https://github.com/Estafet-LTD/estafet-microservices-scrum-lib"
	}
	
	stage("increment version") {
		println "read the file"
		def pom = readFile('pom.xml');
		def snapshotVersion = new XmlSlurper().parseText(pom).version
		def matcher = snapshotVersion =~ /(\d+\.\d+\.)(\d+)(\-SNAPSHOT)/
		nextSnapshotVersion = "${matcher[0][1]}${matcher[0][2].toInteger()+1}-SNAPSHOT"
		releaseVersion = "${matcher[0][1]}${matcher[0][2]}"
		println nextSnapshotVersion
		println releaseVersion
	}
	
}





