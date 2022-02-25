package view;

import java.time.LocalDate;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import simu.framework.Kello;
import simu.model.TietokantaRaportti;

public class TietokantaRaporttiGUI extends Application {

//    private TableView<TietokantaRaportti> table = new TableView<TietokantaRaportti>();
//    private final ObservableList<TietokantaRaportti> data =
//            FXCollections.observableArrayList(
//            		 new TietokantaRaportti(500.0789207815674, 15.0, 25.0, 0.10,33.0, 22.0, 
//            				 12, 26, 17, 101.1,183.3 , 376.8, 300.1, 0.2, 0.3, 0.7, 0.6));

	private static List<TietokantaRaportti> tietokantaRaportti;

	public static void main(String[] args) {
		launch(args);
	}

	public static void setManager(List<TietokantaRaportti> dbList) {
		tietokantaRaportti = dbList;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TableView tbv = new TableView();
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
		TableColumn<Double, TietokantaRaportti> cl7 = new TableColumn<>("Saapuneet X asiakkaat");
		cl7.setCellValueFactory(new PropertyValueFactory<>("asiakasMaaraX"));
		TableColumn<Double, TietokantaRaportti> cl8 = new TableColumn<>("Saapuneet Y asiakkaat");
		cl8.setCellValueFactory(new PropertyValueFactory<>("asiakasMaaraY"));
		TableColumn<Double, TietokantaRaportti> cl9 = new TableColumn<>("X asiakkaiden läpimenoaika");
		cl9.setCellValueFactory(new PropertyValueFactory<>("xAsiakkaidenLapimenoaika"));
		TableColumn<Double, TietokantaRaportti> cl10 = new TableColumn<>("Y asiakkaiden läpimenoaika");
		cl10.setCellValueFactory(new PropertyValueFactory<>("yAsiakkaidenLapimenoaika"));
		
		
		//tbv.getColumns().add(cl1);
		tbv.getColumns().add(cl2);
		tbv.getColumns().add(cl3);

		for (TietokantaRaportti dbList : tietokantaRaportti) {
			tbv.getItems().add(dbList);
		}
		VBox vbox = new VBox();
		vbox.getChildren().addAll(tbv);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
