package simu.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simu.framework.Kello;
import simu.framework.Trace;
import simu.framework.Trace.Level;

class AsiakasTest {

	private static Asiakas asiakasX;
	private static Asiakas asiakasY;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Trace.setTraceLevel(Level.INFO);
		Kello.getInstance().getAika();

		asiakasX = new Asiakas(AsiakasTyyppi.X);
		Kello.getInstance().setAika(5);
		asiakasY = new Asiakas(AsiakasTyyppi.Y);
	}

	/*
	 * @BeforeEach void setUp() { Trace.setTraceLevel(Level.INFO);
	 * Kello.getInstance().getAika(); asiakasX = new Asiakas(AsiakasTyyppi.X); }
	 */

	@Test
	void testAsiakasKonstruktori() {
		// Test asiakasX
		assertEquals(AsiakasTyyppi.X, asiakasX.getTyyppi(), "Asiakastyyppi X on väärin.");
		assertEquals(1, asiakasX.getId(), "Asiakkaan X id on väärin.");
		assertEquals(0, asiakasX.getSaapumisaika(), "Asiakkaan X saapumisaika on väärin.");
		// Test asiakasY
		assertEquals(AsiakasTyyppi.Y, asiakasY.getTyyppi(), "Asiakastyyppi Y on väärin.");
		assertEquals(2, asiakasY.getId(), "Asiakkaan Y id on väärin.");
		assertEquals(5, asiakasY.getSaapumisaika(), "Asiakkaan Y saapumisaika on väärin.");
	}

	@Test
	void testSetPoistumisaika() {
		Kello.getInstance().setAika(10);
		asiakasX.setPoistumisaika(Kello.getInstance().getAika());
		assertEquals(10, asiakasX.getPoistumisaika(), "Asiakkaan X poistumisaika on väärin.");
	}

	@Test
	void testGetSummaXY() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSummaX() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSummaY() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLapimenneetX() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLapimenneetY() {
		fail("Not yet implemented");
	}

	/*
	 * @Test void testRaportti() { fail("Not yet implemented"); }
	 */

}
