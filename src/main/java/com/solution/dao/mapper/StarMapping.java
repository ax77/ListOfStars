package com.solution.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.solution.domain.model.Star;

public interface StarMapping {

	@Insert("INSERT INTO star(name, galacticLongitude, galacticLatitude, starClass, discoverer) "
			+ "VALUES(#{name}, #{galacticLongitude}, #{galacticLatitude}, #{starClass}, #{discoverer})")
	public void addStar(Star star);

	@Update("UPDATE star SET "
			+ "name=#{name}, "
			+ "galacticLongitude=#{galacticLongitude}, "
			+ "galacticLatitude=#{galacticLatitude}, "
			+ "starClass=#{starClass}, "
			+ "discoverer=#{discoverer} "
			+ "where id=#{id}")
	public void updateStar(Star star);

	@Delete("DELETE from star where id = #{id}")
	public void deleteStar(Star star);

	public void deleteStarById(Long id);

	public Star getStarById(Long id);

	@Select("SELECT ID as id, name as name, "
			+ "galacticLongitude as galacticLongitude, "
			+ "galacticLatitude as galacticLatitude, "
			+ "starClass as starClass, "
			+ "discoverer as discoverer " 
			+ "FROM star WHERE name = #{name}")
	public Star getStarByName(String name);

	@Select("select count(*) from star")
	public int countAll();

	@Select("SELECT ID as id, name as name, "
			+ "galacticLongitude as galacticLongitude, "
			+ "galacticLatitude as galacticLatitude, "
			+ "starClass as starClass,"
			+ "discoverer as discoverer " 
			+ "FROM star")
	public List<Star> getAllStars();

}