package controller;

import java.util.ArrayList;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.Asiakas;
import simu.model.OmaMoottori;
import simu.model.Palvelupiste;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV { // UUSI

	private IMoottori moottori;
	private ISimulaattorinUI ui;

	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
	}

	// Moottorin ohjausta:

	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.setSimulointiaika(ui.getAika());
		moottori.setViive(ui.getViive());
		ui.getPP1Visualisointi().tyhjennaNaytto();
		((Thread) moottori).start();
		// ((Thread)moottori).run(); // Ei missään tapauksessa näin. Miksi?
	}

	@Override
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() * 1.10));
	}

	@Override
	public void nopeuta() { // nopeutetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() * 0.9));
	}

	// Simulointitulosten välittämistä käyttöliittymään.
	// Koska FX-ui:n päivitykset tulevat moottorisäikeestä, ne pitää ohjata
	// JavaFX-säikeeseen:

	@Override
	public void naytaLoppuaika(double aika) {
		Platform.runLater(() -> ui.setLoppuaika(aika));
	}

	@Override
	public void visualisoiAsiakas(ArrayList<Palvelupiste> palvelupisteet) {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getPP1Visualisointi().tyhjennaNaytto();
				for (Asiakas asiakas : palvelupisteet.get(0).getJono()) {
					ui.getPP1Visualisointi().uusiAsiakas(asiakas.getTyyppi());
				}
				ui.getPP2Visualisointi().tyhjennaNaytto();
				for (Asiakas asiakas : palvelupisteet.get(1).getJono()) {
					ui.getPP2Visualisointi().uusiAsiakas(asiakas.getTyyppi());
				}
				ui.getPP3Visualisointi().tyhjennaNaytto();
				for (Asiakas asiakas : palvelupisteet.get(2).getJono()) {
					ui.getPP3Visualisointi().uusiAsiakas(asiakas.getTyyppi());
				}
				ui.getPP4Visualisointi().tyhjennaNaytto();
				for (Asiakas asiakas : palvelupisteet.get(3).getJono()) {
					ui.getPP4Visualisointi().uusiAsiakas(asiakas.getTyyppi());
				}
			}
		});
	}

	@Override
	public void naytaAsiakasMaaraX(int asiakasMaaraX) {
		Platform.runLater(() -> ui.setAsiakasMaaraX(asiakasMaaraX));
	}

	@Override
	public void naytaAsiakasMaaraY(int asiakasMaaraY) {
		Platform.runLater(() -> ui.setAsiakasMaaraY(asiakasMaaraY));
	}

	@Override
	public void naytaAsiakasMaara(int asiakasMaara) {
		Platform.runLater(() -> ui.setAsiakasMaara(asiakasMaara));

	}

	@Override
	public void naytaPalvellut(int asiakasMaara) {
		Platform.runLater(() -> ui.setPalvellut(asiakasMaara));

	}

}
