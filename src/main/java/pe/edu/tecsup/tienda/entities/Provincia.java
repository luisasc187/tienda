package pe.edu.tecsup.tienda.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "provincias")
public class Provincia {

	@Id
	private String id;
	
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "departamentos_id")
	private Departamento departamento;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Provincia [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + "]";
	}
	
}
