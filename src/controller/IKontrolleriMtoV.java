package controller;

import java.util.ArrayList;

import simu.model.Palvelupiste;

public interface IKontrolleriMtoV {
	
		// Rajapinta, joka tarjotaan moottorille:
		
		public void naytaLoppuaika(double aika);
		public void visualisoiAsiakas(ArrayList<Palvelupiste> palvelupisteet);
		public void naytaAsiakasMaaraX(int asiakasMaaraX);
		public void naytaAsiakasMaaraY(int asiakasMaaraY);
		public void naytaAsiakasMaara(int asiakasMaara);
		
		public void naytaPalvellutX(int asiakasMaaraX);
		public void naytaPalvellutY(int asiakasMaaraY);
		public void naytaPalvellut(int asiakasMaara);
}
