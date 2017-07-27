package com.solution.domain.model;

import java.io.Serializable;

import com.solution.domain.logic.DomainObject;

public class StarDiscoverer implements Serializable, DomainObject {
	private static final long serialVersionUID = 276059970118477142L;

	private Long id;
	private String name;

	public boolean equals(DomainObject domainObject) {
		StarDiscoverer opener = (StarDiscoverer) domainObject;
		if (opener.getName().equals(this.getName())) {
			return true;
		} else {
			return false;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
