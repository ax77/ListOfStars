package com.solution.service.interfaces;

import java.util.List;

import com.solution.domain.model.Star;

public interface StarService {
	public void updateStar(Star star);

	public void deleteStar(Star star);

	public Star getStarById(Long id);

	public Star getStarByName(String name);

	public List<Star> getAllStars();
	
	public int countOf();
}