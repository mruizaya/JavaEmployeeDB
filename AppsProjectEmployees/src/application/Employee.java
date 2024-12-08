package application;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import conexiondb.ConexionMySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Employee {
	private String ID;
	private String nombre;
	private char Sex;
	private LocalDate FechaNacimiento;
	private LocalDate FechaInicio;
	private int Salario;
	private int Comision;
	private String Cargo;
	private String BossID;
	private int DeptID;
	
	public Employee(String iD, String nombre, char sex, LocalDate fechaNacimiento, LocalDate fechaInicio,
			int salario, int comision, String cargo, String bossID, int deptID) {
		super();
		this.ID = iD;
		this.nombre = nombre;
		this.Sex = sex;
		this.FechaNacimiento = fechaNacimiento;
		this.FechaInicio = fechaInicio;
		this.Salario = salario;
		this.Comision = comision;
		this.Cargo = cargo;
		this.BossID = bossID;
		this.DeptID = deptID;
	}
	
	public Employee() {
		super();
	}

	public String getID() {
		return ID;
	}
	public String getNombre() {
		return nombre;
	}
	public char getSex() {
		return Sex;
	}
	public LocalDate getFechaNacimiento() {
		return FechaNacimiento;
	}
	public LocalDate getFechaInicio() {
		return FechaInicio;
	}
	public int getSalario() {
		return Salario;
	}
	public int getComision() {
		return Comision;
	}
	public String getCargo() {
		return Cargo;
	}
	public String getBossID() {
		return BossID;
	}
	public int getDeptID() {
		return DeptID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setSex(char sex) {
		Sex = sex;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		FechaInicio = fechaInicio;
	}
	public void setSalario(int salario) {
		Salario = salario;
	}
	public void setComision(int comision) {
		Comision = comision;
	}
	public void setCargo(String cargo) {
		Cargo = cargo;
	}
	public void setBossID(String bossID) {
		BossID = bossID;
	}
	public void setDeptID(int deptID) {
		DeptID = deptID;
	}
	
	//Los metodos a continuacion son para conectar a la base de datos mySQL
	public ObservableList<Employee> getEmployee() {
		ObservableList<Employee> obs = FXCollections.observableArrayList();
        try {

            // Abro la conexion
            System.out.println("getEmployee();");

            ConexionMySQL conexion = new ConexionMySQL("localhost", "empleadoss_departamentoss", "root", "");
            System.out.println("Ejecutando getEmployee();");
          

            // realizo la consulta
            conexion.ejecutarConsulta("select * from empleados;");

            ResultSet rs = conexion.getResultSet();
            
            // recorro los resultados
            while(rs.next()){
                
                // Cojo los datos
                String ID = rs.getString("nDIEmp");
                String nombre = rs.getString("nomEmp");
                char Sex = rs.getString("sexEmp").charAt(0);
            	LocalDate FechaNacimiento = rs.getDate("fecNac").toLocalDate();
            	LocalDate FechaInicio = rs.getDate("fecIncorporacion").toLocalDate();
            	int Salario = rs.getInt("salEmp");
            	int Comision = rs.getInt("comisionE");
            	String Cargo = rs.getString("cargoE");
            	String BossID = rs.getString("jefeID");
            	int DeptID = rs.getInt("codDepto");
                
                // Creo el cliente
                Employee c = new Employee(ID, nombre, Sex, FechaNacimiento, FechaInicio, Salario, Comision, Cargo, BossID, DeptID);
                
                obs.add(c);
                
                
            }
            
            // Cierro la conexion
            conexion.cerrarConexion();
            

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obs;
	}
	
	public boolean setEmployee() {

        try {
            
            // Abro la conexion
            ConexionMySQL conexion = new ConexionMySQL("localhost", "empleadoss_departamentoss", "root", "");

            // Sentencia para introducir un servicio
            String SQL = "INSERT INTO empleados (nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, comisionE, cargoE, jefeID, codDepto) "
                    + "values("
                    + "'" + getID() + "', "
                    + "'" + getNombre() + "', "
                    + "'" + getSex() + "', "
                    + "'" + getFechaNacimiento().toString() + "', "
                    + "'" + getFechaInicio().toString() + "', "
                    + "'" + getSalario() + "', "
                    + "'" + getComision() + "', "
                    + "'" + getCargo() + "', "
                    + "'" + getBossID() + "',"
                    + getDeptID()
                    + " )";

            // Devuelvo el numero de filas afectadas
            int filas = conexion.ejecutarInstruccion(SQL);

            conexion.cerrarConexion();
            
            // Si deuvelve mas de 0, es que hemos insertado registros
            if (filas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
