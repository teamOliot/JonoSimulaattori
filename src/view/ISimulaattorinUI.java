package view;

public interface ISimulaattorinUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public double getAika();
	public long getViive();
	
	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(double aika);
	
	public void setAsiakasMaaraX(int asiakasMaaraX);
	public void setAsiakasMaaraY(int asiakasMaaraY);
	public void setAsiakasMaara(int asiakasMaara);
	public void setPalvellut(int asiakasMaara);
	
	// Kontrolleri tarvitsee  
	public IVisualisointi getPP1Visualisointi();
	public IVisualisointi getPP2Visualisointi();

}
