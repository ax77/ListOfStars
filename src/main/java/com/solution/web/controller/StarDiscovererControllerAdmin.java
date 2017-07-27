package com.solution.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.solution.domain.model.StarDiscoverer;
import com.solution.service.custom.CustomServiceException;
import com.solution.service.interfaces.StarDiscovererService;

@Controller
public class StarDiscovererControllerAdmin {

	private final StarDiscovererService service;
	private final static int DEFAULT_PAGE_SIZE = 10;

	@Inject
	public StarDiscovererControllerAdmin(StarDiscovererService service) {
		this.service = service;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/star_discoverer_list", method = RequestMethod.GET)
	public ModelAndView listNotes(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize,
			HttpSession session) {
		if (page == null) {
			page = 0;
		}

		PagedListHolder<StarDiscoverer> resultList = (PagedListHolder<StarDiscoverer>) session
				.getAttribute("AdminController_discovererList");
		if (resultList == null) {
			resultList = new PagedListHolder<StarDiscoverer>(service.getAllDiscoverers());
			session.setAttribute("AdminController_discovererList", resultList);
			if (pageSize == null) {
				pageSize = DEFAULT_PAGE_SIZE;
			}
		}
		if (pageSize != null) {
			resultList.setPageSize(pageSize);
		}
		resultList.setPage(page);
		return new ModelAndView("admin/star_discoverer_list", "resultList", resultList);
	}

	@RequestMapping(value = "/admin/star_discoverer_create", method = RequestMethod.GET)
	public String redirectToDiscovererCreate(Model model) {
		StarDiscoverer notes = new StarDiscoverer();
		model.addAttribute("notes", notes);
		return "admin/star_discoverer_create";
	}

	@RequestMapping(value = "/admin/star_discoverer_edit/{code:.+}", method = RequestMethod.GET)
	public String redirectToStarDiscovererEditDelete(@PathVariable("code") String code, Model model) {
		StarDiscoverer notes = service.getDiscovererByName(code);
		model.addAttribute("notes", notes);
		return "admin/star_discoverer_edit";
	}

	@RequestMapping(value = "/admin/star_discoverer_delete/{code:.+}", method = RequestMethod.GET)
	public ModelAndView deleteNotes(@PathVariable("code") String code, HttpSession session) {
		StarDiscoverer notes = service.getDiscovererByName(code);
		service.deleteDiscoverer(notes);
		ModelAndView modelAndView = new ModelAndView("admin/star_discoverer_list");
		String infoMessage = "Discoverer '" + notes.getName() + "' deleted successfully.";
		modelAndView.addObject("infoMessage", infoMessage);
		PagedListHolder<StarDiscoverer> resultList = new PagedListHolder<StarDiscoverer>(service.getAllDiscoverers());
		modelAndView.addObject("resultList", resultList);
		session.setAttribute("AdminController_discovererList", resultList);
		return modelAndView;
	}

	@RequestMapping(value = "/admin/star_discoverer_update", method = RequestMethod.POST)
	public ModelAndView updateNotes(@ModelAttribute StarDiscoverer notes, HttpSession session) {
		ModelAndView modelAndView;
		try {
			service.updateDiscoverer(notes);
			modelAndView = new ModelAndView("admin/star_discoverer_list");
			String infoMessage = "Discoverer '" + notes.getName() + "' saved successfully.";
			modelAndView.addObject("infoMessage", infoMessage);
			PagedListHolder<StarDiscoverer> resultList = new PagedListHolder<StarDiscoverer>(service.getAllDiscoverers());
			modelAndView.addObject("resultList", resultList);
			session.setAttribute("AdminController_discovererList", resultList);
		} catch (CustomServiceException ex) {
			if (notes.getId() == null) {
				modelAndView = new ModelAndView("admin/star_discoverer_create");
			} else {
				modelAndView = new ModelAndView("admin/star_discoverer_edit");
			}
			modelAndView.addObject("notes", notes);
			modelAndView.addObject("errorMessage", ex.getErrorMsg());
		}
		return modelAndView;
	}

}
