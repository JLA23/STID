package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Base  {
	protected String url, mdp, nom, pseudo, typeCompte;
	protected boolean connecte;
	protected Connection con;
	protected Statement stmt;
	
	public Base(String adresse, String base, String user, String motdepasse){
		con = null;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				url = "jdbc:mysql://"+adresse+"/"+base;
				nom = user;
				mdp = motdepasse;
				con = DriverManager.getConnection(this.url,this.nom,this.mdp);
				stmt = con.createStatement();
				connecte = true;
			}
			catch(Exception e){
				connecte = false;
			}
	}
	
	public boolean isConnecte() {
		return connecte;
	}

	public void setConnecte(boolean connecte) {
		this.connecte = connecte;
	}

	/**
	 * Renvoie l'url de la base
	 * @return l'url de la base sous forme de String
	 */
	public String getUrl(){
		return url;
	}
	
	/**
	 * Renvoie le mot de passe de l'utilisateur sous forme de string
	 * @return le mot de passe en String
	 */
	public String getMdp(){
		return mdp;
	}
	
	/**
	 * Renvoie le nom de l'utilisateur sous forme de string
	 * @return le nom en String
	 */
	public String getNom(){
		return nom;
	}
	
	/**
	 * Renvoie la connexion a la base
	 * @return Connection a la base
	 */
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
	public String [] connection(String pseudo){
		String [] user;
		try{
			String query = "Select u.pseudo, u.mdp, c.type from Users as u, Compte as c where u.TypeCompte = c.id and u.pseudo = '" + pseudo+ "'";
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData metadata = rs.getMetaData();
			int nombreColonnes = metadata.getColumnCount();
			user = new String [nombreColonnes];
			while(rs.next()){
				for(int i = 0; i < nombreColonnes ; i++){
					String nameColone = metadata.getColumnName(i+1);
					user[i] = rs.getString(nameColone);
				}
			}
			if(user[0].isEmpty()){
				user[0] = "vide";
			}
			else{
				this.pseudo = user[0];
				this.typeCompte = user[2];
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			user = new String [2];
			user[0] = "Error";
			if(e.getMessage() == null){
				user[1] = "null";
			}
			else{
				user[1] = e.getMessage();
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
			message = "Ajout effectué avec succée !";
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
			message = "Modification effectué avec succée !";
		}
		catch(Exception e){message = "Error : " + e.getMessage();}
		
		return message;
	}
	
	public String delete(String table, String conditions){
		String message = null;
		try{
			String query = "Delete from " + table + " where " + conditions;
			stmt.executeUpdate(query); 
			message = "Suppression effectué avec succée !";
		}
		catch(Exception e){message = "Error : " + e.getMessage();}
		
		return message;
	}
	
	public void close(){
		try{con.close();} 
		catch (Exception e){System.out.println(e.getMessage());}
	}
}