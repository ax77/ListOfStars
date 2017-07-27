package com.solution.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.solution.domain.model.StarDiscoverer;

public interface StarDiscovererMapping {

	@Insert("INSERT INTO star_discoverer(name) VALUES(#{name})")
	public void addDiscoverer(StarDiscoverer notes);

	@Update("UPDATE star_discoverer SET name=#{name} where id=#{id}")
	public void updateDiscoverer(StarDiscoverer notes);

	@Delete("DELETE from star_discoverer where id = #{id}")
	public void deleteDiscoverer(StarDiscoverer notes);

	public void deleteDiscovererById(Long id);

	public StarDiscoverer getDiscovererById(Long id);

	@Select("SELECT ID as id, name as name FROM star_discoverer WHERE name = #{name}")
	public StarDiscoverer getDiscovererByName(String name);

	@Select("select count(*) from star_discoverer")
	public int countAll();

	@Select("SELECT ID as id, name as name FROM star_discoverer")
	public List<StarDiscoverer> getAllDiscoverers();

}