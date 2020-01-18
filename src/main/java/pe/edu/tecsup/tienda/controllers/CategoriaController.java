package pe.edu.tecsup.tienda.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.tecsup.tienda.entities.Categoria;
import pe.edu.tecsup.tienda.services.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	private static final Logger logger = LoggerFactory.getLogger(CategoriaController.class);
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public String findAll(Model model) {
		logger.info("findAll()");
		
		List<Categoria> categorias = categoriaService.findAll();
		logger.info("categorias: " + categorias);
		
		model.addAttribute("categorias", categorias);
		
		return "categorias/index";
	}
	
}
