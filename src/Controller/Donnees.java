package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
