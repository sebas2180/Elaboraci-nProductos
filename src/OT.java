import java.util.Calendar;
import java.util.Iterator;

import clases.producto;

public class OT   {

	
	private String idOT;
	private producto producto;
	private Calendar inicio;
	private Calendar finEstimado;
	private String estado;
	private String descripcion;
	private int cantidad;
	private boolean urgente;
	
	
	public boolean isUrgente() {
		return urgente;
	}
	public void setUrgente(boolean urgente) {
		this.urgente = urgente;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public OT() {
		super();
	}
	public String getIdOT() {
		return idOT;
	}
	public void setIdOT(String idOT) {
		this.idOT = idOT;
	}
	public producto getProducto() {
		return producto;
	}
	public void setProducto(producto producto) {
		this.producto = producto;
	}
	public Calendar getInicio() {
		return inicio;
	}
	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}
	public Calendar getFinEstimado() {
		return finEstimado;
	}
	public void setFinEstimado(Calendar finEstimado) {
		this.finEstimado = finEstimado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
}
