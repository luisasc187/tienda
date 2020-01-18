package pe.edu.tecsup.tienda.services;

import java.util.List;

import pe.edu.tecsup.tienda.entities.Producto;

public interface ProductoService {

	public List<Producto> findAll();
	
	public Producto save(Producto producto);
	
	List<Producto> searchByNameLike(String name);
	
}
