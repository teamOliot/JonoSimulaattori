package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simu.model.Palvelutapahtuma;

class PalvelutapahtumaTest {
	
	 private static final int ASIAKKAANID = 1;
	 private static final double SAAPUMISAIKA = 2.0;
	 private static final double POISTUMISAIKA= 5.0;
	 Palvelutapahtuma tapahtuma = new Palvelutapahtuma(ASIAKKAANID);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		 tapahtuma.setSaapumisaika(SAAPUMISAIKA);
		 tapahtuma.setPoistumisaika(POISTUMISAIKA);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testPalvelutapahtuma() {

	}

	@Test
	final void testGetAsiakkaanId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetAsiakkaanId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetSaapumisaika() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetSaapumisaika() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetPoistumisaika() {
		assertEquals(POISTUMISAIKA, tapahtuma.getPoistumisaika(), "Asiakkaan poistumisaika ei täsmää saapumisaikaan");
	}

	@Test
	final void testSetPoistumisaika() {
		tapahtuma.setPoistumisaika(4.0);
		assertEquals(4.0, tapahtuma.getPoistumisaika(), "Asiakkaan poistumisaika ei täsmää saapumisaikaan");
	}

	@Test
	final void testAsiakkaanKokonaisoleskeluaika() {
		fail("Not yet implemented"); // TODO
	}

}
