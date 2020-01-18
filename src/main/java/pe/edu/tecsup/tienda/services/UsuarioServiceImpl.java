package pe.edu.tecsup.tienda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.tecsup.tienda.entities.Departamento;
import pe.edu.tecsup.tienda.entities.Provincia;
import pe.edu.tecsup.tienda.entities.Usuario;
import pe.edu.tecsup.tienda.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Departamento> getDepartamentos() {
		return usuarioRepository.getDepartamentos();
	}

	@Override
	public List<Provincia> getProvincias(String departamentos_id) {
		return usuarioRepository.getProvincias(departamentos_id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return usuarioRepository.loadUserbyUsername(email);
	}

}
