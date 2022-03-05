package simu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controller.IKontrolleriMtoV;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

/**
 * @author Dahlman, Laamo, Lappi
 *
 */
public class OmaMoottori extends Moottori {
	static ITietokantaRaporttiDAO tietokantaraporttiDAO = new TietokantaRaporttiAO();

	private Saapumisprosessi saapumisprosessiX;
	private Saapumisprosessi saapumisprosessiY;
	private int asiakasMaaraX;
	private int asiakasMaaraY;
	private ArrayList<Palvelupiste> kaikkiPalvelupisteet;
	private double xParam = 5;
	private double yParam = 5;
	
	/**
	 * @param kontrolleri is controller object 
	 */
	public OmaMoottori(IKontrolleriMtoV kontrolleri) {
		super(kontrolleri);

		palvelupisteet = new Palvelupiste[4];

		palvelupisteet[0] = new Palvelupiste(new Normal(2, 6), tapahtumalista, TapahtumanTyyppi.PP1DEP, "Lipuntarkastus");
		palvelupisteet[1] = new Palvelupiste(new Normal(3, 6), tapahtumalista, TapahtumanTyyppi.PP2DEP, "Turvatarkastus");
		palvelupisteet[2] = new Palvelupiste(new Normal(6, 6), tapahtumalista, TapahtumanTyyppi.PP3DEP,"Kaljatiski");
		palvelupisteet[3] = new Palvelupiste(new Normal(12, 6), tapahtumalista, TapahtumanTyyppi.PP4DEP, "Vessa");
		
		kaikkiPalvelupisteet = new ArrayList<>(); 
		for (Palvelupiste palvelupiste : palvelupisteet) {
			kaikkiPalvelupisteet.add(palvelupiste);
		}

	}

	/**
	 * Method alustukset() generates first customers to queue.
	 */
	@Override
	protected void alustukset() {
		saapumisprosessiX = new Saapumisprosessi(new Negexp(xParam, 5), tapahtumalista, TapahtumanTyyppi.XARR);
		saapumisprosessiY = new Saapumisprosessi(new Negexp(yParam, 5), tapahtumalista, TapahtumanTyyppi.YARR);
		
		saapumisprosessiX.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
		saapumisprosessiY.generoiSeuraava();
		
	}

	
	/**
	 * Method, that executes events (B-type) and moves customers from service point to another.
	 * Shows and visualizes customers and amount of customer types (x,y).
	 */
	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat
		Asiakas a;
		switch (t.getTyyppi()) {

		case XARR:
			palvelupisteet[0].lisaaJonoon(new Asiakas(AsiakasTyyppi.X));
			saapumisprosessiX.generoiSeuraava();
			asiakasMaaraX++;
			kontrolleri.naytaAsiakasMaaraX(asiakasMaaraX);
			kontrolleri.naytaAsiakasMaara((asiakasMaaraX+asiakasMaaraY));
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
			break;
		case YARR:
			palvelupisteet[0].lisaaJonoon(new Asiakas(AsiakasTyyppi.Y));
			saapumisprosessiY.generoiSeuraava();
			asiakasMaaraY++;
			kontrolleri.naytaAsiakasMaaraY(asiakasMaaraY);
			kontrolleri.naytaAsiakasMaara((asiakasMaaraX+asiakasMaaraY));
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
			break;
		case PP1DEP:
			a = palvelupisteet[0].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
			
			break;
		case PP2DEP:
			a = palvelupisteet[1].otaJonosta();
			if (a.getTyyppi().equals(AsiakasTyyppi.X)) {
				palvelupisteet[2].lisaaJonoon(a);
				kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
				break;
			}
			palvelupisteet[3].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
			break;
		case PP3DEP:
			a = palvelupisteet[2].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			a.raportti();
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
			kontrolleri.naytaPalvellutX(Asiakas.getLapimenneetX());
			kontrolleri.naytaPalvellutY(Asiakas.getLapimenneetY());
			kontrolleri.naytaPalvellut(palvelupisteet[2].getPalvellutAsiakkaat());
			//kontrolleri.visualisoiAsiakkaanPoisto();
			break;
		case PP4DEP:
			a = palvelupisteet[3].otaJonosta();
			palvelupisteet[2].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakas(kaikkiPalvelupisteet);
			break;
			
		}
		
	}
	

	@Override
	protected void tulokset() {
		//
		double simulaationSuoritusteho = palvelupisteet[2].getPalvellutAsiakkaat()/Kello.getInstance().getAika()*100;
		
		double jononPituusPP1 = palvelupisteet[0].getJononPituus();
		double jononPituusPP2 = palvelupisteet[1].getJononPituus();
		double jononPituusPP3 = palvelupisteet[2].getJononPituus();
		double jononPituusPP4 = palvelupisteet[3].getJononPituus();
		
		//Simuloinnin kokonaisaika T
		//System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset omamoottori: ");
		System.out.println("Omamoottori: Saapuneiden X asiakkaiden määrä "+Asiakas.getSaapuneetX());
		System.out.println("Omamoottori: Saapuneiden Y asiakkaiden määrä "+Asiakas.getSaapuneetY());
		System.out.println("Omamoottori: Saapuneiden asiakkaiden yht määrä on: " + Asiakas.getSaapuneetAsiakkaatYht());
		System.out.println("Omamoottori: Kaljapisteellä käyneet asiakkaat: " + palvelupisteet[2].getPalvellutAsiakkaat());
		//Koko simulaation suoritusteho X=C/T
		System.out.println("Omamoottori: Koko simulaation suoritusteho on: "+ simulaationSuoritusteho);
		
		for (Palvelupiste palvelupiste : palvelupisteet) {
			System.out.println("Omamoottori: Palvelupisteen "+palvelupiste.getPalvelupisteenNimi()+" kokonaisoleskeluaika on :"+palvelupiste.getKokonaisOleskeluaika());
			// jononpituus
			System.out.println("Omamoottori: Palvelupisteen "+palvelupiste.getPalvelupisteenNimi()+" jonon pituus "+palvelupiste.getJononPituus());
			// U=B/T
			System.out.println("Omamoottori: Palvelupisteen "+palvelupiste.getPalvelupisteenNimi()+" käyttöaste on: "+palvelupiste.getPalvelupisteenKayttoaste());
			System.out.println(palvelupiste);
		}
		
		//Keskimääräinen läpimenoaika R=W/C
		System.out.println("Omamoottori: X asiakkaiden keskimääräinen läpimenoaika "+Asiakas.getXAsiakkaidenLapimenoaika());
		System.out.println("Omamoottori: Y asiakkaiden keskimääräinen läpimenoaika "+Asiakas.getYAsiakkaidenLapimenoaika());
		System.out.println("Omamoottori: kaikkien keskmääräinen läpimenoaika "+Asiakas.getAsiakkaidenLapimenoaikaYht());
		
		kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());
		
		//Kokonaisoleskeluaika kaikissa palvelupisteissä yhteensä W
		
		//Keskimääräinen jononpituus N = W/T
		
		System.out.println("Omamoottori: XParametri: " + xParam + " YParametri: " + yParam);
		
		// raportin luonti
		
		TietokantaRaportti raportti = new TietokantaRaportti(Kello.getInstance().getAika(), xParam, yParam, simulaationSuoritusteho,Asiakas.getSaapuneetX(), Asiakas.getLapimenneetY(), 
				Asiakas.getXAsiakkaidenLapimenoaika(), Asiakas.getYAsiakkaidenLapimenoaika(), Asiakas.getAsiakkaidenLapimenoaikaYht(), 
				palvelupisteet[0].getKokonaisOleskeluaika(), palvelupisteet[1].getKokonaisOleskeluaika(), palvelupisteet[2].getKokonaisOleskeluaika(), palvelupisteet[3].getKokonaisOleskeluaika(),
				jononPituusPP1, jononPituusPP2, jononPituusPP3, jononPituusPP4, Asiakas.getLapimenneetX(), Asiakas.getLapimenneetY());
		
		
		System.out.println("Omamoottori: "+raportti.toString());
		kontrolleri.naytaLoppuraportti(raportti);
		boolean res = tietokantaraporttiDAO.createRaportti(raportti);
		//System.out.println("Omamoottori: toteutuiko: " + res);
		
		TietokantaRaportti[] raportit = tietokantaraporttiDAO.readRaportit();
		for (TietokantaRaportti r : raportit) {
			System.out.println("Omamoottori: tämä tulee tietokannasta " + r);
		}
		
		
	}

	@Override
	public void setXParam(double xParam) {
		this.xParam = xParam;
	}

	@Override
	public void setYParam(double yParam) {
		this.yParam = yParam;
	}
	
}
