package simu.model;

import java.util.List;

public interface ITietokantaRaporttiDAO {
	public abstract boolean createRaportti(TietokantaRaportti raportti);

	//public abstract TietokantaRaportti readRaportti(int id);

	public abstract List<TietokantaRaportti> readRaportit();

	public abstract boolean deleteRaportti(int id);
}
