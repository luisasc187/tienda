package pe.edu.tecsup.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.tecsup.tienda.entities.Producto;
import pe.edu.tecsup.tienda.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public List<Producto> searchByNameLike(String name) {
		return productoRepository.searchByNameLike(name);
	}

}
