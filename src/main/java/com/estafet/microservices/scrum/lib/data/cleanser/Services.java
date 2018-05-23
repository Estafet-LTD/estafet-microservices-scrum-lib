package com.estafet.microservices.scrum.lib.data.cleanser;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(value = "services")
public class Services {
	
	@XStreamImplicit
	private List<Service> services;

	public List<Service> getServices() {
		return services;
	}

}
