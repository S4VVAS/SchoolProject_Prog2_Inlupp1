// Savvas Giortsis Sagi2536 (19980111-4852)

package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreateAktie extends Alert {
	private TextField name, amount, price;
	GridPane root = new GridPane();

	public CreateAktie() {
		super(AlertType.CONFIRMATION);
		createAktieWindow();

		getDialogPane().setContent(root);
		setHeaderText(null);
		root.setVgap(5);
	}

	private void createAktieWindow() {
		setTitle("Ny Aktie");
		name = new TextField();
		amount = new TextField();
		price = new TextField();

		name.setPromptText("Namn");
		amount.setPromptText("Antal");
		price.setPromptText("Pris");

		root.addRow(0, new Label("Namn: "), name);
		root.addRow(1, new Label("Antal: "), amount);
		root.addRow(2, new Label("Pris: "), price);
	}
	
	public String getName() {
		return name.getText();
	}

	public String getAmount() {
		return amount.getText();
	}

	public String getPrice() {
		return price.getText();
	}
}
