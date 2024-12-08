package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import conexiondb.ConexionMySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class departamentos {
	private String codDepto;
	
	private String codDirector;

	public departamentos(String codDepto, String codDirector) {
		super();
		this.codDepto = codDepto;
		this.codDirector = codDirector;
	}

	public departamentos() {
		super();
	}

	public String getCodDepto() {
		return codDepto;
	}

	public String getCodDirector() {
		return codDirector;
	}

	public void setCodDepto(String codDepto) {
		this.codDepto = codDepto;
	}

	public void setCodDirector(String codDirector) {
		this.codDirector = codDirector;
	}
	
	public ObservableList<departamentos> getDepartamentos() {
		ObservableList<departamentos> obs = FXCollections.observableArrayList();
        try {

            // Abro la conexion
            ConexionMySQL conexion = new ConexionMySQL("localhost", "empleadoss_departamentoss", "root", "");
          

            // realizo la consulta
            conexion.ejecutarConsulta("select * from departamentos;");

            ResultSet rs = conexion.getResultSet();
            
            // recorro los resultados
            while(rs.next()){
                
                // Cojo los datos
            	String codDepto = rs.getString("codDepto");
            	
            	String codDirector = rs.getString("codDirector");
                
                // Creo el cliente
            	departamentos c = new departamentos(codDepto, codDirector);
                
                obs.add(c);
                
                
            }
            
            // Cierro la conexion
            conexion.cerrarConexion();
            

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obs;
	}
	
}

