package com.solution.service.interfaces;

import java.util.List;

import com.solution.domain.model.StarDiscoverer;

public interface StarDiscovererService {
	
	// NOTE_DYN:ax:
	public void addDiscoverer(StarDiscoverer notes);
	
	public void updateDiscoverer(StarDiscoverer sd);

	public void deleteDiscoverer(StarDiscoverer sd);

	public StarDiscoverer getDiscovererById(Long id);

	public StarDiscoverer getDiscovererByName(String name);

	public List<StarDiscoverer> getAllDiscoverers();
}