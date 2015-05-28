package org.springframework.cloud.zookeeper.discovery

import org.springframework.util.SocketUtils

trait ServerPortSetAndRestoreTrait {

	String previousServerPortValue

	def setupSpec() {
		previousServerPortValue = System.getProperty("server.port")
		System.setProperty("server.port", SocketUtils.findAvailableTcpPort() as String)
	}

	def cleanupSpec() {
		//Unfortunately @RestoreSystemProperties cannot be used to restore changes made in setupSpec()
		if (previousServerPortValue == null) {
			System.clearProperty("server.port")
		} else {
			System.setProperty("server.port", previousServerPortValue)
		}
	}
}
