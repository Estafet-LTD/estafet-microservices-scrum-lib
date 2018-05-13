node {

	def snapshotVersion;
	def nextSnapshotVersion;
	def releaseVersion

	stage("checkout") {
		git branch: "master", url: "https://github.com/Estafet-LTD/estafet-microservices-scrum-lib"
	}
	
	stage("increment version") {
		println "read the file"
		def pom = readFile('pom.xml');
		snapshotVersion = new XmlSlurper().parseText(pom).version
		println snapshotVersion
		def matcher = snapshotVersion =~ /(\d+\.\d+\.)(\d+)(\-SNAPSHOT)/
		println "get the snapshot integral"
		def snapshotIntegral = matcher[0][1]
		println "get the nextSnapshotVersion"
		nextSnapshotVersion = "${matcher[0][1]}${snapshotIntegral}-SNAPSHOT"
		println "get the releaseVersion"
		releaseVersion = "${matcher[0][1]}${snapshotIntegral}"
		println nextSnapshotVersion
		println releaseVersion
	}
	
}





