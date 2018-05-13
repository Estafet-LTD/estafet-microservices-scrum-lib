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
		//println pom
		println "get the snapshot version from pom"
		snapshotVersion = new XmlSlurper().parseText(pom).version.text
		println snapshotVersion
		def matcher = snapshotVersion =~ /(\d+\.\d+\.)(\d+)(\-SNAPSHOT)/
		/* println "get the snapshot integral"
		int snapshotIntegral = matcher[1]
		println "get the nextSnapshotVersion"
		nextSnapshotVersion = "${matcher[0]${snapshotIntegral+1}-SNAPSHOT"
		println "get the releaseVersion"
		releaseVersion = "${matcher[0]${snapshotIntegral}"
		println nextSnapshotVersion
		println releaseVersion */
	}
	
}





