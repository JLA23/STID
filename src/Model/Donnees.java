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
/**
 * Constructeur de Donnees
 * @param bdd
 */
	public Donnees(Base bdd) {
		this.base = bdd;
	}

	public boolean exist(String table, String colonne, String condition) {
		ResultSet rs = base.Select(colonne, table, condition);
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
	
	
	public String searchNumCommandeClient(String num) {
		ResultSet rs = base.Select("numCommande", "Commandes", "CdeComClient = '" + num + "'");
		String resultat = "";
		try {
			while (rs.next()) {
				resultat = rs.getString(1);
			}
		} catch (SQLException e) {
			resultat = null;
		}
		return resultat;
	}
	
	public String searchTerme(String num){
		ResultSet rs = base.Select("numindice", "Termes", "NumFacture is null AND Numcommande = '" + num + "'");
		String resultat = "";
		try {
			while (rs.next()) {
				resultat = rs.getString(1);
			}
		} catch (SQLException e) {
			resultat = null;
		}
		return resultat;
	}

	public int newNum(String table, String colonne, String condition) {
		ResultSet rs = base.Select("MAX("+colonne+")", table, condition);
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

	public Object[][] liste(String colonnes, String tables, String conditions){
		ResultSet rs = base.Select(colonnes, tables, conditions);
		Object[][] list = null;
		try {
			ResultSetMetaData metadata = rs.getMetaData(); 
			int nombreColonnes = metadata.getColumnCount();
			rs.last();
			int nombreLignes = rs.getRow();
			rs.beforeFirst();
			list = new Object[nombreLignes][nombreColonnes];
			int i = 0;
			while (rs.next() && i < nombreLignes) {
				for(int j = 0; j < nombreColonnes; j++){
					list[i][j] = rs.getString(j+1);
				}
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
	
	public HashMap<String, String[]> modesPaiements(String numClient) {
		ResultSet rs = base.Select("m.id, m.mode", "modepaiement as m, paiementclient as p", "p.numClient = " + numClient + " and p.modePaiement = m.id");
		HashMap<String, String[]> resultat = null;
		try {
			resultat = new HashMap<String, String[]>();
			while (rs.next()) {
				String cle = rs.getString(2);
				String[] valeur = new String[1];
				valeur[0] = rs.getString(1);
				resultat.put(cle, valeur);
			}
		} catch (SQLException e) {
			resultat = new HashMap<String, String[]>();
			resultat.put("Error", new String[] { e.getMessage() });
		}
		return resultat;
	}

	public ArrayList<String> listNumClient() {
		ResultSet rs = base.Select("NumClient", "Clients", null);
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
	
	public String[] fiche(String colonnes, String table, String conditions) {
		String[] resultat = null;
		ResultSet rs = base.Select(colonnes, table, conditions);

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
			System.out.println(e.getMessage());
		}
		return resultat;
	}

	public boolean existPlusieur(String colonnes, String tables, String conditions) {
		ResultSet rs = base.Select(colonnes, tables, conditions);
		boolean resultat = false;
		int i = 0;
		try {
			while (rs.next()) {
				if(!rs.getString(1).equals("null")){
					i++;
				}
			}
		} catch (SQLException e) {
			resultat = true;
		}
		if (i > 1) {
			resultat = true;
		}
		return resultat;
	}

	public boolean lier(String colonnes, String tables, String conditions) {
		ResultSet rs = base.Select(colonnes, tables, conditions);
		boolean resultat = false;
		int i = 0;
		
		try {
			while (rs.next()) {
				if(rs.getString(1) != null){
					i++;
				}
			}
		} catch (SQLException e) {
			resultat = true;
		}
		if (i != 0) {
			resultat = true;
		}
		return resultat;
	}
	
	public boolean noLier(String colonnes, String tables, String conditions) {
		ResultSet rs = base.Select(colonnes, tables, conditions);
		boolean resultat = false;
		int i = 0;
		try {
			while (rs.next()) {
				if(!rs.getString(1).equals("null")){
					i++;
				}
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
	
	public LinkedHashMap<String, String[]> type() {
		ResultSet rs = base.Select("*", "typepers", null);
		LinkedHashMap<String, String[]> resultat = null;
		try {
			resultat = new LinkedHashMap<String, String[]>();
			while (rs.next()) {
				String cle = rs.getString(2);
				String[] valeur = new String[1];
				valeur[0] = rs.getString(1);
				resultat.put(cle, valeur);
			}
		} catch (SQLException e) {
			resultat = new LinkedHashMap<String, String[]>();
			resultat.put("Error", new String[] { e.getMessage() });
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

	public double somme(String colonne, String table, String[] numdevis) {
		String query = "";
		for (int i = 0; i < numdevis.length; i++) {
			query += "numdevis = " + numdevis[i];
			if ((i + 1) < numdevis.length) {
				query += " || ";
			}
		}
		ResultSet rs = base.Select("Sum(Round(" + colonne + ", 2))", "devis", query);
		double resultat = 0;
		try {
			while (rs.next()) {
				resultat = rs.getDouble(1);
			}
		} catch (SQLException e) {
			resultat = -1;
		}
		return resultat;
	}
	
	public String max(String colonne, String table){
		ResultSet rs = base.Select("MAX("+colonne+")", table, null);
		int i = 0;
		try {
			while (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return i + "";
	}

}
