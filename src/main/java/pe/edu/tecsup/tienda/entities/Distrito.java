package pe.edu.tecsup.tienda.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "distritos")
public class Distrito {

	@Id
	private String id;
	
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "provincias_id")
	private Provincia provincia;

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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "Distrito [id=" + id + ", nombre=" + nombre + ", provincia=" + provincia + "]";
	}
	
}
