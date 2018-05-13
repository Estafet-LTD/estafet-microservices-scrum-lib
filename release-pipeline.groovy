node {

	def snapshotVersion;
	def nextSnapshotVersion;
	def releaseVersion

	stage("checkout") {
		git branch: "master", url: "https://github.com/Estafet-LTD/estafet-microservices-scrum-lib"
	}
	
	stage("increment version") {
		def pom = readFile('pom.xml');
		snapshotVersion = new XmlSlurper().parseText(json).version.text
		def matcher = snapshotVersion =~ /(d+\.d+\.)(d+)(\-SNAPSHOT)/
		int snapshotIntegral = matcher[0][2]
		nextSnapshotVersion = "${matcher[0][1]}${snapshotIntegral+1}-SNAPSHOT"
		releaseVersion = "${matcher[0][1]}${snapshotIntegral}"
		println nextSnapshotVersion
		println releaseVersion
	}
	
}





