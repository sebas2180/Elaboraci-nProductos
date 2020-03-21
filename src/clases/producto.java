package clases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class producto implements Iterable{

	private int idProducto;
	private String nombre;
	private int stock;
	private ArrayList<paso> pasos = new ArrayList<>();
	
	
	
	public int getCodigo() {
		return idProducto;
	}
	public void setCodigo(int codigo) {
		this.idProducto = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public ArrayList<paso> getPasos() {
		return pasos;
	}
	public void setPasos(String descrip, empleado emp, String est) {
		pasos.add(new paso(descrip, emp,est));
	}
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return pasos.iterator();
	}
	
	
	
}

