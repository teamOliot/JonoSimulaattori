package simu.model;

import java.util.stream.Collectors;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori {

	private Saapumisprosessi saapumisprosessiX;
	private Saapumisprosessi saapumisprosessiY;
	private int xAsiakasMaara;
	private int yAsiakasMaara;

	public OmaMoottori() {

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
			xAsiakasMaara++;
			break;
		case YARR:
			palvelupisteet[0].lisaaJonoon(new Asiakas(AsiakasTyyppi.Y));
			saapumisprosessiY.generoiSeuraava();
			yAsiakasMaara++;
			break;
		case PP1DEP:
			a = palvelupisteet[0].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
		case PP2DEP:
			a = palvelupisteet[1].otaJonosta();
			if (a.getTyyppi().equals(AsiakasTyyppi.X)) {
				System.out.println("toimiix");
				palvelupisteet[2].lisaaJonoon(a);
				break;
			}
			System.out.println("toimii yyy");
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
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika()); // onko tämä T?
		System.out.println("Tulokset:");
		System.out.println("Saapuneiden X asiakkaiden määrä on: " + xAsiakasMaara);
		System.out.println("Saapuneiden Y asiakkaiden määrä on: " + yAsiakasMaara);
		System.out.println("Saapuneiden asiakkaiden yht määrä on: " + (yAsiakasMaara + xAsiakasMaara));
		System.out.println("Kaljajonossa käyneet asiakkaat: " + palvelupisteet[2].getPalvellutAsiakkaat());
		System.out.println("Koko simulaation suoritusteho on: "+(palvelupisteet[2].getPalvellutAsiakkaat()/Kello.getInstance().getAika())); // Kello, miten pitäisi laskea?
		for (Palvelupiste palvelupiste : palvelupisteet) {
			System.out.println(palvelupiste);
		}

	}
}
