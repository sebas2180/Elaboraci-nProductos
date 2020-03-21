package clases;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class MyDataAcces {

	private String _usuario="root";
	private String _pwd="1234";
	private static String bd="guiaFinal2018";
	static String _url = "jdbc:mysql://localhost/"+bd;
	public Connection conn = null;
	
	
    public MyDataAcces() throws InstantiationException, IllegalAccessException{
	try{
	
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(_url,_usuario,_pwd);
		if(conn!=null){
			System.out.println("Conexion a base de datos"+_url+"... ok");
		}
		
	}catch(SQLException ex){
		System.out.println("Hubo un problema al intenter conectarse a la BD");
	}catch(ClassNotFoundException exx){
		System.out.println(exx);
	}
    }

	public ResultSet GetQuery(String _query){
		Statement state =null;
		ResultSet resultado = null;
		try{
		state=(Statement)conn.createStatement();
		resultado = ((java.sql.Statement) state).executeQuery(_query);
		}catch(SQLException e){
			
		}
		return resultado;
		
	}

	public void setQuery(String _query){
		Statement state = null;
		try{
			state=(Statement)conn.createStatement();
			 ((java.sql.Statement) state).executeQuery(_query);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public boolean ejecutar(String sql) {
        try {
            Statement sentencia = (Statement) conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ((java.sql.Statement) sentencia).executeUpdate(sql);
           sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }        return true;
    }

}