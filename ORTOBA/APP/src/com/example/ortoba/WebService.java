package com.example.ortoba;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WebService {
	
	Gson gson;
	
	public WebService() {
		gson = new Gson();
	}

	private InputStream sendRequest(URL url) throws Exception {

		try {
			 //Ouverture de la connexion
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			 //Connexion à l'url
			urlConnection.connect();

			 //Si le serveur nous répond avec un code OK
		if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return urlConnection.getInputStream();
			}
		} catch (Exception e) {
			//Log.e("WebService", "Impossible de se connecter au webservice !");
		}

		return null;

	}

	/*public List<Point> getPoints() {
		String URL = "http://www.journal-aviation.com/t_includes/code/class/webservices/appli.php?p=secteurs";
		
		try {
			// Envoie de la requête
			InputStream inputStream = sendRequest(new URL(URL));
			
			// Vérification de l'inputStream
			if(inputStream != null) {
				// Lecture de l'inputStream dans un reader
				InputStreamReader reader = new InputStreamReader(inputStream);
				
				// Return la liste désérialisée par le moteur gson 
				return gson.fromJson(reader, new TypeToken<List<Point>>(){}.getType());
			}
			
		} catch (Exception e) {
			//Log.e("WebService", "Impossible de rapatrier les données !");
		}
		return null;
	}*/
	
	
	/*** LA TU RECUPERES ET CONSTRUIS TON OBJET AVEC getNOMDELOBJET ***/
	public List<equipe> getEquipe(){
		String URL = "http://192.168.160.16/equipe/classement";
		// Url du json
		
		try {
			// Envoie de la requête
			InputStream inputStream = sendRequest(new URL(URL));
			
			// Vérification de l'inputStream
			if(inputStream != null) {
				// Lecture de l'inputStream dans un reader
				InputStreamReader reader = new InputStreamReader(inputStream);
				
				// Return la liste désérialisée par le moteur gson 
				
				
				return gson.fromJson(reader, new TypeToken<List<equipe>>(){}.getType());
			}
			
		} catch (Exception e) {
			//Log.e("WebService", "Impossible de rapatrier les données !");
		}
		return null;
	}
	
	/*public JAVAlerte getAlerte(String key, String date, String hour){
		String URL = "http://www.journal-aviation.com/t_includes/code/class/webservices/appli.php?p=search&keys="+key.replaceAll(" ", "%20")+"&date="+date+"%20"+hour;
		
		try {
			// Envoie de la requête
			InputStream inputStream = sendRequest(new URL(URL));
			
			// Vérification de l'inputStream
			if(inputStream != null) {
				// Lecture de l'inputStream dans un reader
				InputStreamReader reader = new InputStreamReader(inputStream);
				
				// Return la liste désérialisée par le moteur gson 
				return gson.fromJson(reader, new TypeToken<JAVAlerte>(){}.getType());
			}
			
		} catch (Exception e) {
			//Log.e("WebService", "Impossible de rapatrier les données !");
		}
		return null;
	}*/

}
