package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Base  {
	protected String url, mdp, nom, pseudo, typeCompte, adresse, base;
	protected Connection con;
	protected Statement stmt;
	
	public Base(String adresse, String base, String user, String motdepasse){
		con = null;		
		nom = user;
		mdp = motdepasse;
		url = "jdbc:mysql://" + adresse + "/" + base;
		this.adresse = adresse;
		this.base = base;
	}
	
	public String connect(){
		String message = null;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(this.url,this.nom,this.mdp);
				stmt = con.createStatement();
				message = "Connexion �tablie";
			}
			catch(Exception e){
				message = e.getMessage();
			}
		return message;	
	}

	public Connection getCon(){
		return con;
	}
	
	public void setCon(Connection c){
		con = c;
	}

	/**
	 * Renvoie toutes les informations de la Base de donnee
	 * @return une hasmap de hasmap ayant comme cle le nom du fichier.
	 */
	public String typeCompte(String pseudo){
		String user = null;
		try{
			String query = "Select c.type from Users as u, Compte as c where u.TypeCompte = c.id and u.pseudo = '" + pseudo+ "'";
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData metadata = rs.getMetaData();
			while(rs.next()){
					String nameColone = metadata.getColumnName(1);
					user = rs.getString(nameColone);
			}
			if(user.isEmpty()){
				user = "vide";
			}
			else{
				this.pseudo = pseudo;
				this.typeCompte = user;
			}
		}
		catch(Exception e){
			if(e.getMessage() == null){
				user = "null";
			}
			else{
				user = e.getMessage();
			}
		}
		return user;
	}
	
	public ResultSet Select(String colonnes, String tables, String conditions){
		ResultSet rs = null;
		String query = "Select " + colonnes + " from " + tables;
		if(conditions != null){
			query += " where " + conditions;
		}
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return rs;
	}
	
	public String insert(String table, String valeurs){
		String message = null;
		try{
			String query = "Insert into " + table + " values (" + valeurs + ")";
			stmt.executeUpdate(query); 
			message = "Ajout effectu� avec succ�e !";
		}
		catch(Exception e){message = "Error : " + e.getMessage();}
		
		return message;
	}
	
	public String update(String table, String colValeurs, String conditions){
		String message = null;
		try{
			String query = "Update " + table + " SET " + colValeurs;
			if(conditions != null){
				query += " where " + conditions;
			}
			stmt.executeUpdate(query); 
			message = "Modification effectu� avec succ�e !";
		}
		catch(Exception e){message = "Error : " + e.getMessage();}
		
		return message;
	}
	
	public String delete(String table, String conditions){
		String message = null;
		try{
			String query = "Delete from " + table + " where " + conditions;
			stmt.executeUpdate(query); 
			message = "Suppression effectu� avec succ�e !";
		}
		catch(Exception e){message = "Error : " + e.getMessage();}
		
		return message;
	}
	
	public String modifierMDP(String newMdp){
		String message = null;
		try{
			String query = "SET PASSWORD FOR " + pseudo + "@'%' = PASSWORD('" + newMdp + "')";
			stmt.executeUpdate(query); 
			message = "Mot de passe modifi� avec succ�s !";
		}
		catch(Exception e){message = "Error : " + e.getMessage();}
		
		return message;
	}
	
	public String newUtilisateur(String newPseudo, String newMdp, String typeCompte){
		String message = null;
		try{
			String query = null;
			String query2 = null;
			if(typeCompte.equals("Admin")){
				query = "GRANT ALL PRIVILEGES ON *.* TO '" + newPseudo + "'@'%' IDENTIFIED BY '" + newMdp + "' WITH GRANT OPTION";
				query2 = "Insert into Users values('" + newPseudo + "', 1)";
			}
			else{
				query = "GRANT SELECT, UPDATE, RELOAD ON *.* TO '" + newPseudo + "'@'%' IDENTIFIED BY '" + newMdp + "'";
				query2 = "Insert into Users values('" + newPseudo + "', 2)";;
			}
				stmt.executeUpdate(query); 
				stmt.executeUpdate(query2);
				message = "Utilisateur ajout� avec succ�s !";
		}
		catch(Exception e){message = "Error : " + e.getMessage(); System.out.println(e.getMessage());}
		
		return message;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String supprUtilisateur(String pseudo){
		String message = null;
		try{
			String query = "DROP USER '" + pseudo + "'@'%'";
			stmt.executeUpdate(query); 
			String res = delete("Users", "pseudo = '" + pseudo +"'");
			if(res.equals("Suppression effectu� avec succ�e !")){
				message = "Utilisateur supprim�s !";
			}
			
			else{
				message = "Error : suppression de compte �chou�";
			}
			
		}
		catch(Exception e){message = "Error : " + e.getMessage();}
		
		return message;
	}
	
	public String modifPseudo(String newPseudo){
		String message = null;
		try{
			String query = "RENAME USER '"+ pseudo + "'@'%' TO '" + newPseudo + "'@'%'";
			stmt.executeUpdate(query); 
			String res = update("Users", "pseudo = '" + newPseudo + "'", "pseudo = '" + pseudo + "'");
			if(res.equals("Modification effectu� avec succ�e !")){
				message = "Nom utilisateur modifi� !";
			}
			else{
				message = "Error : modification compte �chou�";
			}
		}
		catch(Exception e){message = "Error : " + e.getMessage();}
		
		return message;
	}
	
	public String modifTypeCompte(String pseudo, String compte){
		String message = null;
		try{
			String query = "GRANT ALL PRIVILEGE ON *.* TO '" + pseudo + "'@'%' WITH GRANT OPTION";
			stmt.executeUpdate(query);
			String res = update("Users", "typecompte = 1", "pseudo = '" + pseudo + "'");
			if(res.equals("Ajout effectu� avec succ�e !")){
				message = "Compte utilisateur modifi� !";
			}
			else{
				message = "Error : modification compte �chou�";
			}
			
		}
		catch(Exception e){message = "Error : " + e.getMessage();}
		
		return message;
	}
	
	public void close(){
		try{con.close();} 
		catch (Exception e){System.out.println(e.getMessage());}
	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}
}