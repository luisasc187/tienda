package pe.edu.tecsup.tienda.services;

import java.util.List;

import pe.edu.tecsup.tienda.entities.Departamento;
import pe.edu.tecsup.tienda.entities.Provincia;
import pe.edu.tecsup.tienda.entities.Usuario;

public interface UsuarioService {

	List<Usuario> findAll();
	
	Usuario save(Usuario usuario);
	
	List<Departamento> getDepartamentos();
	
	List<Provincia> getProvincias(String departamentos_id);
	
}
