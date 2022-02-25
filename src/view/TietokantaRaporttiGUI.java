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
import simu.model.TietokantaRaportti;


/**
 * @author Dahlman
 *
 */
public class TietokantaRaporttiGUI extends Application {


	private static List<TietokantaRaportti> tietokantaRaportti = new ArrayList<TietokantaRaportti>();
	TietokantaRaportti raportti = new TietokantaRaportti(500.0789207815674, 15, 25, 0.10,33.0, 22.0, 
            				 12, 26, 17, 101.1,183.3 , 376.8, 300.1, 0.2, 0.3, 0.7, 0.6);

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
		TableColumn<Double, TietokantaRaportti> cl4 = new TableColumn<>("Parametri X");
		cl4.setCellValueFactory(new PropertyValueFactory<>("xParam"));
		TableColumn<Double, TietokantaRaportti> cl5 = new TableColumn<>("Parametri Y");
		cl5.setCellValueFactory(new PropertyValueFactory<>("yParam"));
		TableColumn<Double, TietokantaRaportti> cl6 = new TableColumn<>("Simulaation suoritusteho");
		cl6.setCellValueFactory(new PropertyValueFactory<>("simulaationSuoritusteho"));
		cl6.setMinWidth(150);
		TableColumn<Double, TietokantaRaportti> cl7 = new TableColumn<>("Saapuneet X asiakkaat");
		cl7.setCellValueFactory(new PropertyValueFactory<>("asiakasMaaraX"));
		cl7.setMinWidth(150);
		TableColumn<Double, TietokantaRaportti> cl8 = new TableColumn<>("Saapuneet Y asiakkaat");
		cl8.setCellValueFactory(new PropertyValueFactory<>("asiakasMaaraY"));
		cl8.setMinWidth(150);
//		TableColumn<Double, TietokantaRaportti> cl9 = new TableColumn<>("X as läpimenoaika");
//		cl9.setCellValueFactory(new PropertyValueFactory<>("xAsiakkaidenLapimenoaika"));
//		cl9.setMinWidth(120);
//		TableColumn<Double, TietokantaRaportti> cl10 = new TableColumn<>("Y as läpimenoaika");
//		cl10.setCellValueFactory(new PropertyValueFactory<>("yAsiakkaidenLapimenoaika"));
//		cl10.setMinWidth(120);
		TableColumn<Double, TietokantaRaportti> cl11 = new TableColumn<>("As läpimenoaika yht");
		cl11.setCellValueFactory(new PropertyValueFactory<>("asiakkaidenLapimenoaikaYht"));
		cl11.setMinWidth(150);
		TableColumn<Double, TietokantaRaportti> cl13 = new TableColumn<>("PP1 oleskeluaika");
		cl13.setCellValueFactory(new PropertyValueFactory<>("oleskeluaikaPP1"));
		cl13.setMinWidth(120);
		TableColumn<Double, TietokantaRaportti> cl14 = new TableColumn<>("PP2 oleskeluaika");
		cl14.setCellValueFactory(new PropertyValueFactory<>("oleskeluaikaPP2"));
		cl14.setMinWidth(120);
		TableColumn<Double, TietokantaRaportti> cl15 = new TableColumn<>("PP3 oleskeluaika");
		cl15.setCellValueFactory(new PropertyValueFactory<>("oleskeluaikaPP3"));
		cl15.setMinWidth(120);
		TableColumn<Double, TietokantaRaportti> cl16 = new TableColumn<>("PP4 oleskeluaika");
		cl16.setCellValueFactory(new PropertyValueFactory<>("oleskeluaikaPP4"));
		cl16.setMinWidth(120);
		TableColumn<Double, TietokantaRaportti> cl17 = new TableColumn<>("PP1 jonon pituus");
		cl17.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP1"));
		cl17.setMinWidth(120);
		TableColumn<Double, TietokantaRaportti> cl18 = new TableColumn<>("PP2 jonon pituus");
		cl18.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP2"));
		cl18.setMinWidth(120);
		TableColumn<Double, TietokantaRaportti> cl19 = new TableColumn<>("PP3 jonon pituus");
		cl19.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP3"));
		cl19.setMinWidth(120);
		TableColumn<Double, TietokantaRaportti> cl20 = new TableColumn<>("PP4 jonon pituus");
		cl20.setCellValueFactory(new PropertyValueFactory<>("jononPituusPP4"));
		cl20.setMinWidth(120);
		
		//tbv.getColumns().add(cl1);
		tbv.getColumns().add(cl2);
		tbv.getColumns().add(cl3);
		tbv.getColumns().add(cl4);
		tbv.getColumns().add(cl5);
		tbv.getColumns().add(cl6);
		tbv.getColumns().add(cl7);
		tbv.getColumns().add(cl8);
//		tbv.getColumns().add(cl9);
//		tbv.getColumns().add(cl10);
		tbv.getColumns().add(cl11);
		tbv.getColumns().add(cl13);
		tbv.getColumns().add(cl14);
		tbv.getColumns().add(cl15);
		tbv.getColumns().add(cl16);
		tbv.getColumns().add(cl17);
		tbv.getColumns().add(cl18);
		tbv.getColumns().add(cl19);
		tbv.getColumns().add(cl20);
		
		tietokantaRaportti.add(raportti);
		for (TietokantaRaportti dbList : tietokantaRaportti) {
			tbv.getItems().add(dbList);
		}
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(label,tbv);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
}
