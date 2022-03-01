package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;

/**
 * @author Dahlman, Lappi, Laamo
 *
 */

public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long summaXY = 0;
	private static long summaX = 0;
	private static long summaY = 0;
	private static int lapimenneetX = 0;
	private static int lapimenneetY = 0;
	@SuppressWarnings("unused")
	private AsiakasTyyppi tyyppi;
	
	
	/**
	 * @param tyyppi
	 */
	public Asiakas(AsiakasTyyppi tyyppi){
	    id = i;
	    i++;
	    this.tyyppi = tyyppi;
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
	}

	/**
	 * @return double
	 */
	public double getPoistumisaika() {
		return poistumisaika;
	}

	/**
	 * @param poistumisaika
	 */
	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	/**
	 * @return double
	 */
	public double getSaapumisaika() {
		return saapumisaika;
	}

	/**
	 * @param saapumisaika
	 */
	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	/**
	 * @return int
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return AsiakasTyyppi
	 */
	public AsiakasTyyppi getTyyppi() {
		return tyyppi;
	}

	/**
	 * @param tyyppi
	 */
	public void setTyyppi(AsiakasTyyppi tyyppi) {
		this.tyyppi = tyyppi;
	}
	
	/**
	 * @return long
	 */
	public static long getSummaXY() {
		return summaXY;
	}

	/**
	 * @return long
	 */
	public static long getSummaX() {
		return summaX;
	}

	/**
	 * @return long
	 */
	public static long getSummaY() {
		return summaY;
	}
	
	/**
	 * @return int
	 */
	public static int getLapimenneetX() {
		return lapimenneetX;
	}

	/**
	 * @return int
	 */
	public static int getLapimenneetY() {
		return lapimenneetY;
	}
	
	public static void setSummaXY(long summaXY) {
		Asiakas.summaXY = summaXY;
	}

	public static void setSummaX(long summaX) {
		Asiakas.summaX = summaX;
	}

	public static void setSummaY(long summaY) {
		Asiakas.summaY = summaY;
	}

	public static void setLapimenneetX(int lapimenneetX) {
		Asiakas.lapimenneetX = lapimenneetX;
	}

	public static void setLapimenneetY(int lapimenneetY) {
		Asiakas.lapimenneetY = lapimenneetY;
	}

	/**
	 * 
	 */
	public void raportti(){
		Trace.out(Trace.Level.INFO, "\nAsiakas "+id+ " tyyppi: " + this.tyyppi + " valmis! ");
		Trace.out(Trace.Level.INFO, "Asiakas "+id+ " tyyppi: " + this.tyyppi + "   saapui: " +saapumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " tyyppi: " + this.tyyppi +  " poistui: " +poistumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " tyyppi: " + this.tyyppi + " viipyi: " +(poistumisaika-saapumisaika));
		summaXY += (poistumisaika-saapumisaika);
		double keskiarvo = summaXY/id;

		if(this.tyyppi.equals(AsiakasTyyppi.X)) {
			summaX+=(poistumisaika-saapumisaika);
			lapimenneetX++;
		}
		
		if(this.tyyppi.equals(AsiakasTyyppi.Y)) {
			summaY+=(poistumisaika-saapumisaika);
			lapimenneetY++;
		}
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo tähän asti "+ keskiarvo);
	}

}
