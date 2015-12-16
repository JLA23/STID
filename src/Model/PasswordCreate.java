package Model;

import java.util.Random;

public class PasswordCreate {

	public PasswordCreate(){
		
	}
	
	public String create(){
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random rand = new Random();
        String mdp = "";
        for (int i=0; i<8; i++)
        {
               mdp += alphabet.charAt(rand.nextInt(alphabet.length()));
        }
        return mdp;
	}
}
