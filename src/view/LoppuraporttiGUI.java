package view;

import java.text.DecimalFormat;

import controller.IKontrolleriVtoM;
import controller.Kontrolleri;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;

public class LoppuraporttiGUI extends Application implements ILoppuraporttiUI {

	/*
	// Kontrollerin esittely (tarvitaan käyttöliittymässä)
	private IKontrolleriVtoM kontrolleri;
	*/

	// Käyttöliittymäkomponentit:
	private Label simuLoppuraporttiLabel;
	
	private Label simuKokonaisaikaLabel;
	private Label simuKokonaisaikaTulos;
	
	private Label xAsiakkaidenParamLabel;
	private Label xAsiakkaidenParamTulos;

	private Label yAsiakkaidenParamLabel;
	private Label yAsiakkaidenParamTulos;
	
	private Label simuSuoritustehoLabel;
	private Label simuSuoritustehoTulos;
	
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
	
	private Label xAsiakkaidenLapimenoaikaLabel;
	private Label xAsiakkaidenLapimenoaikaTulos;
	
	private Label yAsiakkaidenLapimenoaikaLabel;
	private Label yAsiakkaidenLapimenoaikaTulos;
	
	private Label asiakkaidenLapimenoaikaYhtLabel;
	private Label asiakkaidenLapimenoaikaYhtTulos;
	
	private Label oleskeluaikaPP1Label;
	private Label oleskeluaikaPP1Tulos;
	
	private Label oleskeluaikaPP2Label;
	private Label oleskeluaikaPP2Tulos;
	
	private Label oleskeluaikaPP3Label;
	private Label oleskeluaikaPP3Tulos;
	
	private Label oleskeluaikaPP4Label;
	private Label oleskeluaikaPP4Tulos;
	
	private Label jononPituusPP1Label;
	private Label jononPituusPP1Tulos;
	
	private Label jononPituusPP2Label;
	private Label jononPituusPP2Tulos;
	
	private Label jononPituusPP3Label;
	private Label jononPituusPP3Tulos;
	
	private Label jononPituusPP4Label;
	private Label jononPituusPP4Tulos;
	
	private Button tarkasteleRaporttejaButton;
	
	/*
	@Override
	public void init() {

		Trace.setTraceLevel(Level.INFO);

		kontrolleri = new Kontrolleri(this);
	}
	*/
	
	public static void main(String[] args) {
		launch(args);
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

			primaryStage.setTitle("Simulaation loppuraportti");

			BorderPane border = new BorderPane(); // Pohjana border

			Scene scene = new Scene(border, 750, 750);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setVgap(10);
			grid.setHgap(5);

			border.setCenter(grid); // Borderin keskellä grid
			
			simuLoppuraporttiLabel = new Label("Simulaation loppuraportti");
			simuLoppuraporttiLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
			
			simuKokonaisaikaLabel = new Label("Kokonaisaika:");
			simuKokonaisaikaLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			simuKokonaisaikaTulos = new Label();
			simuKokonaisaikaTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			simuKokonaisaikaTulos.setMaxWidth(100);
			
			xAsiakkaidenParamLabel = new Label("Parametri X:");
			xAsiakkaidenParamLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xAsiakkaidenParamTulos = new Label();
			xAsiakkaidenParamTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xAsiakkaidenParamTulos.setMaxWidth(100);
			
			yAsiakkaidenParamLabel = new Label("Parametri Y:");
			yAsiakkaidenParamLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yAsiakkaidenParamTulos = new Label();
			yAsiakkaidenParamTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yAsiakkaidenParamTulos.setMaxWidth(100);
			
			simuSuoritustehoLabel = new Label("Simulaation suoritusteho:");
			simuSuoritustehoLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			simuSuoritustehoTulos = new Label();
			simuSuoritustehoTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			simuSuoritustehoTulos.setMaxWidth(100);
			
			xSaapuneetLabel = new Label("Saapuneet X:");
			xSaapuneetLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xSaapuneetTulos = new Label();
			xSaapuneetTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xSaapuneetTulos.setMaxWidth(100);
			
			ySaapuneetLabel = new Label("Saapuneet Y:");
			ySaapuneetLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			ySaapuneetTulos = new Label();
			ySaapuneetTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			ySaapuneetTulos.setMaxWidth(100);
			
			yhtSaapuneetLabel = new Label("Saapuneet yhteensä:");
			yhtSaapuneetLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yhtSaapuneetTulos = new Label();
			yhtSaapuneetTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yhtSaapuneetTulos.setMaxWidth(100);
			
			xLapiSysteeminLabel = new Label("Systeemin läpi X:");
			xLapiSysteeminLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xLapiSysteeminTulos = new Label();
			xLapiSysteeminTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xLapiSysteeminTulos.setMaxWidth(100);

			yLapiSysteeminLabel = new Label("Systeemin läpi Y:");
			yLapiSysteeminLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yLapiSysteeminTulos = new Label();
			yLapiSysteeminTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yLapiSysteeminTulos.setMaxWidth(100);

			yhtLapiSysteeminLabel = new Label("Systeemin läpi yhteensä:");
			yhtLapiSysteeminLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yhtLapiSysteeminTulos = new Label();
			yhtLapiSysteeminTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yhtLapiSysteeminTulos.setMaxWidth(100);
			
			// Läpimenoajat
			xAsiakkaidenLapimenoaikaLabel = new Label("X-asiakkaiden keskim. läpimenoaika:");
			xAsiakkaidenLapimenoaikaLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xAsiakkaidenLapimenoaikaTulos = new Label();
			xAsiakkaidenLapimenoaikaTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			xAsiakkaidenLapimenoaikaTulos.setMaxWidth(100);

			yAsiakkaidenLapimenoaikaLabel = new Label("Y-asiakkaiden keskim. läpimenoaika:");
			yAsiakkaidenLapimenoaikaLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yAsiakkaidenLapimenoaikaTulos = new Label();
			yAsiakkaidenLapimenoaikaTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			yAsiakkaidenLapimenoaikaTulos.setMaxWidth(100);

			asiakkaidenLapimenoaikaYhtLabel = new Label("Kaikkien asiakkaiden keskim. läpimenoaika:");
			asiakkaidenLapimenoaikaYhtLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			asiakkaidenLapimenoaikaYhtTulos = new Label();
			asiakkaidenLapimenoaikaYhtTulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			asiakkaidenLapimenoaikaYhtTulos.setMaxWidth(100);
			
			// Oleskeluajat
			oleskeluaikaPP1Label = new Label("Kokonaisoleskeluaika PP1:");
			oleskeluaikaPP1Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			oleskeluaikaPP1Tulos = new Label();
			oleskeluaikaPP1Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			oleskeluaikaPP1Tulos.setMaxWidth(100);
			
			oleskeluaikaPP2Label = new Label("Kokonaisoleskeluaika PP2:");
			oleskeluaikaPP2Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			oleskeluaikaPP2Tulos = new Label();
			oleskeluaikaPP2Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			oleskeluaikaPP2Tulos.setMaxWidth(100);
			
			oleskeluaikaPP3Label = new Label("Kokonaisoleskeluaika PP3:");
			oleskeluaikaPP3Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			oleskeluaikaPP3Tulos = new Label();
			oleskeluaikaPP3Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			oleskeluaikaPP3Tulos.setMaxWidth(100);
			
			oleskeluaikaPP4Label = new Label("Kokonaisoleskeluaika PP4:");
			oleskeluaikaPP4Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			oleskeluaikaPP4Tulos = new Label();
			oleskeluaikaPP4Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			oleskeluaikaPP4Tulos.setMaxWidth(100);
			
			// Jononpituudet
			jononPituusPP1Label = new Label("Keskimääräinen jononpituus PP1:");
			jononPituusPP1Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			jononPituusPP1Tulos = new Label();
			jononPituusPP1Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			jononPituusPP1Tulos.setMaxWidth(100);
			
			jononPituusPP2Label = new Label("Keskimääräinen jononpituus PP2:");
			jononPituusPP2Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			jononPituusPP2Tulos = new Label();
			jononPituusPP2Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			jononPituusPP2Tulos.setMaxWidth(100);
			
			jononPituusPP3Label = new Label("Keskimääräinen jononpituus PP3:");
			jononPituusPP3Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			jononPituusPP3Tulos = new Label();
			jononPituusPP3Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			jononPituusPP3Tulos.setMaxWidth(100);
			
			jononPituusPP4Label = new Label("Keskimääräinen jononpituus PP4:");
			jononPituusPP4Label.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			jononPituusPP4Tulos = new Label();
			jononPituusPP4Tulos.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
			jononPituusPP4Tulos.setMaxWidth(100);
			
			tarkasteleRaporttejaButton = new Button("Tarkastele simulointiraportteja");
			tarkasteleRaporttejaButton.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
			tarkasteleRaporttejaButton.setPrefWidth(220);

			// Tsekkaa painikkeen OnAction
			
			/*
			tarkasteleRaporttejaButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						Stage raporttiStage = new Stage();
						raporttiGUI.start(raporttiStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			*/
			
			// Järjestele grid
			grid.add(simuLoppuraporttiLabel, 0, 0); // sarake, rivi, leveys, korkeus
			GridPane.setHalignment(simuLoppuraporttiLabel, HPos.CENTER);
			
			grid.add(simuKokonaisaikaLabel, 0, 1);
			grid.add(simuKokonaisaikaTulos, 1, 1);
			
			grid.add(xAsiakkaidenParamLabel, 0, 2);
			grid.add(xAsiakkaidenParamTulos, 1, 2);
			
			grid.add(yAsiakkaidenParamLabel, 0, 3);
			grid.add(yAsiakkaidenParamTulos, 1, 3);
			
			grid.add(simuSuoritustehoLabel, 0, 4);
			grid.add(simuSuoritustehoTulos, 1, 4);
			
			grid.add(xSaapuneetLabel, 0, 5);
			grid.add(xSaapuneetTulos, 1, 5);
			
			grid.add(ySaapuneetLabel, 0, 6);
			grid.add(ySaapuneetTulos, 1, 6);
			
			grid.add(yhtSaapuneetLabel, 0, 7);
			grid.add(yhtSaapuneetTulos, 1, 7);
			
			grid.add(xLapiSysteeminLabel, 0, 8);
			grid.add(xLapiSysteeminTulos, 1, 8);
			
			grid.add(yLapiSysteeminLabel, 0, 9);
			grid.add(yLapiSysteeminTulos, 1, 9);
			
			grid.add(yhtLapiSysteeminLabel, 0, 10);
			grid.add(yhtLapiSysteeminTulos, 1, 10);
			
			grid.add(xAsiakkaidenLapimenoaikaLabel, 0, 11);
			grid.add(xAsiakkaidenLapimenoaikaTulos, 1, 11);
			
			grid.add(yAsiakkaidenLapimenoaikaLabel, 0, 12);
			grid.add(yAsiakkaidenLapimenoaikaTulos, 1, 12);
			
			grid.add(asiakkaidenLapimenoaikaYhtLabel, 0, 13);
			grid.add(asiakkaidenLapimenoaikaYhtTulos, 1, 13);
			
			grid.add(oleskeluaikaPP1Label, 0, 14);
			grid.add(oleskeluaikaPP1Tulos, 1, 14);
			
			grid.add(oleskeluaikaPP2Label, 0, 15);
			grid.add(oleskeluaikaPP2Tulos, 1, 15);
			
			grid.add(oleskeluaikaPP3Label, 0, 16);
			grid.add(oleskeluaikaPP3Tulos, 1, 16);
			
			grid.add(oleskeluaikaPP4Label, 0, 17);
			grid.add(oleskeluaikaPP4Tulos, 1, 17);
			
			grid.add(jononPituusPP1Label, 0, 18);
			grid.add(jononPituusPP1Tulos, 1, 18);
			
			grid.add(jononPituusPP2Label, 0, 19);
			grid.add(jononPituusPP2Tulos, 1, 19);
			
			grid.add(jononPituusPP3Label, 0, 20);
			grid.add(jononPituusPP3Tulos, 1, 20);
			
			grid.add(jononPituusPP4Label, 0, 21);
			grid.add(jononPituusPP4Tulos, 1, 21);
			
			grid.add(tarkasteleRaporttejaButton, 0, 23);

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // Käyttöliittymän rakentaminen päättyy

	@Override
	public void setSimulaationKokonaisaika(double simulaationKokonaisaika) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.simuKokonaisaikaTulos.setText(formatter.format(simulaationKokonaisaika));
	}

	@Override
	public void setxParam(double xParam) {
		this.xAsiakkaidenParamTulos.setText(xParam + "");
	}

	@Override
	public void setyParam(double yParam) {
		this.yAsiakkaidenParamTulos.setText(yParam + "");
	}

	@Override
	public void setSimulaationSuoritusteho(double simulaationSuoritusteho) {
		this.simuSuoritustehoTulos.setText(simulaationSuoritusteho + "");
	}

	@Override
	public void setAsiakasMaaraX(double asiakasMaaraX) {
		this.xSaapuneetTulos.setText(asiakasMaaraX + "");
	}

	@Override
	public void setAsiakasMaaraY(double asiakasMaaraY) {
		this.ySaapuneetTulos.setText(asiakasMaaraY + "");
	}

	@Override
	public void setxAsiakkaidenLapimenoaika(double xAsiakkaidenLapimenoaika) {
		this.xAsiakkaidenLapimenoaikaTulos.setText(xAsiakkaidenLapimenoaika + "");
	}

	@Override
	public void setyAsiakkaidenLapimenoaika(double yAsiakkaidenLapimenoaika) {
		this.yAsiakkaidenLapimenoaikaTulos.setText(yAsiakkaidenLapimenoaika + "");
	}

	@Override
	public void setAsiakkaidenLapimenoaikaYht(double asiakkaidenLapimenoaikaYht) {
		this.asiakkaidenLapimenoaikaYhtTulos.setText(asiakkaidenLapimenoaikaYht + "");
	}

	@Override
	public void setLapimenneetX(int lapimenneetX) {
		this.xLapiSysteeminTulos.setText(lapimenneetX + "");
	}

	@Override
	public void setLapimenneetY(int lapimenneetY) {
		this.yLapiSysteeminTulos.setText(lapimenneetY + "");
	}

	@Override
	public void setOleskeluaikaPP1(double oleskeluaikaPP1) {
		this.oleskeluaikaPP1Tulos.setText(oleskeluaikaPP1 + "");
	}

	@Override
	public void setOleskeluaikaPP2(double oleskeluaikaPP2) {
		this.oleskeluaikaPP2Tulos.setText(oleskeluaikaPP2 + "");
	}

	@Override
	public void setOleskeluaikaPP3(double oleskeluaikaPP3) {
		this.oleskeluaikaPP3Tulos.setText(oleskeluaikaPP3 + "");
	}

	@Override
	public void setOleskeluaikaPP4(double oleskeluaikaPP4) {
		this.oleskeluaikaPP4Tulos.setText(oleskeluaikaPP4 + "");
	}

	@Override
	public void setJononPituusPP1(double jononPituusPP1) {
		this.jononPituusPP1Tulos.setText(jononPituusPP1 + "");
	}

	@Override
	public void setJononPituusPP2(double jononPituusPP2) {
		this.jononPituusPP2Tulos.setText(jononPituusPP2 + "");
	}

	@Override
	public void setJononPituusPP3(double jononPituusPP3) {
		this.jononPituusPP3Tulos.setText(jononPituusPP3 + "");
	}

	@Override
	public void setJononPituusPP4(double jononPituusPP4) {
		this.jononPituusPP4Tulos.setText(jononPituusPP4 + "");
	}
}
