// Savvas Giortsis Sagi2536

package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreateApparat extends Alert {
	private TextField name, price, condition;
	GridPane root = new GridPane();

	public CreateApparat() {
		super(AlertType.CONFIRMATION);
		createApparatWindow();

		getDialogPane().setContent(root);
		setHeaderText(null);
		root.setVgap(5);
	}

	private void createApparatWindow() {
		setTitle("Ny Apparat");
		name = new TextField();
		price = new TextField();
		condition = new TextField();
		
		name.setPromptText("Namn");
		price.setPromptText("Pris");
		condition.setPromptText("Skick (1-10)");

		root.addRow(0, new Label("Namn: "), name);
		root.addRow(1, new Label("Pris: "), price);
		root.addRow(2, new Label("Skick: "), condition);
	}
	
	public String getName() {
		return name.getText();
	}

	public String getCondition() {
		return condition.getText();
	}

	public String getPrice() {
		return price.getText();
	}
}
