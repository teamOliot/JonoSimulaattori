package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;


// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long summaXY = 0;
	private static long summaX = 0;
	private static long summaY = 0;
	@SuppressWarnings("unused")
	private AsiakasTyyppi tyyppi;
	
	
	public Asiakas(AsiakasTyyppi tyyppi){
	    id = i++;
	    this.tyyppi = tyyppi;
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
	}

	public double getPoistumisaika() {
		return poistumisaika;
	}

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	public int getId() {
		return id;
	}
	
	public AsiakasTyyppi getTyyppi() {
		return tyyppi;
	}

	public void setTyyppi(AsiakasTyyppi tyyppi) {
		this.tyyppi = tyyppi;
	}
	
	public static long getSummaXY() {
		return summaXY;
	}

	public static long getSummaX() {
		return summaX;
	}

	public static long getSummaY() {
		return summaY;
	}
	

	public void raportti(){
		Trace.out(Trace.Level.INFO, "\nAsiakas "+id+ " tyyppi: " + this.tyyppi + " valmis! ");
		Trace.out(Trace.Level.INFO, "Asiakas "+id+ " tyyppi: " + this.tyyppi + "   saapui: " +saapumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " tyyppi: " + this.tyyppi +  " poistui: " +poistumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " tyyppi: " + this.tyyppi + " viipyi: " +(poistumisaika-saapumisaika));
		summaXY += (poistumisaika-saapumisaika);
		double keskiarvo = summaXY/id;

		if(this.tyyppi.equals(AsiakasTyyppi.X)) {
			summaX+=(poistumisaika-saapumisaika);
		}
		
		if(this.tyyppi.equals(AsiakasTyyppi.Y)) {
			summaY+=(poistumisaika-saapumisaika);
		}
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo tähän asti "+ keskiarvo);
	}

}
