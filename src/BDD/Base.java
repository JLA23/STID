package BDD;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Base  {
	protected String url, mdp, nom;
	protected boolean connecte;
	protected Connection con;
	protected Statement stmt;
	
	public Base(String Fichier){
		con = null;
		File fichier = new File("bdd/"+Fichier);
		if(fichier.exists()){
			try{
				Class.forName("org.sqlite.JDBC");
				url = "jdbc:sqlite:bdd/"+Fichier;
				nom = null;
				mdp = null;
				con = DriverManager.getConnection(this.url,this.nom,this.mdp);
				stmt = con.createStatement();
				connecte = true;
			}
			catch(Exception e){
				connecte = false;
			}
		}
		else{
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
		}
		catch(Exception e){
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
	
	public void close(){
		try{con.close();} 
		catch (Exception e){System.out.println(e.getMessage());}
	}
}