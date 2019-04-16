// Savvas Giortsis Sagi2536 (19980111-4852)

package application;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreateSmycke extends Alert {
	private TextField name, amtStones;
	private CheckBox material;
	
	GridPane root = new GridPane();

	public CreateSmycke() {
		super(AlertType.CONFIRMATION);
		createSmyckeWindow();

		getDialogPane().setContent(root);
		setHeaderText(null);
		root.setVgap(5);
	}

	private void createSmyckeWindow() {
		setTitle("Nytt Smycke");
		name = new TextField();
		amtStones = new TextField();
		material = new CheckBox();
		
		name.setPromptText("Namn");
		amtStones.setPromptText("Antal stenar");

		root.addRow(0, new Label("Namn: "), name);
		root.addRow(1, new Label("AntalStenar: "), amtStones);
		root.addRow(2, new Label("Av guld: "), material);
	}
	
	public String getName() {
		return name.getText();
	}
	
	public String getAmtStones() {
		return amtStones.getText();
	}
	
	public boolean getMaterial() {
		return material.isSelected();
	}
}
