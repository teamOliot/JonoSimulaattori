package simu.model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public class Palvelupiste {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
	
	//JonoStartegia strategia; //optio: asiakkaiden järjestys
	
	private boolean varattu = false;
	private int palvellutAsiakkaat;
	private double kokonaisPalveluaika;
	private String palvelupisteenNimi;


	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, String palvelupisteenNimi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.palvelupisteenNimi = palvelupisteenNimi;
	}


	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		
	}

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		palvellutAsiakkaat++;
		varattu = false;
		return jono.poll();
	}

	public int getPalvellutAsiakkaat() {
		return palvellutAsiakkaat;
	}


	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
			Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
			
			varattu = true;
			double palveluaika = generator.sample();
			tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
			kokonaisPalveluaika+=palveluaika;
	}


	public double getKokonaispalveluAika() {
		return kokonaisPalveluaika;
	}


	public boolean onVarattu(){
		return varattu;
	}


	public boolean onJonossa(){
		return jono.size() != 0;
	}

	public LinkedList<Asiakas> getJono() {
		return jono;
	}


	@Override
	public String toString() {
		return "Palvelupisteen nimi: " + palvelupisteenNimi+", palvelupisteen palvellut asiakkaat: " + palvellutAsiakkaat + ", kokonaispalveluaika: " + kokonaisPalveluaika+".";
	}

}
