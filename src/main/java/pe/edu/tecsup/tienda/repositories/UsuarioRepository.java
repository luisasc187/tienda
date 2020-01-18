package pe.edu.tecsup.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import pe.edu.tecsup.tienda.entities.Departamento;
import pe.edu.tecsup.tienda.entities.Provincia;
import pe.edu.tecsup.tienda.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT d FROM Departamento d")
	List<Departamento> getDepartamentos();
	
	@Query("SELECT p FROM Provincia p WHERE departamentos_id=:departamentos_id")
	List<Provincia> getProvincias(@Param("departamentos_id") String departamentos_id);
	
	@Query("SELECT u FROM Usuario u WHERE email=:email")
	UserDetails loadUserbyUsername(@Param("email") String email);
	
}
