package com.solution.domain.model;

import java.io.Serializable;

import com.solution.domain.logic.DomainObject;

public class Star implements Serializable, DomainObject {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String galacticLongitude;
	private String galacticLatitude;
	private String starClass;

	// MARK: 
	// For study application we dont't needed reference control and constraints's;
	// Because: we will not be able to remove the discoverer, until we remove all the stars that it discover.
	// In real application is one-to many associations.
	private String discoverer;

	public Star() {
	}

	public Star(Long id, String name, String galacticLongitude, String galacticLatitude, String starClass,
			String discoverer) {
		super();
		this.id = id;
		this.name = name;
		this.galacticLongitude = galacticLongitude;
		this.galacticLatitude = galacticLatitude;
		this.starClass = starClass;
		this.discoverer = discoverer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean equals(DomainObject domainObject) {
		Star star = (Star) domainObject;
		if (star.getName().equals(this.getName())) {
			return true;
		} else {
			return false;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGalacticLongitude() {
		return galacticLongitude;
	}

	public void setGalacticLongitude(String galacticLongitude) {
		this.galacticLongitude = galacticLongitude;
	}

	public String getGalacticLatitude() {
		return galacticLatitude;
	}

	public void setGalacticLatitude(String galacticLatitude) {
		this.galacticLatitude = galacticLatitude;
	}

	public String getStarClass() {
		return starClass;
	}

	public void setStarClass(String starClass) {
		this.starClass = starClass;
	}

	public String getDiscoverer() {
		return discoverer;
	}

	public void setDiscoverer(String discoverer) {
		this.discoverer = discoverer;
	}
}