package com.example.ortoba;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.ortoba.Match.AsyncPostRequest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class Inscription extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		
		Button valider = (Button) findViewById(R.id.ValiderButton);
		valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
  
                new AlertDialog.Builder(Inscription.this)
                .setTitle("Confirmation")
                .setMessage("Etes-vous sure de vouloir inscrire cette equipe?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	
                    	AsyncPostRequest APR = new AsyncPostRequest();
                		APR.execute();
                    	
                    }
                 })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { 
                        // do nothing
                    }
                 })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
            }
        });
		
	
	Button retour = (Button) findViewById(R.id.RetourButton);
	retour.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent intent = new Intent(Inscription.this, MainActivity.class);
        	startActivity(intent);
        }
    });
	}
	
public class AsyncPostRequest extends AsyncTask<Void, Integer, Void>{
		
		int IdEquipe1, IdEquipe2;
		
		@Override
		protected Void doInBackground(Void... arg0) {
			try {
            	// 1. create HttpClient
                HttpClient httpclient = new DefaultHttpClient();
                
                // 2. make POST request to the given URL
                HttpPost httpPost = new HttpPost("http://nodewindow.cloudapp.net/equipe/add/" +((EditText) findViewById(R.id.NomTexte)).getText().toString() +"/"+((EditText)findViewById(R.id.editText2)).getText().toString());

                // 8. Execute POST request to the given URL
                httpclient.execute(httpPost);
     
        	} catch (Exception e) {
        		//Log.d("InputStream", e.getLocalizedMessage());
        	}
			return null;
		}
		
		protected void onPostExecute(Void result) {
			new AlertDialog.Builder(Inscription.this)
            .setTitle("Envoie")
            .setMessage("Envoie Terminé")
            .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) { 
                       // do nothing
                   }
                })
            .show();
		}
	}
}
