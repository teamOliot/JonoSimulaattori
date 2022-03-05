package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import simu.model.ITietokantaRaporttiDAO;
import simu.model.TietokantaRaportti;
import simu.model.TietokantaRaporttiAO;


/**
 * @author Dahlman
 *
 */
public class TietokantaRaporttiGUI extends Application {
	static ITietokantaRaporttiDAO tietokantaraporttiDAO = new TietokantaRaporttiAO();


//	private static List<TietokantaRaportti> tietokantaRaportti = new ArrayList<TietokantaRaportti>();
//	TietokantaRaportti raportti = new TietokantaRaportti(500.0789207815674, 15, 25, 0.10,33.0, 22.0, 
//           				 12, 26, 17, 101.1,183.3 , 376.8, 300.1, 0.2, 0.3, 0.7, 0.6, 77, 44, 12.0,12.0,12.0,12.0);

	public static void main(String[] args) {
		launch(args);
	}

//	public static void setManager(List<TietokantaRaportti> dbList) {
//		tietokantaRaportti = dbList;
//	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Created a table view
		TableView tbv = new TableView();
		// voisi tehdä myös toisen table view?? Sitten pitää myös miettiä miten loppaa rapsan tiedot, kts. for loop rivi 117
        final Label label = new Label("Loppuraportti tietokannasta");
        label.setFont(new Font("Arial", 20));
		
		// Added all columns to table view
//		TableColumn<Integer, TietokantaRaportti> cl1 = new TableColumn<>("Id");
//		cl1.setCellValueFactory(new PropertyValueFactory<>("Id"));
		TableColumn<LocalDate, TietokantaRaportti> cl2 = new TableColumn<>("Päivämäärä");
		cl2.setCellValueFactory(new PropertyValueFactory<>("paivamaara"));
		TableColumn<Double, TietokantaRaportti> cl3 = new TableColumn<>("Kokonaisaika");
		cl3.setCellValueFactory(new PropertyValueFactory<>("simulaationKokonaisaika"));
		TableColumn<Double, TietokantaRaportti> cl4 = new TableColumn<>("Ei WC as. \nparametri");
		cl4.setCellValueFactory(new PropertyValueFactory<>("xParam"));
		TableColumn<Double, TietokantaRaportti> cl5 = new TableColumn<>("WC as. \nparametri");
		cl5.setCellValueFactory(new PropertyValueFactory<>("yParam"));
		TableColumn<Double, TietokantaRaportti> cl6 = new TableColumn<>("Simulaation \nsuoritusteho");
		cl6.setCellValueFactory(new PropertyValueFactory<>("simulaationSuoritusteho"));
		//cl6.setMinWidth(150);
		TableColumn<Double, TietokantaRaportti> cl7 = new TableColumn<>("Saapuneet \nei WC as.");
		cl7.setCellValueFactory(new PropertyValueFactory<>("asiakasMaaraX"));
		cl7.setMinWidth(80);
		TableColumn<Double, TietokantaRaportti> cl8 = new TableColumn<>("Saapuneet \nWC as.");
		cl8.setCellValueFactory(new PropertyValueFactory<>("asiakasMaaraY"));
		cl8.setMinWidth(80);
		TableColumn<Double, TietokantaRaportti> cl9 = new TableColumn<>("Ei WC as. \nläpimenoaika");
		cl9.setCellValueFactory(new PropertyValueFactory<>("xAsiakkaidenLapimenoaika"));
		cl9.setMinWidth(80);
		TableColumn<Double, TietokantaRaportti> cl10 = new TableColumn<>("WC as. \nläpimenoaika");
		cl10.setCellValueFactory(new PropertyValueFactory<>("yAsiakkaidenLapimenoaika"));
		cl10.setMinWidth(80);
		TableColumn<Double, TietokantaRaportti> cl11 = new TableColumn<>("Läpimenoaika \nyhteensä");
		cl11.setCellValueFactory(new PropertyValueFactory<>("asiakkaidenLapimenoaikaYht"));
		cl11.setMinWidth(80);
		TableColumn<Double, TietokantaRaportti> cl13 = new TableColumn<>("PP1 \nkäyttöaste");
		cl13.setCellValueFactory(new PropertyValueFactory<>("kayttoastePP1"));
		TableColumn<Double, TietokantaRaportti> cl14 = new TableColumn<>("PP2 \nkäyttöaste");
		cl14.setCellValueFactory(new PropertyValueFactory<>("kayttoastePP2"));
		TableColumn<Double, TietokantaRaportti> cl15 = new TableColumn<>("PP3 \nkäyttöaste");
		cl15.setCellValueFactory(new PropertyValueFactory<>("kayttoastePP3"));
		TableColumn<Double, TietokantaRaportti> cl16 = new TableColumn<>("PP4 \nkäyttöaste");
		cl16.setCellValueFactory(new PropertyValueFactory<>("kayttoastePP4"));
		TableColumn<Double, TietokantaRaportti> cl17 = new TableColumn<>("PP1 jonon \npituus");
		cl17.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP1"));
		TableColumn<Double, TietokantaRaportti> cl18 = new TableColumn<>("PP2 jonon \npituus");
		cl18.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP2"));
		TableColumn<Double, TietokantaRaportti> cl19 = new TableColumn<>("PP3 jonon \npituus");
		cl19.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP3"));
		TableColumn<Double, TietokantaRaportti> cl20 = new TableColumn<>("PP4 jonon \npituus");
		cl20.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP4"));
		
		//tbv.getColumns().add(cl1);
		tbv.getColumns().add(cl2);
		tbv.getColumns().add(cl3);
		tbv.getColumns().add(cl4);
		tbv.getColumns().add(cl5);
		tbv.getColumns().add(cl6);
		tbv.getColumns().add(cl7);
		tbv.getColumns().add(cl8);
		tbv.getColumns().add(cl9);
		tbv.getColumns().add(cl10);
		tbv.getColumns().add(cl11);
		tbv.getColumns().add(cl13);
		tbv.getColumns().add(cl14);
		tbv.getColumns().add(cl15);
		tbv.getColumns().add(cl16);
		tbv.getColumns().add(cl17);
		tbv.getColumns().add(cl18);
		tbv.getColumns().add(cl19);
		tbv.getColumns().add(cl20);
		
		TietokantaRaportti[] raportit = tietokantaraporttiDAO.readRaportit();
		for (TietokantaRaportti r : raportit) {
			System.out.println("Omamoottori: tämä tulee tietokannasta " + r);
			tbv.getItems().add(r);
		}
//		
//		tietokantaRaportti.add(raportti);
//	for (TietokantaRaportti dbList : tietokantaRaportti) {
//		tbv.getItems().add(dbList);
//	}
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(label,tbv);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
}
