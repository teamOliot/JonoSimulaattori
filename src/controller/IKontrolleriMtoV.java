package controller;

public interface IKontrolleriMtoV {
	
		// Rajapinta, joka tarjotaan moottorille:
		
		public void naytaLoppuaika(double aika);
		public void visualisoiAsiakas();
		public void naytaAsiakasMaaraX(int asiakasMaaraX);
		public void naytaAsiakasMaaraY(int asiakasMaaraY);
		public void naytaAsiakasMaara(int asiakasMaara);
		public void naytaPalvellut(int asiakasMaara);
}
