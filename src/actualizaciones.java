import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import clases.empleado;

public class actualizaciones {

	
	private JFrame window = new JFrame("actualizaciones desde archivo");
	private empleado emple;
	
	public actualizaciones(empleado emp) {
		emple = emp;
		String nombre = null;
		nombre = JOptionPane.showInputDialog(null, "ingrese el nombre del archivo");
		
		File archivo = new File(nombre+".txt");
		if(archivo.exists()) {
			try {
				Scanner scArc = new Scanner(archivo);
				while(scArc.hasNextLine()) {
					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "El archivo no existe, reintente");
			window.setVisible(false);
			actualizaciones act = new actualizaciones(emp);
		}
		window.setLayout(new FlowLayout());
		window.setVisible(true);
		window.setSize(800,800);
	}
}
