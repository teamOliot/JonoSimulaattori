package view;

import java.text.DecimalFormat;
import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class SimulaattorinGUI extends Application implements ISimulaattorinUI {

	// Kontrollerin esittely (tarvitaan käyttöliittymässä)
	private IKontrolleriVtoM kontrolleri;

	// Käyttöliittymäkomponentit:
	private Button tarkasteleRaporttejaButton;
	private Button infoButton;

	private Label aikaLabel;
	private TextField aika;

	private Label viiveLabel;
	private TextField viive;

	private Label xAsiakkaidenParamLabel;
	private TextField xAsiakkaidenParam;

	private Label yAsiakkaidenParamLabel;
	private TextField yAsiakkaidenParam;

	private Button kaynnistaButton;
	// Tehdäänkö lopetaButton?
	private Button nopeutaButton;
	private Button hidastaButton;

	private Label pp1Label;
	private Label pp2Label;
	private Label pp3Label;
	private Label pp4Label;

	private IVisualisointi pp1Naytto;
	private IVisualisointi pp2Naytto;
	private IVisualisointi pp3Naytto;
	private IVisualisointi pp4Naytto;

	private Label xSaapuneetLabel;
	private Label xSaapuneetTulos;

	private Label ySaapuneetLabel;
	private Label ySaapuneetTulos;

	private Label yhtSaapuneetLabel;
	private Label yhtSaapuneetTulos;

	private Label xLapiSysteeminLabel;
	private Label xLapiSysteeminTulos;

	private Label yLapiSysteeminLabel;
	private Label yLapiSysteeminTulos;

	private Label yhtLapiSysteeminLabel;
	private Label yhtLapiSysteeminTulos;

	private Label simuKokonaisaikaLabel;
	private Label simuKokonaisaikaTulos;

	@Override
	public void init() {

		Trace.setTraceLevel(Level.INFO);

		kontrolleri = new Kontrolleri(this);
	}

	@Override
	public void start(Stage primaryStage) {
		// Käyttöliittymän rakentaminen
		try {

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					Platform.exit();
					System.exit(0);
				}
			});

			primaryStage.setTitle("Simulaattori");

			BorderPane border = new BorderPane(); // Pohjana border

			Scene scene = new Scene(border, 1000, 750);
			primaryStage.setScene(scene);
			primaryStage.show();

			HBox hBox = new HBox();
			hBox.setPadding(new Insets(20, 10, 0, 10)); // marginaalit ylä, oikea, ala, vasen
			hBox.setSpacing(10);
			hBox.setAlignment(Pos.CENTER);
			border.setTop(hBox); // Borderin yläreunassa hBox

			tarkasteleRaporttejaButton = new Button("Tarkastele simulointiraportteja");
			tarkasteleRaporttejaButton.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			tarkasteleRaporttejaButton.setPrefWidth(260);

			// Tarkastele simulointiraportteja -painikkeelle lisättävä vielä OnAction

			infoButton = new Button("i");
			infoButton.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));

			infoButton.setStyle("-fx-background-radius: 5em; " + "-fx-min-width: 30px; " + "-fx-min-height: 30px; "
					+ "-fx-max-width: 30px; " + "-fx-max-height: 30px;");

			infoButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.setTitle("Simulaattorin käyttöohjeet");
					alert.setHeaderText(null);
					alert.setContentText(
					"1. Syötä Simulointiaika-kenttään arvo. \n" + 
					"2. Syötä Viive-kenttään arvo. \n" + 
					"3. Syötä Parametrit X -kenttään arvo. \n" + 
					"4. Syötä Parametrit Y -kenttään arvo. \n" + 
					"5. Klikkaa Käynnistä simulointi -painiketta. \n" + 
					"6. Voit simuloinnin ollessa käynnissä nopeuttaa tai hidastaa simuloinnin viivettä. \n" + 
					"7. Voit tarkastella tallennettuja simulointiraportteja.");

					alert.showAndWait();
				}
			});

			StackPane stack = new StackPane();
			stack.getChildren().add(infoButton);
			stack.setAlignment(Pos.CENTER_RIGHT);
			StackPane.setMargin(infoButton, new Insets(0, 20, 0, 0)); // marginaalit ylä, oikea, ala, vasen

			Region tyhjaTila1 = new Region();
			HBox.setHgrow(tyhjaTila1, Priority.ALWAYS);

			Region tyhjaTila2 = new Region();
			HBox.setHgrow(tyhjaTila2, Priority.ALWAYS);

			// GUI:n ylimmällä rivillä tyhjä tila, painike keskellä, tyhjä tila ja painike oikeassa reunassa
			hBox.getChildren().addAll(tyhjaTila1, tarkasteleRaporttejaButton, tyhjaTila2, stack);

			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setVgap(10);
			grid.setHgap(5);

			border.setCenter(grid); // Borderin keskellä grid

			aikaLabel = new Label("Simulointiaika: ");
			aikaLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			aika = new TextField("");
			aika.setPromptText("Syötä arvo");
			aika.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			aika.setMaxWidth(100);

			viiveLabel = new Label("Viive: ");
			viiveLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			viive = new TextField("");
			viive.setPromptText("Syötä arvo");
			viive.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			viive.setMaxWidth(100);

			xAsiakkaidenParamLabel = new Label("Parametrit X: ");
			xAsiakkaidenParamLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xAsiakkaidenParam = new TextField("");
			xAsiakkaidenParam.setPromptText("Syötä arvo");
			xAsiakkaidenParam.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xAsiakkaidenParam.setMaxWidth(100);

			yAsiakkaidenParamLabel = new Label("Parametrit Y: ");
			yAsiakkaidenParamLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yAsiakkaidenParam = new TextField("");
			yAsiakkaidenParam.setPromptText("Syötä arvo");
			yAsiakkaidenParam.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yAsiakkaidenParam.setMaxWidth(100);

			kaynnistaButton = new Button();
			kaynnistaButton.setText("Käynnistä simulointi");
			kaynnistaButton.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			kaynnistaButton.setMaxWidth(160.0);
			GridPane.setFillWidth(kaynnistaButton, true); // Käynnistä simulointi -painike ulottuu kahden sarakkeen alueelle

			kaynnistaButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					kontrolleri.kaynnistaSimulointi();
					kaynnistaButton.setDisable(true);
				}
			});

			nopeutaButton = new Button();
			nopeutaButton.setText("Nopeuta");
			nopeutaButton.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			nopeutaButton.setPrefWidth(100);
			nopeutaButton.setOnAction(e -> kontrolleri.nopeuta());

			hidastaButton = new Button();
			hidastaButton.setText("Hidasta");
			hidastaButton.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			hidastaButton.setPrefWidth(100);
			hidastaButton.setOnAction(e -> kontrolleri.hidasta());

			pp1Label = new Label("PP1 Lipuntarkastus");
			pp1Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));

			pp2Label = new Label("PP2 Turvatarkastus");
			pp2Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));

			pp3Label = new Label("PP3 Baaritiski");
			pp3Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));

			pp4Label = new Label("PP4 WC");
			pp4Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));

			pp1Naytto = new Visualisointi(200, 260); // leveys, korkeus
			pp2Naytto = new Visualisointi(200, 260);
			pp3Naytto = new Visualisointi(200, 260);
			pp4Naytto = new Visualisointi(200, 260);

			xSaapuneetLabel = new Label("Saapuneet X:");
			xSaapuneetLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));

			xSaapuneetTulos = new Label(); // Tähän tulee näkyviin tulos simulaattoria ajettaessa
			xSaapuneetTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xSaapuneetTulos.setMaxWidth(100);

			ySaapuneetLabel = new Label("Saapuneet Y:");
			ySaapuneetLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));

			ySaapuneetTulos = new Label(); // Tähän tulee näkyviin tulos simulaattoria ajettaessa
			ySaapuneetTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			ySaapuneetTulos.setMaxWidth(100);

			yhtSaapuneetLabel = new Label("Saapuneet yhteensä:");
			yhtSaapuneetLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));

			yhtSaapuneetTulos = new Label(); // Tähän tulee näkyviin tulos simuloinnin loputtua
			yhtSaapuneetTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yhtSaapuneetTulos.setMaxWidth(100);

			// Lisää läpi systeemin menneet x ja y
			xLapiSysteeminLabel = new Label("Systeemin läpi X:");
			xLapiSysteeminLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));

			xLapiSysteeminTulos = new Label(); // Tähän tulee näkyviin tulos simulaattoria ajettaessa
			xLapiSysteeminTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xLapiSysteeminTulos.setMaxWidth(100);

			yLapiSysteeminLabel = new Label("Systeemin läpi Y:");
			yLapiSysteeminLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));

			yLapiSysteeminTulos = new Label(); // Tähän tulee näkyviin tulos simulaattoria ajettaessa
			yLapiSysteeminTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yLapiSysteeminTulos.setMaxWidth(100);

			yhtLapiSysteeminLabel = new Label("Systeemin läpi yhteensä:");
			yhtLapiSysteeminLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));

			yhtLapiSysteeminTulos = new Label(); // Tähän tulee näkyviin tulos simuloinnin loputtua
			yhtLapiSysteeminTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yhtLapiSysteeminTulos.setMaxWidth(100);

			simuKokonaisaikaLabel = new Label("Kokonaisaika:");
			simuKokonaisaikaLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			simuKokonaisaikaTulos = new Label();
			simuKokonaisaikaTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			simuKokonaisaikaTulos.setMaxWidth(100);

			// Järjestele grid

			grid.add(aikaLabel, 1, 0); // sarake, rivi, leveys, korkeus
			GridPane.setHalignment(aikaLabel, HPos.RIGHT);
			grid.add(aika, 2, 0);

			grid.add(viiveLabel, 1, 1);
			GridPane.setHalignment(viiveLabel, HPos.RIGHT);
			grid.add(viive, 2, 1);

			grid.add(xAsiakkaidenParamLabel, 1, 2);
			GridPane.setHalignment(xAsiakkaidenParamLabel, HPos.RIGHT);
			grid.add(xAsiakkaidenParam, 2, 2);

			grid.add(yAsiakkaidenParamLabel, 1, 3);
			GridPane.setHalignment(yAsiakkaidenParamLabel, HPos.RIGHT);
			grid.add(yAsiakkaidenParam, 2, 3);

			// Välissä tyhjä rivi 4

			grid.add(kaynnistaButton, 1, 5, 2, 1);
			GridPane.setHalignment(kaynnistaButton, HPos.CENTER);

			grid.add(nopeutaButton, 1, 6);
			GridPane.setHalignment(nopeutaButton, HPos.RIGHT);

			grid.add(hidastaButton, 2, 6);
			GridPane.setHalignment(hidastaButton, HPos.LEFT);

			// Välissä tyhjä rivi 7

			// Labelien järjestys: PP1, PP2, PP4, PP3
			grid.add(pp1Label, 0, 8);
			GridPane.setHalignment(pp1Label, HPos.CENTER);

			grid.add(pp2Label, 1, 8);
			GridPane.setHalignment(pp2Label, HPos.CENTER);

			grid.add(pp4Label, 2, 8);
			GridPane.setHalignment(pp4Label, HPos.CENTER);

			grid.add(pp3Label, 3, 8);
			GridPane.setHalignment(pp3Label, HPos.CENTER);

			// Näyttöjen järjestys: PP1, PP2, PP4, PP3
			grid.add((Canvas) pp1Naytto, 0, 9);
			grid.add((Canvas) pp2Naytto, 1, 9);
			grid.add((Canvas) pp4Naytto, 2, 9);
			grid.add((Canvas) pp3Naytto, 3, 9);

			// Saapuneet, systeemin läpi ja kokonaisaika

			grid.add(xSaapuneetLabel, 0, 10);
			GridPane.setHalignment(xSaapuneetLabel, HPos.CENTER);
			grid.add(xSaapuneetTulos, 1, 10);
			GridPane.setHalignment(xSaapuneetTulos, HPos.CENTER);

			grid.add(ySaapuneetLabel, 0, 11);
			GridPane.setHalignment(ySaapuneetLabel, HPos.CENTER);
			grid.add(ySaapuneetTulos, 1, 11);
			GridPane.setHalignment(ySaapuneetTulos, HPos.CENTER);

			grid.add(yhtSaapuneetLabel, 0, 12);
			GridPane.setHalignment(yhtSaapuneetLabel, HPos.CENTER);
			grid.add(yhtSaapuneetTulos, 1, 12);
			GridPane.setHalignment(yhtSaapuneetTulos, HPos.CENTER);

			grid.add(xLapiSysteeminLabel, 2, 10);
			GridPane.setHalignment(xLapiSysteeminLabel, HPos.CENTER);
			grid.add(xLapiSysteeminTulos, 3, 10);
			GridPane.setHalignment(xLapiSysteeminTulos, HPos.CENTER);

			grid.add(yLapiSysteeminLabel, 2, 11);
			GridPane.setHalignment(yLapiSysteeminLabel, HPos.CENTER);
			grid.add(yLapiSysteeminTulos, 3, 11);
			GridPane.setHalignment(yLapiSysteeminTulos, HPos.CENTER);

			grid.add(yhtLapiSysteeminLabel, 2, 12);
			GridPane.setHalignment(yhtLapiSysteeminLabel, HPos.CENTER);
			grid.add(yhtLapiSysteeminTulos, 3, 12);
			GridPane.setHalignment(yhtLapiSysteeminTulos, HPos.CENTER);

			// Välissä tyhjä rivi 13

			grid.add(simuKokonaisaikaLabel, 0, 14);
			GridPane.setHalignment(simuKokonaisaikaLabel, HPos.CENTER);
			grid.add(simuKokonaisaikaTulos, 1, 14);
			GridPane.setHalignment(simuKokonaisaikaTulos, HPos.CENTER);

			// Käytettävissä olevat fontit
			System.out.println(javafx.scene.text.Font.getFamilies());

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // Käyttöliittymän rakentaminen päättyy

	// Käyttöliittymän rajapintametodit (kutsutaan kontrollerista)

	@Override
	public double getAika() {
		return Double.parseDouble(aika.getText());
	}

	@Override
	public long getViive() {
		return Long.parseLong(viive.getText());
	}

	@Override
	public void setLoppuaika(double aika) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.simuKokonaisaikaTulos.setText(formatter.format(aika));
	}

	@Override
	public void setAsiakasMaaraX(int asiakasMaaraX) {
		this.xSaapuneetTulos.setText(asiakasMaaraX + "");
	}

	@Override
	public void setAsiakasMaaraY(int asiakasMaaraY) {
		this.ySaapuneetTulos.setText(asiakasMaaraY + "");
	}

	@Override
	public void setAsiakasMaaraYht(int asiakasMaaraYht) {
		this.yhtSaapuneetTulos.setText(asiakasMaaraYht + "");
	}

	// Tämä ei näy vielä GUI:ssa
	@Override
	public void setPalvellutX(int asiakasMaaraX) {
		this.xLapiSysteeminTulos.setText(asiakasMaaraX + "");
	}

	// Tämä ei näy vielä GUI:ssa
	@Override
	public void setPalvellutY(int asiakasMaaraY) {
		this.yLapiSysteeminTulos.setText(asiakasMaaraY + "");
	}

	@Override
	public void setPalvellut(int asiakasMaara) {
		this.yhtLapiSysteeminTulos.setText(asiakasMaara + "");
	}

	@Override
	public IVisualisointi getPP1Visualisointi() {
		return pp1Naytto;
	}

	@Override
	public IVisualisointi getPP2Visualisointi() {
		return pp2Naytto;
	}

	@Override
	public IVisualisointi getPP3Visualisointi() {
		return pp3Naytto;
	}

	@Override
	public IVisualisointi getPP4Visualisointi() {
		return pp4Naytto;
	}

	// JavaFX-sovelluksen (käyttöliittymän) käynnistäminen
	public static void main(String[] args) {
		launch(args);
	}

}
