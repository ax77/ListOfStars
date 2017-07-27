package com.solution.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solution.dao.mapper.StarMapping;
import com.solution.domain.model.Star;
import com.solution.service.custom.CustomServiceException;
import com.solution.service.general.GeneralService;
import com.solution.service.interfaces.StarService;

@Component("starService")
public class StarServiceImpl implements StarService {

	@Autowired
	StarMapping mapping;

	public List<Star> getAllStars() {
		return mapping.getAllStars();
	}

	public void updateStar(Star star) {

		Star starToCheck = mapping.getStarByName(star.getName());
		if (GeneralService.domainObjectsAreSame(star, starToCheck)) {
			throw new CustomServiceException("",
					"There is already a Star with name '" + star.getName()
							+ "'. Star name should be unique.");
		}

		if (star.getId() == null) {
			mapping.addStar(star);
		} else {
			mapping.updateStar(star);
		}
	}

	public void deleteStar(Star star) {
		mapping.deleteStar(star);
	}

	public Star getStarById(Long id) {
		return mapping.getStarById(id);
	}

	public Star getStarByName(String name) {
		return mapping.getStarByName(name);
	}
}