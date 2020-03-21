import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import clases.MyDataAcces;
import clases.empleado;

public class login implements ActionListener {
	
	private JFrame window = new JFrame("login");
	private JLabel lblUsuario = new JLabel("ingrese nro legajo");
	private JTextField txtUsuario = new JTextField(15);
	private JButton btnEntrar = new JButton("ENTRAR");
	private MyDataAcces conexion;
	private JLabel mensaje= new JLabel("");
	public login() {
		
		window.add(lblUsuario);
		window.add(txtUsuario);
		window.add(btnEntrar);
		btnEntrar.setActionCommand("entrar");
		btnEntrar.addActionListener(this);
		window.setSize(500, 500);
		window.setLayout(new FlowLayout());
		window.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("entrar")) {
			
			try {
				conexion = new MyDataAcces();
			if(txtUsuario.getText()!=null) {
				
				int legajo = Integer.parseInt(txtUsuario.getText());
				empleado empleado = new empleado();
				if(legajo>0 && legajo<10000) {
					mensaje.setText("");
					System.out.println("select * from empleado where legajo="+legajo);
					ResultSet rsEmp = conexion.GetQuery("select * from empleado where legajo="+legajo);
					boolean encontrado=false;
					try {
						while(rsEmp.next()) {
							System.out.println("hola");
							encontrado=true;
							empleado.setLegajo(rsEmp.getInt("legajo"));
							empleado.setTipo(rsEmp.getString("tipo"));
							empleado.setNombre(rsEmp.getString("nombre"));
						}
						if(encontrado==true) {
							menu menu = new menu(empleado);
						}else {
							mensaje.setText("legajo inexistente");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					mensaje.setText("el numero de legajo debe estar entre  0 y 10000");
				}
			
			}
			}catch (NumberFormatException | InstantiationException | IllegalAccessException e0) {
				mensaje.setText("el legajo debe ser numerico");
			}
			
					
		}
		
	}

}
