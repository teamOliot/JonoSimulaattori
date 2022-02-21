package simu.model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava


/**
 * @author Dahlman, Laamo, Lappi
 *
 */
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


	/**
	 * @param generator provides a double value according to the distribution it relies on, this is for generating service times.
	 * @param tapahtumalista is a list that keeps track on all events that should executed and time that they will be executed.
	 * @param tyyppi enum for type of event.
	 * @param palvelupisteenNimi is a name for service point.
	 */
	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, String palvelupisteenNimi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.palvelupisteenNimi = palvelupisteenNimi;
		
	}


	/**
	 * @param a is customer that will be added to list. First from list will be given a service next.
	 */
	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		
	}

	/**
	 * @return will return and remove first customer from queue (jono) list.
	 */
	public Asiakas otaJonosta(){
		palvellutAsiakkaat++;
		varattu = false;
		return jono.poll();
		
	}

	/**
	 * @return returns amount of customers that have been already served.
	 */
	public int getPalvellutAsiakkaat() {
		return palvellutAsiakkaat;
	}


	/**
	 * method aloitaPalvelu() starts a new service. Customer stays in queue while been served.
	 */
	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
			Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
			
			varattu = true;
			double palveluaika = generator.sample();
			tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
			kokonaisPalveluaika+=palveluaika;
			
	}

	
	/**
	 * @return the time how long entire service has lasted.
	 */
	public double getKokonaispalveluAika() {
		return kokonaisPalveluaika;
		
	}


	/**
	 * @return the status of service point - busy or available for next customer.
	 */
	public boolean onVarattu(){
		return varattu;
	}


	/**
	 * @return the status of queue, if there is still customers in line or not.
	 */
	public boolean onJonossa(){
		return jono.size() != 0;
	}

	/**
	 * @return the queue list of customers. 
	 */
	public LinkedList<Asiakas> getJono() {
		return jono;
	}


	@Override
	public String toString() {
		return "Palvelupisteen nimi: " + palvelupisteenNimi+", palvelupisteen palvellut asiakkaat: " + palvellutAsiakkaat + ", kokonaispalveluaika: " + kokonaisPalveluaika+".";
	}

}
