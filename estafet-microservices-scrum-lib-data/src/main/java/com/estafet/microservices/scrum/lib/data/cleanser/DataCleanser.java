package com.estafet.microservices.scrum.lib.data.cleanser;

import java.io.File;

import com.google.common.io.Resources;
import com.thoughtworks.xstream.XStream;

public class DataCleanser {
	public static void main(String args[]) {
		new DataCleanser().clean();
	}
	public void clean() {
		File file = new File(Resources.getResource("services.xml").getPath());
		XStream xStream = new XStream();
		xStream.processAnnotations(Services.class);
		Services services = (Services)xStream.fromXML(file);
		for (Service service : services.getServices()) {
			service.clean();
		}
	}
	
}
