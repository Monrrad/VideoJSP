package co.empresa.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
	private Connection con = null;
	private PreparedStatement preparedStatement;

	private static final String url = "jdbc:mysql://localhost:3306/";
	private static final String dbName = "sistema";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String userName = "root";
	private static final String password = "";

	public Conexion (String driver, String url, String dbName, String userName, String passwprd) {
		
		try {
			Class.forName(driver).newInstance();
			con = (Connection)DriverManager.getConnection(url+dbName,userName,password);
					
		}catch (InstantiationException | IllegalAccessException 
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void cerrarConexion() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet query() throws SQLException {
		ResultSet res = preparedStatement.executeQuery();
		return res;
	}
	
	public int execute() throws SQLException {
        if (preparedStatement != null) {
            return preparedStatement.executeUpdate();
        } else {
            throw new SQLException("La declaración preparada es nula");
        }
    }

    public Connection getCon() {
        return con;
    }

    public PreparedStatement setPreparedStatement(String sql) throws SQLException {
        if (con != null) {
            preparedStatement = con.prepareStatement(sql);
            return preparedStatement;
        } else {
            throw new SQLException("La conexión es nula");
        }
    }
}

