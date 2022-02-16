package view;


import java.text.DecimalFormat;
import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;



public class SimulaattorinGUI extends Application implements ISimulaattorinUI{

	//Kontrollerin esittely (tarvitaan käyttöliittymässä)
	private IKontrolleriVtoM kontrolleri;

	// Käyttöliittymäkomponentit:
	private TextField aika;
	private TextField viive;
	private Label xLabel;
	private Label xTulos;
	private Label tulos;
	private Label aikaLabel;
	private Label viiveLabel;
	private Label tulosLabel;
	
	private Button kaynnistaButton;
	private Button hidastaButton;
	private Button nopeutaButton;

	private IVisualisointi pp1Naytto;
	private IVisualisointi pp2Naytto;
	private IVisualisointi pp3Naytto;
	private IVisualisointi pp4Naytto;

	@Override
	public void init(){
		
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

			kaynnistaButton = new Button();
			kaynnistaButton.setText("Käynnistä simulointi");
			kaynnistaButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                kontrolleri.kaynnistaSimulointi();
	                kaynnistaButton.setDisable(true);
	            }
	        });

			hidastaButton = new Button();
			hidastaButton.setText("Hidasta");
			hidastaButton.setOnAction(e -> kontrolleri.hidasta());

			nopeutaButton = new Button();
			nopeutaButton.setText("Nopeuta");
			nopeutaButton.setOnAction(e -> kontrolleri.nopeuta());

			aikaLabel = new Label("Simulointiaika:");
			aikaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        aika = new TextField("Syötä aika");
	        aika.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        aika.setPrefWidth(150);

	        viiveLabel = new Label("Viive:");
			viiveLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        viive = new TextField("Syötä viive");
	        viive.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        viive.setPrefWidth(150);
	                	        
	        tulosLabel = new Label("Kokonaisaika:");
			tulosLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        tulos = new Label();
	        tulos.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        tulos.setPrefWidth(150);
	        
	        //
	        xLabel = new Label("x asiakkaat:");
	        xLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        xTulos = new Label();
	        xTulos.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        xTulos.setPrefWidth(150);

	        HBox hBox = new HBox();
	        hBox.setPadding(new Insets(15, 12, 15, 12)); // marginaalit ylä, oikea, ala, vasen
	        hBox.setSpacing(10);   // noodien välimatka 10 pikseliä
	        
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setVgap(10);
	        grid.setHgap(5);

	        grid.add(aikaLabel, 0, 0);   // sarake, rivi
	        grid.add(aika, 1, 0);          // sarake, rivi
	        grid.add(viiveLabel, 0, 1);      // sarake, rivi
	        grid.add(viive, 1, 1);           // sarake, rivi
	        grid.add(tulosLabel, 0, 2);      // sarake, rivi
	        grid.add(tulos, 1, 2);           // sarake, rivi
	        grid.add(kaynnistaButton,0, 3);  // sarake, rivi
	        grid.add(nopeutaButton, 0, 4);   // sarake, rivi
	        grid.add(hidastaButton, 1, 4);   // sarake, rivi
	        //
	        grid.add(xLabel, 0, 5);      // sarake, rivi
	        grid.add(xTulos, 1, 5); 
	        
	        pp1Naytto = new Visualisointi(200,300);
	        pp2Naytto = new Visualisointi(200, 300);
	        pp3Naytto = new Visualisointi(200, 300);
	        pp4Naytto = new Visualisointi(200, 300);

	        // Täytetään boxi:
	        // Näyttöjen järjestys: PP1, PP2, PP4, PP3
	        hBox.getChildren().addAll(grid, (Canvas)pp1Naytto, (Canvas)pp2Naytto, (Canvas)pp4Naytto, (Canvas)pp3Naytto);
	        
	        Scene scene = new Scene(hBox);
	        primaryStage.setScene(scene);
	        primaryStage.show();



		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	//Käyttöliittymän rajapintametodit (kutsutaan kontrollerista)

	@Override
	public double getAika(){
		return Double.parseDouble(aika.getText());
	}

	@Override
	public long getViive(){
		return Long.parseLong(viive.getText());
	}

	@Override
	public void setLoppuaika(double aika){
		 DecimalFormat formatter = new DecimalFormat("#0.00");
		 this.tulos.setText(formatter.format(aika));
	}
	
	@Override
	public void setAsiakasMaaraX(int asiakasMaaraX) {
		this.xTulos.setText(asiakasMaaraX + "");
	}
	
	@Override
	public void setAsiakasMaaraY(int asiakasMaaraY) {
		System.out.println("SimuGUI Asiakas Y "+asiakasMaaraY);	
	}
	
	@Override
	public void setAsiakasMaara(int asiakasMaara) {
		System.out.println("SimuGUI saapuneet asiakkaat yht "+asiakasMaara);	
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

	@Override
	public void setPalvellut(int asiakasMaara) {
		System.out.println("SimuGUI Palvellut asiakkaat yhteensä "+asiakasMaara);	
		
	}
}
