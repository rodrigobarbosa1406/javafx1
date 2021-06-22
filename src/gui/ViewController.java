package gui;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class ViewController {
	
	@FXML
	private Button btTeste;
	
	@FXML
	public void onBtTestAction() {
		Alerts.showAlert("Título do alerta", "Cabeçalho do alerta", "Alô!", AlertType.INFORMATION);
	}
}
