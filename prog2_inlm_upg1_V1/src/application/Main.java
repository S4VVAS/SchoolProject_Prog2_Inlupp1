// Savvas Giortsis Sagi2536 (19980111-4852)

package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {

	ArrayList<Vardesaker> items = new ArrayList<Vardesaker>();
	RadioButton sortByName, sortByValue;
	TextArea mainWindow;
	Button show, crash;
	ComboBox<String> comboBox;

	private void updateTextArea() {
		mainWindow.clear();
		sortVardeSaker();

		for (int i = 0; i < items.size(); i++)
			mainWindow.appendText(items.get(i).toString() + "\n");
	}

	private void sortVardeSaker() {
		if (sortByName.isSelected()) {
			Comparator<Vardesaker> nameSort = (x, y) -> x.getName().compareTo(y.getName());
			Collections.sort(items, nameSort);

		} else if (sortByValue.isSelected()) {
			Comparator<Vardesaker> valueSort = (y, x) -> Double.compare(x.getValue(), y.getValue());
			Collections.sort(items, valueSort);
		}
	}

	@Override
	public void start(Stage stage) {

		stage.setTitle("Sakregister");
		BorderPane root = new BorderPane();
		VBox right = new VBox();
		HBox bottom = new HBox();
		Insets inset = new Insets(10);

		// TOP
		root.setTop(new Label("Värdesaker"));
		BorderPane.setAlignment(root.getTop(), Pos.CENTER);

		// CENTER
		mainWindow = new TextArea("Welcome");
		mainWindow.setEditable(false);
		mainWindow.setPrefSize(350, 200);
		mainWindow.setPadding(inset);
		root.setCenter(mainWindow);

		// RIGHT
		sortByName = new RadioButton("Namn");
		sortByValue = new RadioButton("Värde");
		ToggleGroup group = new ToggleGroup();

		sortByName.setToggleGroup(group);
		sortByValue.setToggleGroup(group);

		sortByName.setOnAction(e -> updateTextArea());
		sortByValue.setOnAction(e -> updateTextArea());

		sortByName.setSelected(true);

		right.getChildren().addAll(new Label("Sortering"), sortByName, sortByValue);
		right.setSpacing(5);
		right.setPadding(inset);
		root.setRight(right);

		// BOTTOM
		comboBox = new ComboBox<>();
		comboBox.getItems().addAll("Smycke", "Aktie", "Apparat");
		comboBox.setPromptText("Välj värdesak:");
		comboBox.setOnAction(new CreateVardesak());

		show = new Button("Visa");
		show.setOnAction(e -> updateTextArea());

		crash = new Button("Börskrasch");
		crash.setOnAction(new CrashAll());

		bottom.getChildren().addAll(new Label("Ny: "), comboBox, show, crash);
		bottom.setSpacing(2);
		bottom.setPadding(inset);
		bottom.setAlignment(Pos.CENTER);
		root.setBottom(bottom);

		// SETTING SCENE AND STAGE
		Scene s = new Scene(root);
		stage.setScene(s);
		stage.setResizable(true);
		stage.show();

	}

	class CrashAll implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == crash)
				for (Vardesaker v : items)
					if (v instanceof Aktier)
						((Aktier) v).crash();

			updateTextArea();
		}
	}

	class CreateVardesak implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			String selection = comboBox.getSelectionModel().getSelectedItem();

			if (comboBox.getValue() != comboBox.getPromptText()) {
				try {
					if (selection.equals("Smycke")) {
						createSmycke();
					} else if (selection.equals("Aktie")) {
						createAktie();
					} else if (selection.equals("Apparat")) {
						createApparat();
					}
				} catch (Exception e) {
				}
				Platform.runLater(() -> comboBox.getSelectionModel().select(comboBox.getPromptText()));
			}
		}

		private void createSmycke() {
			CreateSmycke createSmycke = new CreateSmycke();
			Optional<ButtonType> answer = createSmycke.showAndWait();
			if (answer.isPresent() && answer.get() == ButtonType.OK) {
				if (!createSmycke.getName().trim().isEmpty()) {
					try {
						if (Integer.parseInt(createSmycke.getAmtStones()) > -1) {
							items.add(new Smycken(createSmycke.getName(), createSmycke.getMaterial(),
									Integer.parseInt(createSmycke.getAmtStones())));
						} else
							falseInputError("Fält får ej understiga 0!");
					} catch (NumberFormatException e) {
						falseInputError("Felaktig inmatning!");
					}
				}
				if (createSmycke.getName().trim().isEmpty())
					falseInputError("Namn får ej vara tomt!");
			}
		}

		private void createApparat() {
			CreateApparat createApparat = new CreateApparat();
			Optional<ButtonType> answer = createApparat.showAndWait();
			if (answer.isPresent() && answer.get() == ButtonType.OK) {
				if (!createApparat.getName().trim().isEmpty()) {
					try {
						if (Integer.parseInt(createApparat.getPrice()) > -1) {
							items.add(new Apparater(createApparat.getName(), Integer.parseInt(createApparat.getPrice()),
									Integer.parseInt(createApparat.getCondition())));
							if (Integer.parseInt(createApparat.getCondition()) > 10)
								falseInputError("Skicket får inte översiga 10!\nSkicket har automatiskt sätts till 10");
							else if (Integer.parseInt(createApparat.getCondition()) < 1)
								falseInputError("Skicket får inte understiga 1!\nSkicket har automatiskt sätts till 1");
						} else
							falseInputError("Fält får ej understiga 0!");
					} catch (NumberFormatException e) {
						falseInputError("Felaktig inmatning!");
					}
				} else if (createApparat.getName().trim().isEmpty())
					falseInputError("Namn får ej vara tomt!");
			}
		}

		private void createAktie() {
			CreateAktie createAktie = new CreateAktie();
			Optional<ButtonType> answer = createAktie.showAndWait();
			if (answer.isPresent() && answer.get() == ButtonType.OK) {
				if (!createAktie.getName().trim().isEmpty()) {
					try {
						if (Integer.parseInt(createAktie.getPrice()) > -1
								&& Integer.parseInt(createAktie.getAmount()) > -1) {
							items.add(new Aktier(createAktie.getName(), Integer.parseInt(createAktie.getAmount()),
									Integer.parseInt(createAktie.getPrice())));
						} else
							falseInputError("Fält får ej understiga 0!");
					} catch (NumberFormatException e) {
						falseInputError("Felaktig inmatning!");
					}
					System.out.print("ok");
				} else if (createAktie.getName().trim().isEmpty())
					falseInputError("Namn får ej vara tomt!");
			}
		}

		private void falseInputError(String msg) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(msg);
			alert.setTitle("Fel!");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
