package pe.edu.tecsup.tienda.services;

import java.util.List;

import pe.edu.tecsup.tienda.entities.Rol;

public interface RolService {

	List<Rol> findAll();
	
	Rol findById(Long id);
	
}
