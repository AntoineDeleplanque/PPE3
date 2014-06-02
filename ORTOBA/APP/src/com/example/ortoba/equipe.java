package com.example.ortoba;

public class equipe {
	int id_equipe;
    String nom_equipe;
    String ville_equipe;
    int score_equipe;
 
    equipe(int Id, String Nom, String Ville, int Score){
    	this.id_equipe = Id;

    	this.nom_equipe = Nom;

    	this.ville_equipe = Ville;

    	this.score_equipe = Score;
      }
    
    public int getId()
    {
    	return id_equipe;
    }
    
    public void setId(int Id)
    {
    	id_equipe = Id;
    }
    
    public String getNom()
    {
    	return nom_equipe;
    }
    
    public void setNom( String Nom )
    {
    	nom_equipe = Nom;
    }
    
    public String getVille()
    {
    	return ville_equipe;
    }
    
    public void setVille( String Ville)
    {
    	ville_equipe = Ville;
    }
    
    public int getScore()
    {
    	return score_equipe;
    }
    
    public void setScore( int Score )
    {
    	score_equipe = Score;
    }
    
    public String toString(){
    	return this.nom_equipe;
	}
}
