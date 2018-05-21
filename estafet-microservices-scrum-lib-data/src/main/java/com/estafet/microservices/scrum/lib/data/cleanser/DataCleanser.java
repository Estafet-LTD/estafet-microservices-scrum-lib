package com.estafet.microservices.scrum.lib.data.cleanser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.common.io.Resources;
import com.thoughtworks.xstream.XStream;

public class DataCleanser {

	public static void clean() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(Resources.getResource("services.xml").openStream()));
			XStream xStream = new XStream();
			xStream.processAnnotations(Services.class);
			Services services = (Services) xStream.fromXML(reader);
			for (Service service : services.getServices()) {
				service.clean();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

}
