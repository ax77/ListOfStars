package com.solution.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solution.dao.mapper.StarDiscovererMapping;
import com.solution.domain.model.StarDiscoverer;
import com.solution.service.custom.CustomServiceException;
import com.solution.service.general.GeneralService;
import com.solution.service.interfaces.StarDiscovererService;

@Component("starDiscovererService")
public class StarDiscovererImpl implements StarDiscovererService {

	@Autowired
	StarDiscovererMapping mapping;

	public void deleteDiscoverer(StarDiscoverer star) {
		mapping.deleteDiscoverer(star);

	}

	public StarDiscoverer getDiscovererById(Long id) {
		return mapping.getDiscovererById(id);
	}

	public StarDiscoverer getDiscovererByName(String name) {
		return mapping.getDiscovererByName(name);
	}

	public List<StarDiscoverer> getAllDiscoverers() {
		return mapping.getAllDiscoverers();
	}

	public void updateDiscoverer(StarDiscoverer sd) {
		StarDiscoverer starToCheck = mapping.getDiscovererByName(sd.getName());
		if (GeneralService.domainObjectsAreSame(sd, starToCheck)) {
			throw new CustomServiceException("", "There is already a Discoverer with name '" + sd.getName() + "'. Discoverer name should be unique.");
		}

		if (sd.getId() == null) {
			mapping.addDiscoverer(sd);
		} else {
			mapping.updateDiscoverer(sd);
		}

	}

	// NOTE_DYN:ax:
	public void addDiscoverer(StarDiscoverer notes) {
		mapping.addDiscoverer(notes);
	}
}