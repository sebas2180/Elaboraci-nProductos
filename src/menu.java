import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import clases.empleado;

public class menu implements ActionListener {
	private JFrame window  = new JFrame("menu");
	private empleado emplead ;
	private JMenu menu = new JMenu("OPCIONES");
	private JMenuBar barra = new JMenuBar();
	private JMenuItem ingresoOrden = new JMenuItem("ingresoOrden");

	private JMenuItem consultarTareas = new JMenuItem("consultarTareas");
	private JMenuItem actualizarInstrucciones = new JMenuItem("actualizarInstrucciones");
	
	public menu(empleado empleado) {
		emplead = empleado;
		if(emplead.getTipo().equals("supervisor")) {
			menu.add(ingresoOrden);
			menu.addSeparator();
			
			menu.addSeparator();
			menu.add(actualizarInstrucciones);
			ingresoOrden.addActionListener(this);
		
			actualizarInstrucciones.addActionListener(this);
		}
		menu.addSeparator();
		menu.add(consultarTareas);
		consultarTareas.addActionListener(this);
		if(emplead.getTipo().equals("operario")) {
			
		}
		barra.add(menu);
		window.add(barra);
		window.setSize(500, 800);
		window.setLayout(new FlowLayout());
		window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ingresoOrden) {
			window.setVisible(false);
			ingresoOT ingreso = new ingresoOT(emplead);
		}
		
		if(e.getSource() == actualizarInstrucciones) {
			window.setVisible(false);
		}
		if(e.getSource() == consultarTareas) {
			window.setVisible(false);
			System.out.println("login: "+emplead.getLegajo()+"  "+emplead.getTipo());
			visualizarTareas visua = new visualizarTareas(emplead);
		}
		
	}

}
