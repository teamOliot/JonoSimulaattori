package simu.model;

import java.util.stream.Collectors;

import controller.IKontrolleriMtoV;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori {

	private Saapumisprosessi saapumisprosessiX;
	private Saapumisprosessi saapumisprosessiY;
	private int asiakasMaaraX;
	private int asiakasMaaraY;

	public OmaMoottori(IKontrolleriMtoV kontrolleri) {
		super(kontrolleri);

		palvelupisteet = new Palvelupiste[4];

		palvelupisteet[0] = new Palvelupiste(new Normal(10, 6), tapahtumalista, TapahtumanTyyppi.PP1DEP, "Lipuntarkastus");
		palvelupisteet[1] = new Palvelupiste(new Normal(10, 10), tapahtumalista, TapahtumanTyyppi.PP2DEP, "Turvatarkastus");
		palvelupisteet[2] = new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.PP3DEP,"Kaljatiski");
		palvelupisteet[3] = new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.PP4DEP, "Vessa");

		saapumisprosessiX = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.XARR);
		saapumisprosessiY = new Saapumisprosessi(new Negexp(20, 5), tapahtumalista, TapahtumanTyyppi.YARR);

	}

	@Override
	protected void alustukset() {
		saapumisprosessiX.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
		saapumisprosessiY.generoiSeuraava();
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat

		Asiakas a;
		switch (t.getTyyppi()) {

		case XARR:
			palvelupisteet[0].lisaaJonoon(new Asiakas(AsiakasTyyppi.X));
			saapumisprosessiX.generoiSeuraava();
			asiakasMaaraX++;
			kontrolleri.naytaAsiakasMaaraX(asiakasMaaraX);
			break;
		case YARR:
			palvelupisteet[0].lisaaJonoon(new Asiakas(AsiakasTyyppi.Y));
			saapumisprosessiY.generoiSeuraava();
			asiakasMaaraY++;
			kontrolleri.naytaAsiakasMaaraY(asiakasMaaraY);
			break;
		case PP1DEP:
			a = palvelupisteet[0].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
		case PP2DEP:
			a = palvelupisteet[1].otaJonosta();
			if (a.getTyyppi().equals(AsiakasTyyppi.X)) {
				palvelupisteet[2].lisaaJonoon(a);
				break;
			}
			palvelupisteet[3].lisaaJonoon(a);
			break;
		case PP3DEP:
			a = palvelupisteet[2].otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			a.raportti();
			break;
		case PP4DEP:
			a = palvelupisteet[3].otaJonosta();
			palvelupisteet[2].lisaaJonoon(a);
			break;
		}
	}
	

	@Override
	protected void tulokset() {
		//Simuloinnin kokonaisaika T
		//System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset:");
		System.out.println("Saapuneiden X asiakkaiden määrä on: " + asiakasMaaraX);
		System.out.println("Saapuneiden Y asiakkaiden määrä on: " + asiakasMaaraY);
		System.out.println("Saapuneiden asiakkaiden yht määrä on: " + (asiakasMaaraY + asiakasMaaraX));
		System.out.println("Kaljapisteellä käyneet asiakkaat: " + palvelupisteet[2].getPalvellutAsiakkaat());
		//Koko simulaation suoritusteho X=C/T
		System.out.println("Koko simulaation suoritusteho on: "+(palvelupisteet[2].getPalvellutAsiakkaat()/Kello.getInstance().getAika()));
		for (Palvelupiste palvelupiste : palvelupisteet) {
			System.out.println(palvelupiste);
		}
		//Keskimääräinen läpimenoaika R=W/C
		System.out.println("X asiakkaiden keskimääräinen läpimenoaika"+(Asiakas.getSummaX()/asiakasMaaraX));
		System.out.println("Y asiakkaiden keskimääräinen läpimenoaika"+(Asiakas.getSummaY()/asiakasMaaraY));
		
		// UUTTA graafista
		// Palvellut asiakkaat
		kontrolleri.naytaPalvellut(palvelupisteet[2].getPalvellutAsiakkaat());
		//Saapuneiden asiakkaiden lukumäärä A
		kontrolleri.naytaAsiakasMaara((asiakasMaaraX+asiakasMaaraY));
		kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());
		
		//Kokonaisoleskeluaika kaikissa palvelupisteissä yhteensä W
		
		//Keskimääräinen jononpituus N = W/T
		

	}
}
