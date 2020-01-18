package pe.edu.tecsup.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import pe.edu.tecsup.tienda.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

	@Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:name%")
	List<Producto> searchByNameLike(@Param("name") String name);
	
}
