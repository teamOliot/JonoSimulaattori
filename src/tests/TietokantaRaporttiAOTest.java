package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simu.model.ITietokantaRaporttiDAO;
import simu.model.TietokantaRaportti;
import simu.model.TietokantaRaporttiAO;

class TietokantaRaporttiAOTest {
	
	ITietokantaRaporttiDAO tietokantaraporttiDAO;
	
	@BeforeEach
	void setUp() throws Exception {
		tietokantaraporttiDAO = new TietokantaRaporttiAO();
	}

	@Test
	void testCreateRaportti() {
		TietokantaRaportti raportti = new TietokantaRaportti(500.0789207815674, 15, 25, 0.10,33.0, 22.0, 
				 12, 26, 17, 101.1,183.3 , 376.8, 300.1, 0.2, 0.3, 0.7, 0.6);
		boolean res = tietokantaraporttiDAO.createRaportti(raportti);
		assertEquals(true, res, "Raportin lisääminen onnistui.");
	}
	
	@Test
	void testReadRaportit() {
		TietokantaRaportti[] raportit = tietokantaraporttiDAO.readRaportit();
		assertEquals(0, raportit.length, "Tyhjän tietokanna lukeminen palauttaa väärää tietoa.");
		
		TietokantaRaportti raportti = new TietokantaRaportti(500.0789207815674, 15, 25, 0.10,33.0, 22.0, 
				 12, 26, 17, 101.1,183.3 , 376.8, 300.1, 0.2, 0.3, 0.7, 0.6);
		boolean res = tietokantaraporttiDAO.createRaportti(raportti);
		assertEquals(true, res, "Raportin lisääminen onnistui.");
	}

}
