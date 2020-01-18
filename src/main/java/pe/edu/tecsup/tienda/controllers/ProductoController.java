package pe.edu.tecsup.tienda.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.tecsup.tienda.entities.Categoria;
import pe.edu.tecsup.tienda.entities.Producto;
import pe.edu.tecsup.tienda.services.CategoriaService;
import pe.edu.tecsup.tienda.services.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public String findAll(Model model) {
		logger.info("findAll()");
		
		List<Producto> productos = productoService.findAll();
		logger.info("productos: " + productos);
		model.addAttribute("productos", productos);
		
		return "productos/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		logger.info("create()");
		
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		
		List<Categoria> categorias = categoriaService.findAll();
		model.addAttribute("categorias", categorias);
		
		return "productos/create";
	}
	
	@PostMapping("/store")
	public String store(@ModelAttribute Producto producto, 
			RedirectAttributes redirectAttributes) {
		logger.info("store()");
		
		producto.setCreado(new Date());
		producto.setEstado(1);
		
		productoService.save(producto);
		
		redirectAttributes.addFlashAttribute("message", "Registro guardado satisfactoriamente");
		
		return "redirect:/productos";
	}
	
}
