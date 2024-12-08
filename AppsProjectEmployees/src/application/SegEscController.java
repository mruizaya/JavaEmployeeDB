package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.DatePicker;

public class SegEscController {
	@FXML
	private Button ButtonGrabar;
	@FXML
	private TextField TextField1;
	@FXML
	private TextField TextField2;
	@FXML
	private TextField TextField3;
	@FXML
	private TextField TextField4;
	@FXML
	private TextField TextField5;
	@FXML
	private TextField TextField6;
	@FXML
	private DatePicker DatePicker2;
	@FXML
	private DatePicker DatePicker1;
	@FXML
	private ComboBox<departamentos> codDept;
	@FXML
	private TextField jefID;
	@FXML
	private Button ButtonSalir;

    public void initialize() {
		
		// Asocio los atributos de servicio con las columnas de la tabla
    	
        // Cargo los servicios en la tabla
    	departamentos s = new departamentos();
        ObservableList<departamentos> items = s.getDepartamentos();
        codDept.setItems(items);
        codDept.setConverter(new StringConverter<departamentos>() {
            public String toString(departamentos departamentos) {
                return departamentos != null ? departamentos.getCodDepto() : "";
            }

            public departamentos fromString(String string) {
                return null; // Not needed
            }
        });
      
	}
	
    @FXML
    public void ClickcmbDept(ActionEvent event) {
    	departamentos selectedEmployee = codDept.getSelectionModel().getSelectedItem();

        // Update the jefID textbox
    	jefID.setText(selectedEmployee.getCodDirector());
    }
    
	// Event Listener on Button[#ButtonGrabar].onAction
	@FXML
	public void GrabarEmp(ActionEvent event) {
		// TODO Autogenerated
		//Para Comprobar que todos los campos esten llenos
			if (jefID.getText().isEmpty() || TextField1.getText().isEmpty() || TextField2.getText().isEmpty() || TextField3.getText().isEmpty() || TextField4.getText().isEmpty() || TextField5.getText().isEmpty() || TextField6.getText().isEmpty() || DatePicker1.getValue()==null || DatePicker2.getValue()==null) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText("Campos Insuficientes");
				alert.setTitle("Error");
				alert.setContentText("Todos los campos no estan llenos");
				alert.showAndWait();
			}
		//Aqui acuerdate de poner la alerta de si ingesa un string donde debe estar un Int
			if (DatePicker1.getValue().isAfter(DatePicker2.getValue())) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Fecha Incorrecta");
				alert.setTitle("Error");
				alert.setContentText("Fecha de nacimiento no puede ser despues de fecha de incorporacion");
				alert.showAndWait();
			}
			if ((TextField3.getText().length() > 1) || !((TextField3.getText().equals("M")) || (TextField3.getText().equals("F")))) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Genero equivocado");
				alert.setTitle("Error");
				alert.setContentText("Debe ser M o F");
				alert.showAndWait();
			}
			if (!(TextField4.getText().isEmpty() || TextField5.getText().isEmpty()) && !(isInteger(TextField4.getText()) && isInteger(TextField5.getText()))) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Input Error");
				alert.setTitle("Error");
				alert.setContentText("Los valores de salario y comision deben ser numeros enteros");
				alert.showAndWait();
			}
			departamentos selectedDep = codDept.getValue();
			Employee s = new Employee(TextField1.getText(), TextField2.getText(), TextField3.getText().charAt(0), DatePicker1.getValue(), DatePicker2.getValue(), Integer.parseInt(TextField4.getText()), Integer.parseInt(TextField5.getText()), TextField6.getText(), jefID.getText(), Integer.parseInt(selectedDep.getCodDepto()));//dalta combo y ultimo textbox
            
            // lo inserto
            
            
            if (s.setEmployee()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Exito");
                alert.setContentText("Se inserto correctamente");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("No se inserto correctamente");
                alert.setTitle("Error");
                alert.setContentText("El empleado ya existe");
                alert.showAndWait();
            }
	}
	
	private boolean isInteger(String input) {
        try {
            Integer.parseInt(input); // Attempt to parse the input
            return true; // It's an integer
        } catch (NumberFormatException e) {
            return false; // Not an integer
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