import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Group;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;

import clases.*;

public class ingresoOT implements ActionListener  {
	private int ancho = 15;
	private JFrame window = new JFrame("Ingreso OT");
	private empleado emple = new empleado();
	private Calendar fechaActual = Calendar.getInstance();
	private JLabel lblCodigo = new JLabel("Codigo:");
	private JTextField txtCodigo = new JTextField(ancho);
	private JLabel lblCantidad = new JLabel("Cantidad:");
	private JTextField txtCantidad = new JTextField(ancho);
	private JLabel lblProducto = new JLabel("Codigo Producto:");
	private JTextField txtProducto = new JTextField(ancho);
	private JLabel lblComentario= new JLabel("Comentario: ");
	private JTextField txtComentario = new JTextField(ancho);
	private JLabel lblFin = new JLabel("fecha estimada fin(> a la actual(yyyy-mm-dd)");
	private JComboBox<Integer> cbDia = new JComboBox<>();
	private JComboBox<Integer> cbMes = new JComboBox<>();
	private JComboBox<Integer> cbAnio = new JComboBox<>();
	private JButton btnAceptar = new JButton("ACEPTAR");
	private JButton btnLimpiar = new JButton("LIMPIAR");
	private JLabel lblnada = new JLabel("-");
	private JLabel lblUrgente = new JLabel("Es urgente::");
	private JRadioButton rbSi = new JRadioButton("SI");
	private JRadioButton rbnO = new JRadioButton("NO");
	private ButtonGroup grupo = new ButtonGroup();
	private MyDataAcces conexion;
	private SimpleDateFormat sdfY= new SimpleDateFormat("yyyy");
	private OT ordenTrabajo = new OT();
	private JLabel mensaje0 = new JLabel("");
	private JLabel mensaje1 = new JLabel("");
	private JLabel mensaje2 = new JLabel("");
	private JLabel mensaje3 = new JLabel("");
	private JLabel mensaje4 = new JLabel("");
	private JLabel mensaje5 = new JLabel("");
	private Calendar fin = Calendar.getInstance();
	public ingresoOT(empleado emp) {
		emple = emp;
		try {
			conexion = new MyDataAcces();
			ResultSet rsCodigo = conexion.GetQuery("select * from OT order by idOT DESC");
			OT ot = new OT();
			boolean encontrado = false;
			if(rsCodigo.next()) {
				ot.setIdOT(rsCodigo.getString("idOT"));
				encontrado=true;
			}
			int cod = Integer.parseInt(ot.getIdOT().substring(0,4));
			String anioCod = (ot.getIdOT().substring(5, 9));
			System.out.println(cod+" / "+anioCod);
			String id = null;
			if(encontrado == true) {
				
				if(sdfY.format(fechaActual.getTime()).equals(anioCod)){
					 cod = cod+1;
					 if(cod<10) {  id = "000"+cod+"/"+sdfY.format(fechaActual.getTime());								 }
					 if(cod>=10 && cod<100) {     id = "00"+cod+"/"+sdfY.format(fechaActual.getTime());				 }
					 if(cod>=100 && cod<1000) {	  id = "0"+cod+"/"+sdfY.format(fechaActual.getTime());					 }
					 if(cod>=1000 && cod<10000) {  id = cod+"/"+sdfY.format(fechaActual.getTime());					 }
							
					txtCodigo.setText(ordenTrabajo.getIdOT());
					
				}else {
					//empiezo desde cero pero con anio actual
					 id = "0000/"+sdfY.format(fechaActual.getTime());
				}
			}else {			 id = "0000/"+sdfY.format(fechaActual.getTime());
			}
			ordenTrabajo.setIdOT(id);
			txtCodigo.setText(id);
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultComboBoxModel<Integer> modeloAnio = new DefaultComboBoxModel<>();
		for (int i = 0; i < 5; i++) {
			int anio = Integer.parseInt(sdfY.format(fechaActual.getTime()))+i;
			modeloAnio.addElement(anio);
		}
		cbMes.setModel(new DefaultComboBoxModel(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12}));
		cbMes.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				int mes= (int) cbMes.getSelectedItem();
				if( mes ==2) {
					cbDia.setModel(new DefaultComboBoxModel<>( new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28}));
				}
				if(mes ==1 || mes == 3 || mes ==5 || mes == 7 || mes == 8 || mes == 10 || mes ==12 ) {
					cbDia.setModel(new DefaultComboBoxModel<>( new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30}));
				}
				if(mes == 4 || mes == 6 || mes ==9 || mes == 11 ) {
					cbDia.setModel(new DefaultComboBoxModel<>( new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31}));
				}
			}
		});
		cbDia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fin.set(((Integer) cbAnio.getSelectedItem()),((Integer) cbMes.getSelectedItem()),((Integer) cbDia.getSelectedItem()));
				if(fin.after(fechaActual)) {
					cbDia.setEnabled(false);
					cbMes.setEnabled(false);
					cbAnio.setEnabled(false);
				}
				
			}
		});
		cbAnio.setModel(modeloAnio);
		for (int i = 0; i < 5; i++) {
			int anio = Integer.parseInt(sdfY.format(fechaActual.getTime()))+i;
			modeloAnio.addElement(anio);
		}
		btnAceptar.setActionCommand("aceptar");
		btnAceptar.addActionListener(this);
		btnLimpiar.setActionCommand("limpiar");
		btnLimpiar.addActionListener(this);
		grupo.add(rbSi);
		grupo.add(rbnO);
		window.add(lblCodigo);
		window.add(txtCodigo);
		window.add(lblFin);
		window.add(cbAnio);
		window.add(lblnada);
		window.add(cbMes);
		window.add(lblnada);
		window.add(cbDia);
		window.add(lblProducto);
		window.add(txtProducto);
		window.add(lblCantidad);
		window.add(txtCantidad);
		window.add(lblUrgente);
		window.add(rbSi);
		window.add(rbnO);
		window.add(btnAceptar);
		window.add(mensaje0);
		window.add(mensaje1);
		window.add(mensaje2);
		window.add(mensaje3);
		window.add(mensaje4);
		window.add(lblComentario);
		window.add(txtCodigo);
		
		
		txtCodigo.setEditable(false);
		window.setSize(500, 800);
		window.setLayout(new FlowLayout());
		window.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("aceptar")) {
			try {
				conexion = new MyDataAcces();
			} catch (InstantiationException | IllegalAccessException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			producto prod = new producto();
			ArrayList<producto> productos = new ArrayList<>();
			
			boolean finOk=false,urgenciaOk=false,cantidadOk=false,codigoOk=false;
			if(cbDia.getSelectedItem()== null) {
				mensaje0.setText("falta seleccionar fecha fin");
			}else {
				
				fin.set(((Integer) cbAnio.getSelectedItem()),((Integer) cbMes.getSelectedItem()),((Integer) cbDia.getSelectedItem()));
				if(fechaActual.before(fin)) {
					mensaje0.setText("");
					finOk=true;
				}else {
					mensaje0.setText("el fin debe ser posterior a la fecha actual");
				
				}
				
			}
			if(rbnO.isSelected()==true) {
				mensaje1.setText("");
				urgenciaOk=true;
			}else {
				if(rbSi.isSelected()==true) {
					mensaje1.setText("");
					urgenciaOk=true;
				}else {
					
					mensaje1.setText("falta seleccionar urgencia");
				}
			}
			if(lblCantidad.getText()!=null) {
				try {
					int cant = Integer.parseInt(txtCantidad.getText());
					if(cant>0) {
						mensaje2.setText("");
						cantidadOk=true;
					}else {
						mensaje2.setText("la cantidad debe ser mayor a 0");
					}
				}catch (NumberFormatException ee) {
					mensaje2.setText("la cantidad debe ser numerica");
				}
			}else {
				mensaje2.setText("FALTA CANTIDAD. ");
			}
			if(lblCodigo.getText()!=null) {
				try {
					int cant = Integer.parseInt(txtCantidad.getText());
					if(cant>0) {
						ResultSet rsProd = conexion.GetQuery("select * from producto where idProducto="+cant);
						boolean enc = false;
						while(rsProd.next()) {
							
							prod.setCodigo(rsProd.getInt("idProducto"));
							enc = true;
							
						}
						if(enc ==true) {
							codigoOk=true;
							mensaje3.setText("");
						}else {
							mensaje3.setText("producto inexistente");
						}
						
					}else {
						mensaje3.setText("el codigo debe ser mayor a 0");
					}
				}catch (NumberFormatException | SQLException ee) {
					mensaje3.setText("el codigo debe ser numerica");
				}
			}else {
				mensaje3.setText("FALTA CODIGO. ");
			}
			System.out.println(cantidadOk+" "+codigoOk+" "+finOk+" "+urgenciaOk );
			if(cantidadOk==true && codigoOk == true && finOk== true && urgenciaOk == true) {
				
				ordenTrabajo.setDescripcion(txtComentario.getText());
				ordenTrabajo.setEstado("sin asignar");
				ordenTrabajo.setFinEstimado(fin);
				ordenTrabajo.setProducto(prod);
				ordenTrabajo.setInicio(fechaActual);
				ordenTrabajo.setCantidad(Integer.parseInt(txtCantidad.getText()));
				if(rbnO.isSelected()) {
					ordenTrabajo.setUrgente(false);
				}else {
					ordenTrabajo.setUrgente(true);
				}
				
				try {
					Date inicio = new Date(fechaActual.get(Calendar.YEAR), fechaActual.get(Calendar.MONTH), fechaActual.get(Calendar.DATE));
					Date fin = new Date(ordenTrabajo.getFinEstimado().get(Calendar.YEAR), ordenTrabajo.getFinEstimado().get(Calendar.MONTH), ordenTrabajo.getFinEstimado().get(Calendar.DATE));
					java.sql.CallableStatement call = conexion.conn.prepareCall("Call insertOT(?,?,?,?,?,?,?,?)");
					call.setString(1, ordenTrabajo.getIdOT());
					call.setString(2,ordenTrabajo.getDescripcion());
					call.setString(3, ordenTrabajo.getEstado());
					call.setInt(4, ordenTrabajo.getProducto().getCodigo());
					call.setInt(5, ordenTrabajo.getCantidad());
					call.setDate(6, inicio);
					call.setDate(7, fin);
					call.setString(8,String.valueOf( ordenTrabajo.isUrgente()));
					call.execute();
					JOptionPane.showMessageDialog(null, "guardado exitoso");
					asignarOT  asig= new asignarOT(emple,ordenTrabajo);
				} catch (SQLException e1) {
					System.out.println("eeror");
					e1.printStackTrace();
				}
			
			}
		}
		if(e.getSource().equals("limpiar")) {
			txtCantidad.setText("");
			txtCodigo.setText("");
			txtComentario.setText("");
			txtProducto.setText("");
			cbDia.setSelectedIndex(0);
			rbnO.setSelected(false);
			rbSi.setSelected(false);
			cbDia.setEditable(true);
			cbMes.setEnabled(true);
			cbAnio.setEnabled(true);
		}
		
	}
}
