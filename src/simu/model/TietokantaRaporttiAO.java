package simu.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TietokantaRaporttiAO implements ITietokantaRaporttiDAO {
		SessionFactory sessionFactory = null;

		public TietokantaRaporttiAO() {
			try {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			} catch (Exception e) {
				System.err.println("SessionFactoryn luominen ei onnistunut. " + e);
				System.exit(-1);
			}
		}

		public void finalize() {
			try {
				if (sessionFactory != null) {
					sessionFactory.close();
				}
			} catch (Exception e) {
				System.err.println("SessionFactoryn sulkeminen epäonnistui: " + e);
			}
		}
		
		@Override
		public boolean createRaportti(TietokantaRaportti raportti) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public List<TietokantaRaportti> readRaportit() {
			// TODO Auto-generated method stub
			List<TietokantaRaportti> result;
			try (Session session = sessionFactory.openSession()) {
				result = session.createQuery("from Loppuraportti").getResultList();
			} catch (Exception e) {
				throw e;
			}

			return result;
		}

		@Override
		public boolean deleteRaportti(int id) {
			// TODO Auto-generated method stub
			return false;
		}

/*		@Override
		public boolean createValuutta(Valuutta valuutta) {
			Transaction transaktio = null;
			try (Session istunto = istuntotehdas.openSession()) {
				transaktio = istunto.beginTransaction();
				istunto.save(valuutta);
				transaktio.commit();
				return true;
			} catch (Exception e) {
				if (transaktio != null)
					transaktio.rollback();
				return false;
			}
		}


		@Override
		public Valuutta[] readValuutat() {
			List<Valuutta> result;
			try (Session istunto = istuntotehdas.openSession()) {
				result = istunto.createQuery("from Valuutta").getResultList();
			} catch (Exception e) {
				throw e;
			}
			Valuutta[] returnArray = new Valuutta[result.size()];
			return (Valuutta[]) result.toArray(returnArray);
		}


		@Override
		public boolean deleteValuutta(String tunnus) {
			Transaction transaktio = null;
			try (Session istunto = istuntotehdas.openSession()) {
				transaktio = istunto.beginTransaction();
				Valuutta valuutta = readValuutta(tunnus);
				istunto.delete(valuutta);
				transaktio.commit();
				return true;
			} catch (Exception e) {
				if (transaktio != null)
					transaktio.rollback();
				return false;
			}
		}*/


}
