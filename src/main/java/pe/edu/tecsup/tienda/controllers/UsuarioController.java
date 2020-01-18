package pe.edu.tecsup.tienda.controllers;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.tecsup.tienda.entities.Departamento;
import pe.edu.tecsup.tienda.entities.Provincia;
import pe.edu.tecsup.tienda.entities.Rol;
import pe.edu.tecsup.tienda.entities.Usuario;
import pe.edu.tecsup.tienda.services.RolService;
import pe.edu.tecsup.tienda.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@GetMapping()
	public String index(Model model) {
		
		List<Usuario> usuarios = usuarioService.findAll();
		model.addAttribute("usuarios", usuarios);
		
		return "usuarios/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		List<Rol> roles = rolService.findAll();
		model.addAttribute("roles", roles);
		
		List<Departamento> departamentos = usuarioService.getDepartamentos();
		model.addAttribute("departamentos", departamentos);
		
		return "usuarios/create";
	}
	
	@PostMapping("/store")
	public String store(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
		logger.info("store(): " + usuario);
		
		usuario.setEstado(1);
		
		usuario = usuarioService.save(usuario);
		logger.info("usuario: " + usuario);
		
		redirectAttributes.addFlashAttribute("message", "Registro guardado satisfactoriamente");
		
		return "redirect:/usuarios";
	}
	
	/*@PostMapping("/store")
	public String store(
//			HttpServletRequest request,
			@RequestParam("rol") Long rol,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("nombres") String nombres,
			@RequestParam("apellidos") String apellidos,
			@RequestParam("sexo") String sexo,
			@RequestParam("fecnacimiento") String fecnacimiento,
			@RequestParam("telefono") String telefono,
			@RequestParam("direccion") String direccion,
			RedirectAttributes redirectAttributes) {

//		String email = request.getParameter("email");
		
		logger.info("rol: " + rol);
		logger.info("email: " + email);
		logger.info("nombres: " + nombres);
		logger.info("apellidos: " + apellidos);
		logger.info("sexo: " + sexo);
		logger.info("fecnacimiento: " + fecnacimiento);
		
		Usuario usuario = new Usuario();
		usuario.setRol(rolService.findById(rol));
		usuario.setEmail(email);
		usuario.setPassword(password);
		usuario.setNombres(nombres);
		usuario.setApellidos(apellidos);
		usuario.setSexo(sexo);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			usuario.setFecnacimiento(sdf.parse(fecnacimiento));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		usuario.setTelefono(telefono);
		usuario.setDireccion(direccion);
		usuario.setEstado(1);
		
		usuario = usuarioService.save(usuario);
		logger.info("ID generado: " + usuario.getId());
		
		redirectAttributes.addFlashAttribute("message", "Registro guardado satisfactoriamente");
		
		return "redirect:/usuarios";
	}*/
	
	// /usuarios/provincias/15
	@GetMapping("/provincias/{departamentos_id}")
	public @ResponseBody List<Provincia> getProvincias(@PathVariable("departamentos_id") String departamentos_id){
		logger.info("getProvincias(departamentos_id: " + departamentos_id + ")");
		
		return usuarioService.getProvincias(departamentos_id);
	}
	
}
