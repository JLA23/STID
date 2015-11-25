package Controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import BDD.Base;

public class Donnees {
	
	private Base base;
	
	public Donnees(Base bdd){
		this.base = bdd;
	}
	
	public boolean existPseudo(String pseudo){
		ResultSet rs = base.Select("pseudo", "users", "pseudo = '" + pseudo + "'");
		boolean resultat = false;
		int i = 0;
		try {
			while(rs.next()){
				i ++;
			}
		} catch (SQLException e) {
			resultat = true;
		}
		if(i != 0){
			resultat = true;
		}
		return resultat;
	}
	
	public boolean existClient(String numero){
		ResultSet rs = base.Select("NumClient", "Clients", "NumClient = " + numero);
		boolean resultat = false;
		int i = 0;
		try {
			while(rs.next()){
				i ++;
			}
		} catch (SQLException e) {
			resultat = true;
		}
		if(i != 0){
			resultat = true;
		}
		return resultat;
	}
	
	public boolean existNumDevis(String num){
		ResultSet rs = base.Select("numDevis", "Devis", "numDevis = " + num);
		boolean resultat = false;
		int i = 0;
		try {
			while(rs.next()){
				i ++;
			}
		} catch (SQLException e) {
			resultat = true;
		}
		if(i != 0){
			resultat = true;
		}
		return resultat;
	}
	
	public int newNumDevis(){
		ResultSet rs = base.Select("MAX(NumDevis)", "devis", null);
		int i = 0;
		try {
			while(rs.next()){
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return i;
	}
	
	public Object[][] listeClient(){
		ResultSet rs = base.Select("NumClient, NomClient, Adresse2, Adresse3, Adresse4, Adresse5, Adresse6, Adresse7", "Clients", null);
		Object[][] list = null;
		try {
			rs.last(); 
			int nombreLignes = rs.getRow(); 
			rs.beforeFirst(); 
			list = new Object[nombreLignes][3];
			int i = 0;
			while(rs.next() && i < nombreLignes){
				list[i][0] = rs.getString(1);
				list[i][1] = rs.getString(2);
				list[i][2] = rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " " + rs.getString(8);
				i ++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
		 
	}
	
	public Object[][] listeDevis(){
		ResultSet rs = base.Select("d.numDevis, cdf.numClient, d.lblDevis", "Devis as d, CommandesDevisFacture as cdf", "d.numDevis = cdf.numDevis");
		Object[][] list = null;
		try {
			rs.last(); 
			int nombreLignes = rs.getRow(); 
			rs.beforeFirst(); 
			list = new Object[nombreLignes][3];
			int i = 0;
			while(rs.next() && i < nombreLignes){
				list[i][0] = rs.getString(1);
				list[i][1] = rs.getString(2);
				list[i][2] = rs.getString(3);
				i ++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
		 
	}
	
	public String [] devises(){
		ResultSet rs = base.Select("Symbole", "Devises", null);
		String [] resultat = null;
		try {
			rs.last();
			int nombreLignes = rs.getRow(); 
			rs.beforeFirst();
			if(nombreLignes > 0){
				resultat = new String [nombreLignes];
				int i = 0;
				while(rs.next() && i < nombreLignes){
					resultat[i] = rs.getString(1);
					i++;
				}
			}
		} catch (SQLException e) {
			resultat = new String [] {e.getMessage()};
		} 
		return resultat;
	}
	
	public ArrayList<String> listNumClient(){
		ResultSet rs = base.Select("NumCLient", "Clients", null);
		ArrayList<String> resultat = null;
		try {
				resultat = new ArrayList<String>();
				while(rs.next()){
					resultat.add(rs.getString(1));
				}
		} catch (SQLException e) {
		} 
		return resultat;
	}
	
	public double valeurDevise(String symbole){
		ResultSet rs = base.Select("Valeur", "Devises", "symbole = '" + symbole + "'");
		double res = -1;
		try {
			while(rs.next()){
				res = rs.getDouble(1);
			}
		} catch (SQLException e) {
			res = -1;
		} 
		return res;
	}
	
	public String codeDevise(String symbole){
		ResultSet rs = base.Select("CodeDevise", "Devises", "symbole = '" + symbole + "'");
		String resultat = null;
		try {
			while(rs.next()){
				resultat = rs.getString(1);
			}
		} catch (SQLException e) {
			resultat = e.getMessage();
		} 
		return resultat;
	}
	
	public String [] devis(String numDevis){
		String [] resultat;
		ResultSet rs = base.Select("*", "Devis", "numDevis = " + numDevis);
		
		try {
			ResultSetMetaData metadata = rs.getMetaData();
			int nombreColonnes = metadata.getColumnCount();
			resultat = new String [nombreColonnes+2];
			while(rs.next()){
				for(int i = 0; i< resultat.length-2; i++){
					resultat[i] = rs.getString(i+1);
				}
			}
			rs.close();
			ResultSet rs2 = base.Select("numclient ,symbole", "CommandesDevisFacture as cdf, Devises as d", "d.CodeDevise = cdf.CodeDevise AND numDevis = " + numDevis);
			while(rs2.next()){
				resultat[resultat.length-2] = rs2.getString(1);
				resultat[resultat.length-1] = rs2.getString(2);
			}
		} catch (SQLException e) {
			resultat = new String[] {e.getMessage()};
			System.out.println(e.getMessage());
		}
		return resultat;
	}

}
