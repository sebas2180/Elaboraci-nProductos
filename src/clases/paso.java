package clases;

import java.util.Calendar;

public class paso {
	
	private String descripcion;
	private materia materia;
	private empleado empleado;
	private String estado;
	private Calendar fechaYhora;
	
	
	
	
	public Calendar getFechaYhora() {
		return fechaYhora;
	}
	public void setFechaYhora(Calendar fechaYhora) {
		this.fechaYhora = fechaYhora;
	}
	public paso() {
		super();
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public materia getMateria() {
		return materia;
	}
	public void setMateria(materia materia) {
		this.materia = materia;
	}
	public empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(empleado empleado) {
		this.empleado = empleado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public paso(String descripcion, clases.empleado empleado, String estado) {
		super();
		this.descripcion = descripcion;
		this.empleado = empleado;
		this.estado = estado;
	}
	
	
	
}
