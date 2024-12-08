package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class TercEscController {
	@FXML
	private Button ButtonSalir;
	
	@FXML
	private TableView<Employee> TablaT;
	
	@FXML
    private TableColumn<Employee, String> Sexo;

    @FXML
    private TableColumn<Employee, String> cargo;

    @FXML
    private TableColumn<Employee, Integer> codDep;

    @FXML
    private TableColumn<Employee, Integer> comision;

    @FXML
    private TableColumn<Employee, LocalDate> fini;

    @FXML
    private TableColumn<Employee, LocalDate> fnac;

    @FXML
    private TableColumn<Employee, String> id;

    @FXML
    private TableColumn<Employee, String> jefe;

    @FXML
    private TableColumn<Employee, String> nombre;

    @FXML
    private TableColumn<Employee, Integer> salario;
    
    @FXML
    private ComboBox<Employee> CmbBoxEmp;
        
    public void initialize() {
		
		// Asocio los atributos de servicio con las columnas de la tabla
    	id.setCellValueFactory(new PropertyValueFactory("iD"));
        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        Sexo.setCellValueFactory(new PropertyValueFactory("Sex"));
        fnac.setCellValueFactory(new PropertyValueFactory("FechaNacimiento"));
        fini.setCellValueFactory(new PropertyValueFactory("FechaInicio"));
        salario.setCellValueFactory(new PropertyValueFactory("salario"));
        comision.setCellValueFactory(new PropertyValueFactory("comision"));
        cargo.setCellValueFactory(new PropertyValueFactory("Cargo"));
        jefe.setCellValueFactory(new PropertyValueFactory("BossID"));
        codDep.setCellValueFactory(new PropertyValueFactory("DeptID"));

        // Cargo los servicios en la tabla
    	Employee s = new Employee();
        ObservableList<Employee> items = s.getEmployee();
        TablaT.setItems(items);
        this.TablaT.refresh();
        CmbBoxEmp.setItems(items);
        CmbBoxEmp.setConverter(new StringConverter<Employee>() {
            public String toString(Employee employee) {
                return employee != null ? employee.getNombre() : "";
            }

            public Employee fromString(String string) {
                return null; // Not needed
            }
        });
      
	}
    
    @FXML
    public void ClickcmbEmployees(ActionEvent event) {
    	Employee selectedEmployee = CmbBoxEmp.getSelectionModel().getSelectedItem();

        // Update the TableView with the selected employee
        if (selectedEmployee != null) {
            TablaT.setItems(FXCollections.observableArrayList(selectedEmployee));
        }
    }

	// Event Listener on Button[#ButtonSalir].onAction
	@FXML
	public void Salir(ActionEvent event) {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("PrimEsc.fxml"));
	        Parent root = loader.load(); // Load the next scene

	        Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();

	        // Close the current window
	        Stage currentStage = (Stage) ButtonSalir.getScene().getWindow();
	        currentStage.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
