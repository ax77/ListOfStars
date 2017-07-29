package com.solution.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.solution.domain.model.Star;
import com.solution.domain.model.StarDiscoverer;
import com.solution.service.custom.CustomServiceException;
import com.solution.service.interfaces.StarDiscovererService;
import com.solution.service.interfaces.StarService;

@Controller
public class StarControllerAdmin {

	private final StarService starService;
	private final static int DEFAULT_PAGE_SIZE = 10;

	@Autowired
	public StarDiscovererService discovererService;

	@Inject
	public StarControllerAdmin(StarService starService) {
		this.starService = starService;
	}

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String admin() {
		return "admin/home";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/starlist", method = RequestMethod.GET)
	public ModelAndView listNotes(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize,
			HttpSession session) {
		if (page == null) {
			page = 0;
		}

		PagedListHolder<Star> resultList = (PagedListHolder<Star>) session.getAttribute("AdminController_starlist");
		if (resultList == null) {
			resultList = new PagedListHolder<Star>(starService.getAllStars());
			session.setAttribute("AdminController_starlist", resultList);
			if (pageSize == null) {
				pageSize = DEFAULT_PAGE_SIZE;
			}
		}
		if (pageSize != null) {
			resultList.setPageSize(pageSize);
		}
		resultList.setPage(page);

		ModelAndView mv = new ModelAndView("admin/starlist");
		mv.addObject("resultList", resultList);
		mv.addObject("count", starService.countOf());

		return mv;
	}

	@RequestMapping(value = "/admin/star_create", method = RequestMethod.GET)
	public String redirectToStarCreate(Model model) {
		Star notes = new Star();
		model.addAttribute("notes", notes);
		return "admin/star_create";
	}

	@RequestMapping(value = "/admin/star_edit/{code:.+}", method = RequestMethod.GET)
	public String redirectToStarEditDelete(@PathVariable("code") String code, Model model) {
		Star notes = starService.getStarByName(code);
		model.addAttribute("notes", notes);
		return "admin/star_edit";
	}

	@RequestMapping(value = "/admin/star_delete/{code:.+}", method = RequestMethod.GET)
	public ModelAndView deleteNotes(@PathVariable("code") String code, HttpSession session) {
		Star notes = starService.getStarByName(code);
		ModelAndView modelAndView = new ModelAndView("admin/starlist");

		// TODO:ax - Check this on client? JQValidator?
		String starClass = notes.getStarClass();
		if (starClass.equals(new String("Yellow"))) {
			modelAndView.addObject("errorMessage", "Error: 'Forbidden to remove yellow stars!'");
		} else {
			starService.deleteStar(notes);
			modelAndView.addObject("infoMessage", "Star '" + notes.getName() + "' deleted successfully.");
		}

		PagedListHolder<Star> resultList = new PagedListHolder<Star>(starService.getAllStars());
		modelAndView.addObject("resultList", resultList);
		session.setAttribute("AdminController_starlist", resultList);

		return modelAndView;
	}

	@RequestMapping(value = "/admin/star_update", method = RequestMethod.POST)
	public ModelAndView updateNotes(@ModelAttribute Star notes, HttpSession session) {
		ModelAndView modelAndView;
		try {

			starService.updateStar(notes);
			modelAndView = new ModelAndView("admin/starlist");
			String infoMessage = "Star '" + notes.getName() + "' saved successfully.";
			modelAndView.addObject("infoMessage", infoMessage);
			PagedListHolder<Star> resultList = new PagedListHolder<Star>(starService.getAllStars());
			modelAndView.addObject("resultList", resultList);
			session.setAttribute("AdminController_starlist", resultList);
		} catch (CustomServiceException ex) {
			if (notes.getId() == null) {
				modelAndView = new ModelAndView("admin/star_create");
			} else {
				modelAndView = new ModelAndView("admin/star_edit");
			}
			modelAndView.addObject("notes", notes);
			modelAndView.addObject("errorMessage", ex.getErrorMsg());
		}
		return modelAndView;
	}

	@ModelAttribute("discoverers")
	public List<String> discovererList() {
		List<StarDiscoverer> ds = discovererService.getAllDiscoverers();
		List<String> result = new ArrayList<String>();

		for (int i = 0; i < ds.size(); i++) {
			result.add(ds.get(i).getName());
		}

		return result;
	}

	@ModelAttribute("colorList")
	public List<String> getColorList() {
		List<String> colorList = new ArrayList<String>();

		colorList.add("Blue");
		colorList.add("White-blue");
		colorList.add("White");
		colorList.add("Yellow-white");
		colorList.add("Yellow");
		colorList.add("Orange");
		colorList.add("Red");

		return colorList;
	}
}
