import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.*;

public class visualizarTareas {
	
	private JFrame window = new JFrame("visualizar tareas");
	private JTable tabla = new JTable();
	private JScrollPane barra = new JScrollPane();
	private empleado emplead = new empleado();
	private MyDataAcces conexion;
	private ArrayList<OT> ordenes = new ArrayList<>();
	
	public visualizarTareas(empleado emp) {
		
	
		emplead = emp;
		System.out.println(emp.getLegajo()+"|||||"+emp.getTipo());
		System.out.println(emplead.getLegajo()+"|||||"+emplead.getTipo());
		
		crearTabla(emp);
		window.add(barra);
		window.setLayout(new FlowLayout());
		window.setSize(800, 800);
		window.setVisible(true);
		
		}
	private void crearTabla(empleado emp) {
		System.out.println(emp.getLegajo()+"|||||"+emp.getTipo());
		String[] titulos = {"legajo","idOT","Tarea","Estado"};
		String[][] datos = obtenerMatriz(emp);
		tabla.setModel(new DefaultTableModel(datos,titulos));
		barra.setViewportView(tabla);
		
	}
	private String[][] obtenerMatriz(empleado emp) {
		System.out.println(emp.getLegajo()+"|||||"+emp.getTipo());
		String[][] datos = null;
		
		
		try {
			conexion = new MyDataAcces();
			if(emplead.getTipo().equals("operario")) {
				ResultSet rsPasos = conexion.GetQuery("select * from asignacion where legajo="+emplead.getLegajo());
				while(rsPasos.next()) {
					paso aux = new paso();
					aux.setEstado(rsPasos.getString("estado"));
					aux.setDescripcion(rsPasos.getString("tarea"));
					OT ot = new OT();
					boolean encontrado = false;
					for (OT o : ordenes) {
						if(o.getIdOT().equals(rsPasos.getString("idOT"))) {
							o.getProducto().setPasos(aux.getDescripcion(), emplead, aux.getEstado());
							encontrado=true;
						}
					}
					if(encontrado==false) {
						producto p = new producto();
						ot.setProducto(p);
						ot.setIdOT(rsPasos.getString("idOT"));
						ot.getProducto().setPasos(aux.getDescripcion(), emplead, aux.getEstado());
						ordenes.add(ot);
					}
					
				}
				int i=0;
				datos = new String[ordenes.size()][4];
				for (OT o  : ordenes) {
					Iterator<paso> pas = o.getProducto().iterator();
					for (Iterator iterator = o.getProducto().iterator(); iterator.hasNext();) {
						paso pa = (paso) iterator.next();
						datos[i][0] =emp.getLegajo()+"";
						datos[i][1] =o.getIdOT()+"";
						datos[i][2] =pa.getDescripcion()+"";
						datos[i][3] =pa.getEstado()+"";
				i++;
					}					
				}
				
			}
			
		} catch (InstantiationException | IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}
}
