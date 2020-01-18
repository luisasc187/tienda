package pe.edu.tecsup.tienda.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pe.edu.tecsup.tienda.entities.Usuario;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	private static final Logger logger  = LoggerFactory.getLogger(ProfileController.class);
	
	@GetMapping()
	public String index(@AuthenticationPrincipal Usuario usuario) {
		logger.info("call index(): " + usuario);
		
		return "profile";
	}
	
	@PreAuthorize("hasAnyAuthority('Administrador')")
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		
		return "Acceso solo para el admin";
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String exception(Throwable throwable, Model model) {
		logger.error("catch exception: " + throwable);
		if(throwable != null)
			model.addAttribute("errorMessage", throwable.getMessage());
		return "error";
	}
	
}
