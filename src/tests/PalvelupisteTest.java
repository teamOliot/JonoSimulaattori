package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eduni.distributions.Normal;
import simu.framework.*;
import simu.model.*;

class PalvelupisteTest {
	
	Tapahtumalista serviceList;
	Asiakas customer = new Asiakas (AsiakasTyyppi.X);
	LinkedList<Asiakas> queueList = new LinkedList<Asiakas>();
	Palvelupiste servicepoint = new Palvelupiste(new Normal(10, 6), serviceList, TapahtumanTyyppi.PP1DEP, "CHECKPOINT");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {

	}
	
    @AfterEach
    public void tearDown() {
    	queueList.clear();
    }

	@Test
	public void testPalvelupiste() {
		assertFalse(servicepoint.equals(null), "Olion luominen ei onnistunut, olio palauttaa NULL eli on tyhjä.");
	}


	@Test
	public void testLisaaJonoon() {
		System.out.println(queueList.add(customer));
		assertEquals(customer, queueList.getFirst(),"Listalle lisätty olio ei vastaa listalla olevaa oliota");
	}

	@Test
	final void testOtaJonosta() {
		System.out.println(queueList.add(customer));
		assertEquals(queueList.getFirst(), queueList.peek(),"Listalle lisätty olio ei vastaa listalla olevaa oliota");
	}

}
