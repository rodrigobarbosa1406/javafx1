package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Pessoa;

public class ViewController implements Initializable {

	@FXML
	private Button btTeste;

	@FXML
	private TextField txtNumero1;

	@FXML
	private TextField txtNumero2;

	@FXML
	private Label labelResultado;

	@FXML
	private Button btSoma;

	@FXML
	private Button btTudo;
	
	@FXML
	private ComboBox<Pessoa> comboBoxPessoa;

	private ObservableList<Pessoa> obsList;

	@FXML
	public void onBtTestAction() {
		// System.out.println("Click");
		Alerts.showAlert("Título do alerta", "Cabeçalho do alerta", "Alô!", AlertType.INFORMATION);
	}

	@FXML
	public void onBtSomaAction() {
		try {
			Locale.setDefault(Locale.US);
			double numero1 = Double.parseDouble(txtNumero1.getText());
			double numero2 = Double.parseDouble(txtNumero2.getText());

			double soma = numero1 + numero2;

			labelResultado.setText(String.format("%.2f", soma));
		} catch (NumberFormatException e) {
			Alerts.showAlert("Erro", "Parse error", e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	public void onComboBoxPessoaAction() {
		Pessoa pessoa = comboBoxPessoa.getSelectionModel().getSelectedItem();
		System.out.println(pessoa);
	}
	
	@FXML
	public void onBtTudoAction() {
		for (Pessoa pessoa : comboBoxPessoa.getItems()) {
			System.out.println(pessoa);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldDouble(txtNumero1);
		Constraints.setTextFieldDouble(txtNumero2);

		Constraints.setTextFieldMaxLength(txtNumero1, 12);
		Constraints.setTextFieldMaxLength(txtNumero2, 12);

		List<Pessoa> lstPessoa = new ArrayList<>();
		lstPessoa.add(new Pessoa(1, "Maria", "maria@gmail.com"));
		lstPessoa.add(new Pessoa(2, "Alex", "alex@gmail.com"));
		lstPessoa.add(new Pessoa(3, "Bob", "bob@gmail.com"));

		obsList = FXCollections.observableArrayList(lstPessoa);
		comboBoxPessoa.setItems(obsList);

		Callback<ListView<Pessoa>, ListCell<Pessoa>> factory = lv -> new ListCell<Pessoa>() {
			@Override
			protected void updateItem(Pessoa item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		comboBoxPessoa.setCellFactory(factory);
		comboBoxPessoa.setButtonCell(factory.call(null));
	}
}
