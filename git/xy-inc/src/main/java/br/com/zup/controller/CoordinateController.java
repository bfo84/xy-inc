package br.com.zup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.domain.entity.Coordinate;
import br.com.zup.service.CoordinateService;

@Controller
public class CoordinateController {

	@Autowired
	private CoordinateService coordinateService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/coordinates", method = RequestMethod.GET)
	public String list(Model model, Pageable pageable) throws Exception {
		Page<Coordinate> coordinatePage = coordinateService.findAll(pageable);
		PageWrapper<Coordinate> page = new PageWrapper<Coordinate>(coordinatePage, "/coordinates");
		model.addAttribute("coordinates", page.getContent());
		model.addAttribute("page", page);
		return "coordinates";
	}

	@RequestMapping("coordinate/{id}")
	public String showCoordinate(@PathVariable Long id, Model model) {
		model.addAttribute("coordinate", coordinateService.findById(id));
		return "coordinateShow";
	}

	@RequestMapping("coordinate/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("coordinate", coordinateService.findById(id));
		return "coordinateForm";
	}

	@RequestMapping("coordinate/new")
	public String newCoordinate(Model model) {
		model.addAttribute("coordinate", new Coordinate());
		return "coordinateForm";
	}

	@RequestMapping(value = "coordinate", method = RequestMethod.POST)
	public String saveCoordinate(Coordinate coordinate) throws Exception {
		if (coordinate.getX() > 0 && coordinate.getY() > 0) {
			coordinateService.save(coordinate);
			return "redirect:/coordinate/" + coordinate.getId();
		}
		return "coordinates";
	}

	@RequestMapping("find")
	public String find(Model model) {
		model.addAttribute("coordinate", new Coordinate());
		return "findCoordinate";
	}

	@RequestMapping(value = "findCoordinates", method = RequestMethod.POST)
	public String findCoordinates(Coordinate coordinate, Model model) throws Exception {
		List<Coordinate> list = coordinateService.findCoordinates(coordinate);
		model.addAttribute("coordinates", list);
		return "findCoordinate";
	}

	@RequestMapping("coordinate/delete/{id}")
	public String delete(@PathVariable Long id) throws Exception {
		coordinateService.delete(id);
		return "redirect:/coordinates";
	}
}
