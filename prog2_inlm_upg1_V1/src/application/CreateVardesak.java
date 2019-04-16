package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreateVardesak extends Alert{
	TextField name, property1, property2;
	CheckBox bool;
	Vardesaker newObject = null;
	
	GridPane root = new GridPane();

	public CreateVardesak(String object) {
		super(AlertType.CONFIRMATION);

		if (object.equals("Apparat"))
			createApparat();
		else if (object.equals("Smycke"))
			createSmycke();
		else if (object.equals("Aktie"))
			createAktie();

		getDialogPane().setContent(root);
		setHeaderText(null);
		root.setVgap(5);
		
		Optional<ButtonType> answer = showAndWait();
		if(answer.isPresent() && answer.get() == ButtonType.OK && name != null) {
			if (object.equals("Apparat"))
				newObject = new Apparater(name.getText(), Integer.parseInt(property1.getText()), Integer.parseInt(property2.getText()));
			else if (object.equals("Smycke"))
				newObject = new Smycken(name.getText(), bool.isSelected() ? true : false, Integer.parseInt(property1.getText()));
			else if (object.equals("Aktie"))
				newObject = new Aktier(name.getText(), Integer.parseInt(property1.getText()), Integer.parseInt(property2.getText()));
		}
	}

	private void createApparat() {
		setTitle("Ny Apparat");
		name = new TextField();
		property1 = new TextField();
		property2 = new TextField();
		
		root.addRow(0, new Label("Namn: "), name);
		root.addRow(1, new Label("Pris: "), property1);
		root.addRow(2, new Label("Skick: "), property2);

	}

	private void createSmycke() {
		setTitle("Nytt Smycke");
		name = new TextField();
		property1 = new TextField();
		bool = new CheckBox();
		
		root.addRow(0, new Label("Namn: "), name);
		root.addRow(1, new Label("AntalStenar: "), property1);
		root.addRow(2, new Label("Material: "), bool);
		
	}

	private void createAktie() {
		setTitle("Ny Aktie");
		name = new TextField();
		property1 = new TextField();
		property2 = new TextField();
		
		root.addRow(0, new Label("Namn: "), name);
		root.addRow(1, new Label("Antal: "), property1);
		root.addRow(2, new Label("Pris: "), property2);
	}
	
	public Vardesaker getObject() {
		return newObject;
	}

}
