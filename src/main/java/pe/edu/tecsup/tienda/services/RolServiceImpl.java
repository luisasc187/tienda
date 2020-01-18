package pe.edu.tecsup.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.tecsup.tienda.entities.Rol;
import pe.edu.tecsup.tienda.repositories.RolRepository;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public List<Rol> findAll() {
		return rolRepository.findAll();
	}

	@Override
	public Rol findById(Long id) {
		return rolRepository.getOne(id);
	}

}
