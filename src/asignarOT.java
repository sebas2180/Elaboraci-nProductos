import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import clases.MyDataAcces;
import clases.empleado;
import clases.materia;
import clases.paso;
import clases.producto;

public class asignarOT implements ActionListener{

	private empleado empleado;
	private OT ordenTrabajo = new OT();
	private JFrame window = new JFrame("aisgnacion de operarios");
	private MyDataAcces conexion;
	private ArrayList<paso> pasos = new ArrayList<>();
	private ArrayList<empleado> operarios = new ArrayList<>();
	private JLabel paso1 = new JLabel("");
	
	private JComboBox<Integer> cb1 = new JComboBox<>();
	private DefaultComboBoxModel<Integer> modelo1 = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<Integer> modelo2= new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<Integer> modelo3 = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<Integer> modelo4 = new DefaultComboBoxModel<>();
	private JLabel mensaje1 = new JLabel("");
	private JLabel paso2 = new JLabel("");
	private JComboBox<Integer> cb2 = new JComboBox<>();
	private JLabel mensaje2 = new JLabel("");
	
	private JLabel paso3 = new JLabel("");
	private JComboBox<Integer> cb3 = new JComboBox<>();
	private JLabel mensaje3 = new JLabel("");
	
	private JLabel paso4 = new JLabel("");
	private JComboBox<Integer> cb4 = new JComboBox<>();
	private JLabel mensaje4 = new JLabel("");
	
	private JButton btnAceptar = new JButton("ACEPTAR");

	public asignarOT(empleado emp, OT ot) {
		try {
			conexion = new MyDataAcces();
		} catch (InstantiationException | IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ResultSet rsEmp = conexion.GetQuery("select * from empleado where tipo='operario'");
		
			try {
				while(rsEmp.next()) {
				empleado e = new empleado();
					e.setLegajo(rsEmp.getInt("legajo"));
					e.setTipo(rsEmp.getString("tipo"));
					e.setNombre(rsEmp.getString("nombre"));
					operarios.add(e);
				}
				modelo1.addElement(0);
				modelo2.addElement(0);
				modelo3.addElement(0);
				modelo4.addElement(0);
				for (empleado ee : operarios) {
					modelo1.addElement(ee.getLegajo());
					modelo2.addElement(ee.getLegajo());
					modelo3.addElement(ee.getLegajo());
					modelo4.addElement(ee.getLegajo());
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		File arcPaso = new File("pasos.txt");
		if(arcPaso.exists()) {
			Scanner scPaso;
			try {
				scPaso = new Scanner(arcPaso);
				while(scPaso.hasNextLine()) {
					paso auxP = new paso();
					String linea = scPaso.nextLine();
					System.out.println(linea.length());
					System.out.println(linea.length()+";"+linea.substring(0, 20)+"-"+linea.substring(21,25)+"-"+linea.substring(25,45)+"-"+linea.substring(46,50));
					System.out.println(linea.substring(25,29));
					int codigo = Integer.parseInt(linea.substring(21,25));
					producto p = new producto();
					p.setCodigo(codigo);
					ordenTrabajo.setProducto(p);
					if(codigo == ordenTrabajo.getProducto().getCodigo()) {
						auxP.setDescripcion(linea.substring(0, 20));
						materia mate = new materia();
						if(!linea.substring(25, 29).equals("null")) {
							mate.setNombre(linea.substring(25,45));
							mate.setCantidad(Integer.parseInt(linea.substring(46,49)));
						}
						
						auxP.setMateria(mate);
						pasos.add(auxP);
					}
					if(pasos.size()==0) {
						JOptionPane.showMessageDialog(null, "error al econtrar archivo de pasos");
						window.setVisible(false);
						menu menu = new menu(empleado);
					}else {
						for (int i = 0; i < pasos.size(); i++) {
							
							if (i==0) {
								paso1.setText(pasos.get(i).getDescripcion());
								cb1.setModel(modelo1);
								window.add(paso1);
								window.add(cb1);
							}
							if (i==1) {
								paso2.setText(pasos.get(i).getDescripcion());
								cb2.setModel(modelo2);
								window.add(paso2);
								window.add(cb2);
							}
							if (i==2) {
								paso3.setText(pasos.get(i).getDescripcion());
								cb3.setModel(modelo3);
								window.add(paso3);
								window.add(cb3);
							}
							if (i==3) {
								paso4.setText(pasos.get(i).getDescripcion());
								cb4.setModel(modelo4);
								window.add(paso4);
								window.add(cb4);
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "error al econtrar archivo de pasos");
				window.setVisible(false);
				menu menu = new menu(empleado);
				e.printStackTrace();
			}
			
		}
		ordenTrabajo = ot;
		empleado = emp;
		window.add(btnAceptar);
		btnAceptar.setActionCommand("aceptar");
		btnAceptar.addActionListener(this);
		window.setSize(500, 500);
		window.setLayout(new FlowLayout());
		window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("aceptar")) {
			boolean t1ok=false,t2ok=false,t3ok=false,t4ok=false;
			

				
				int cant = pasos.size();
				System.out.println("cant"+cant);
				////////////////////////////////////////////////////////////////////////////////
					if(cant==1) {
						if(cb1.getSelectedIndex()==0) {
							mensaje1.setText("falta operario para el paso 1");
						}else {
							mensaje1.setText("");
							t1ok=true;
						}
					
						if(t1ok==true) {
							conexion.ejecutar("insert into asignacion(idOT,legajo,tarea) VALUES("+ordenTrabajo.getIdOT()+","+cb1.getSelectedItem()+",'"+pasos.get(0)+"')");
							JOptionPane.showMessageDialog(null, "guardado exitoso");
							window.setVisible(false);
							menu menu = new menu(empleado);
						}
					}
					/////////////////////////////////////////////////////////////////////
					if(cant==2) {
						if(cb1.getSelectedIndex()==0) {
							mensaje1.setText("falta operario para el paso 1");
						}else {
							mensaje1.setText("");
							t1ok=true;
						}
						if(cb2.getSelectedIndex()==0) {
							mensaje2.setText("falta operario para el paso 2");
						}else {
							mensaje2.setText("");
							t2ok=true;
						}
						if(t1ok==true && t2ok == true) {
							conexion.ejecutar("insert into asignacion(idOT,legajo,tarea) VALUES("+ordenTrabajo.getIdOT()+","+cb1.getSelectedItem()+",'"+pasos.get(0)+"')");
							conexion.ejecutar("insert into asignacion(idOT,legajo,tarea) VALUES("+ordenTrabajo.getIdOT()+","+cb2.getSelectedItem()+",'"+pasos.get(1)+"')");
							JOptionPane.showMessageDialog(null, "guardado exitoso");
							window.setVisible(false);
							menu menu = new menu(empleado);
						}
					}
					/////////////////////////////////////////////////////////////////////////////
					if(cant==3) {
						if(cb1.getSelectedIndex()==0) {
							mensaje1.setText("falta operario para el paso 1");
						}else {
							mensaje1.setText("");
							t1ok=true;
						}
						if(cb2.getSelectedIndex()==0) {
							mensaje2.setText("falta operario para el paso 2");
						}else {
							mensaje2.setText("");
							t2ok=true;
						}
						if(cb3.getSelectedIndex()==0) {
							mensaje3.setText("falta operario para el paso 3");
						}else {
							mensaje3.setText("");
							t3ok=true;
						}
						System.out.println(t1ok+" "+t2ok+" "+t3ok+" "+t4ok);
						if(t1ok==true && t2ok==true && t3ok==true) {
							conexion.ejecutar("insert into asignacion(idOT,legajo,tarea) VALUES("+ordenTrabajo.getIdOT()+","+cb1.getSelectedItem()+",'"+pasos.get(0)+"')");
							conexion.ejecutar("insert into asignacion(idOT,legajo,tarea) VALUES("+ordenTrabajo.getIdOT()+","+cb2.getSelectedItem()+",'"+pasos.get(1)+"')");
							conexion.ejecutar("insert into asignacion(idOT,legajo,tarea) VALUES("+ordenTrabajo.getIdOT()+","+cb3.getSelectedItem()+",'"+pasos.get(3)+"')");
							JOptionPane.showMessageDialog(null, "guardado exitoso");
							window.setVisible(false);
							menu menu = new menu(empleado);
						}
					}
					////////////////////////////////////////////////////////////////////////////
					if(cant==4) {
						if(cb1.getSelectedIndex()==0) {
							mensaje1.setText("falta operario para el paso 1");
						}else {
							mensaje1.setText("");
							t1ok=true;
						}
						if(cb2.getSelectedIndex()==0) {
							mensaje2.setText("falta operario para el paso 2");
						}else {
							mensaje2.setText("");
							t2ok=true;
						}
						if(cb3.getSelectedIndex()==0) {
							mensaje3.setText("falta operario para el paso 3");
						}else {
							mensaje3.setText("");
							t3ok=true;
						}
						if(cb4.getSelectedIndex()==0) {
							mensaje4.setText("falta operario para el paso 4");
						}else {
							mensaje4.setText("");
							t4ok=true;
						}
						
						if(t1ok==true && t2ok && t3ok==true && t4ok==true ) {
							conexion.ejecutar("insert into asignacion(idOT,legajo,tarea) VALUES("+ordenTrabajo.getIdOT()+","+cb1.getSelectedItem()+",'"+pasos.get(0)+"')");
							conexion.ejecutar("insert into asignacion(idOT,legajo,tarea) VALUES("+ordenTrabajo.getIdOT()+","+cb2.getSelectedItem()+",'"+pasos.get(1)+"')");
							conexion.ejecutar("insert into asignacion(idOT,legajo,tarea) VALUES("+ordenTrabajo.getIdOT()+","+cb3.getSelectedItem()+",'"+pasos.get(3)+"')");
							conexion.ejecutar("insert into asignacion(idOT,legajo,tarea) VALUES("+ordenTrabajo.getIdOT()+","+cb4.getSelectedItem()+",'"+pasos.get(4)+"')");
							JOptionPane.showMessageDialog(null, "guardado exitoso");
							window.setVisible(false);
							menu menu = new menu(empleado);

						}
					}
					////////////////////////////////////////////////////////////////////////////////
				
			
		}
		
	}

}
