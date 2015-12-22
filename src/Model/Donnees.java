package Model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import BDD.Base;

public class Donnees {

	private Base base;

	public Donnees(Base bdd) {
		this.base = bdd;
	}

	public boolean existPseudo(String pseudo) {
		ResultSet rs = base.Select("pseudo", "users", "pseudo = '" + pseudo + "'");
		boolean resultat = false;
		int i = 0;
		try {
			while (rs.next()) {
				i++;
			}
		} catch (SQLException e) {
			resultat = true;
		}
		if (i != 0) {
			resultat = true;
		}
		return resultat;
	}

	public boolean existClient(String numero) {
		ResultSet rs = base.Select("NumClient", "Clients", "NumClient = " + numero);
		boolean resultat = false;
		int i = 0;
		try {
			while (rs.next()) {
				i++;
			}
		} catch (SQLException e) {
			resultat = true;
		}
		if (i != 0) {
			resultat = true;
		}
		return resultat;
	}

	public boolean existNumDevis(String num) {
		ResultSet rs = base.Select("numDevis", "Devis", "numDevis = " + num);
		boolean resultat = false;
		int i = 0;
		try {
			while (rs.next()) {
				i++;
			}
		} catch (SQLException e) {
			resultat = true;
		}
		if (i != 0) {
			resultat = true;
		}
		return resultat;
	}

	public boolean existNumClient(String num) {
		ResultSet rs = base.Select("numClient", "clients", "numClient = " + num);
		boolean resultat = false;
		int i = 0;
		try {
			while (rs.next()) {
				i++;
			}
		} catch (SQLException e) {
			resultat = true;
		}
		if (i != 0) {
			resultat = true;
		}
		return resultat;
	}

	public int newNumDevis() {
		ResultSet rs = base.Select("MAX(NumDevis)", "devis", null);
		int i = 0;
		try {
			while (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return i + 1;
	}

	public int newNumClient() {
		ResultSet rs = base.Select("MAX(NumClient)", "clients", null);
		int i = 0;
		try {
			while (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return i + 1;
	}

	public Object[][] listeClient() {
		ResultSet rs = base.Select("NumClient, NomClient, Adresse2, Adresse3, Adresse4, Adresse5, Adresse6, Adresse7",
				"Clients", null);
		Object[][] list = null;
		try {
			rs.last();
			int nombreLignes = rs.getRow();
			rs.beforeFirst();
			list = new Object[nombreLignes][3];
			int i = 0;
			while (rs.next() && i < nombreLignes) {
				list[i][0] = rs.getString(1);
				list[i][1] = rs.getString(2);
				list[i][2] = rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6)
						+ " " + rs.getString(7) + " " + rs.getString(8);
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;

	}

	public Object[][] listeDevis() {
		ResultSet rs = base.Select("d.numDevis, d.numClient, d.lblDevis", "Devis as d", null);
		Object[][] list = null;
		try {
			rs.last();
			int nombreLignes = rs.getRow();
			rs.beforeFirst();
			list = new Object[nombreLignes][3];
			int i = 0;
			while (rs.next() && i < nombreLignes) {
				list[i][0] = rs.getString(1);
				list[i][1] = rs.getString(2);
				list[i][2] = rs.getString(3);
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;

	}

	public HashMap<String, String[]> devises() {
		ResultSet rs = base.Select("*", "Devises", null);
		HashMap<String, String[]> resultat = null;
		try {
			resultat = new HashMap<String, String[]>();
			while (rs.next()) {
				String cle = rs.getString(3);
				String[] valeur = new String[3];
				valeur[0] = rs.getString(1);
				valeur[1] = rs.getString(2);
				valeur[2] = rs.getString(4);
				resultat.put(cle, valeur);
			}
		} catch (SQLException e) {
			resultat = new HashMap<String, String[]>();
			resultat.put("Error", new String[] { e.getMessage() });
		}
		return resultat;
	}

	public ArrayList<String> listNumClient() {
		ResultSet rs = base.Select("NumCLient", "Clients", null);
		ArrayList<String> resultat = null;
		try {
			resultat = new ArrayList<String>();
			while (rs.next()) {
				resultat.add(rs.getString(1));
			}
		} catch (SQLException e) {
		}
		return resultat;
	}

	public double valeurDevise(String symbole) {
		ResultSet rs = base.Select("Valeur", "Devises", "symbole = '" + symbole + "'");
		double res = -1;
		try {
			while (rs.next()) {
				res = rs.getDouble(1);
			}
		} catch (SQLException e) {
			res = -1;
		}
		return res;
	}

	public String codeDevise(String symbole) {
		ResultSet rs = base.Select("CodeDevise", "Devises", "symbole = '" + symbole + "'");
		String resultat = null;
		try {
			while (rs.next()) {
				resultat = rs.getString(1);
			}
		} catch (SQLException e) {
			resultat = e.getMessage();
		}
		return resultat;
	}

	public String[] devis(String numDevis) {
		String[] resultat;
		ResultSet rs = base.Select("*", "Devis", "numDevis = " + numDevis);

		try {
			ResultSetMetaData metadata = rs.getMetaData();
			int nombreColonnes = metadata.getColumnCount();
			resultat = new String[nombreColonnes];
			while (rs.next()) {
				for (int i = 0; i < resultat.length; i++) {
					resultat[i] = rs.getString(i + 1);
				}
			}
			rs.close();
		} catch (SQLException e) {
			resultat = new String[] { e.getMessage() };
			System.out.println(e.getMessage());
		}
		return resultat;
	}

	public boolean lieeCommande(String numDevis) {
		ResultSet rs = base.Select("numCommande", "Devis", "numDevis = " + numDevis);
		boolean resultat = false;
		int i = 0;
		try {
			while (rs.next()) {
				i++;
			}
		} catch (SQLException e) {
			resultat = true;
		}
		if (i != 0) {
			resultat = true;
		}
		return resultat;
	}

	public LinkedHashMap<String, Object[]> modesPaiements() {
		LinkedHashMap<String, Object[]> modes = new LinkedHashMap<String, Object[]>();
		ResultSet rs = base.Select("*", "modepaiement", null);
		try {
			while (rs.next()) {
				Object[] ob = new Object[2];
				String titre = rs.getString(2);
				ob[0] = rs.getString(1);
				ob[1] = false;
				modes.put(titre, ob);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			modes = null;
		}
		return modes;
	}

	public LinkedHashMap<String, String[]> taux() {
		ResultSet rs = base.Select("*", "taux", null);
		LinkedHashMap<String, String[]> resultat = null;
		try {
			resultat = new LinkedHashMap<String, String[]>();
			while (rs.next()) {
				String cle = rs.getString(3);
				String[] valeur = new String[2];
				valeur[0] = rs.getString(1);
				valeur[1] = rs.getString(2);
				resultat.put(cle, valeur);
			}
		} catch (SQLException e) {
			resultat = new LinkedHashMap<String, String[]>();
			resultat.put("Error", new String[] { e.getMessage() });
		}
		return resultat;
	}

	public String[] client(String numero) {
		String[] resultat;
		ResultSet rs = base.Select("*", "Clients", "numClient = " + numero);

		try {
			ResultSetMetaData metadata = rs.getMetaData();
			int nombreColonnes = metadata.getColumnCount();
			resultat = new String[nombreColonnes];
			while (rs.next()) {
				for (int i = 0; i < resultat.length; i++) {
					resultat[i] = rs.getString(i + 1);
				}
			}
			rs.close();
		} catch (SQLException e) {
			resultat = new String[] { e.getMessage() };
			System.out.println(e.getMessage());
		}
		return resultat;
	}

	public String[] modeClient(String numero) {
		String[] resultat;
		ResultSet rs = base.Select("ModePaiement", "paiementClient", "numClient = " + numero);

		try {
			rs.last();
			int nombreLignes = rs.getRow();
			rs.beforeFirst();
			resultat = new String[nombreLignes];
			int i = 0;
			while (rs.next()) {
				resultat[i] = rs.getString(1);
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			resultat = new String[] { e.getMessage() };
			System.out.println(e.getMessage());
		}
		return resultat;
	}

}
