package com.example.ortoba;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.ortoba.Classement.WebServiceAsync;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Match extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match);
		
		
		Button retour = (Button) findViewById(R.id.retour);
		retour.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent intent = new Intent(Match.this, MainActivity.class);
	        	startActivity(intent);
	        }
	    });
		
		final Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
		final Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
		
		WebServiceAsync wba = new WebServiceAsync();
		wba.execute();
		
		Button valider = (Button) findViewById(R.id.valide);
		valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
  
                new AlertDialog.Builder(Match.this)
                .setTitle("Confirmation")
                .setMessage("Etes-vous sure de vouloir inscrire ce match?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	
                    	AsyncPostRequest APR = new AsyncPostRequest((Spinner) findViewById(R.id.spinner1), (Spinner) findViewById(R.id.spinner2));
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
	}
	
	public class AsyncPostRequest extends AsyncTask<Void, Integer, Void>{
		
		int IdEquipe1, IdEquipe2;
		
		AsyncPostRequest(Spinner spinner1, Spinner spinner2){
			IdEquipe1 = ((equipe)spinner1.getSelectedItem()).id_equipe;
			IdEquipe2 = ((equipe)spinner2.getSelectedItem()).id_equipe;
		}
		
		@Override
		protected Void doInBackground(Void... arg0) {
			try {
            	// 1. create HttpClient
                HttpClient httpclient = new DefaultHttpClient();
                
                // 2. make POST request to the given URL
                HttpPost httpPost = new HttpPost("http://nodewindow.cloudapp.net/match/add/"+ IdEquipe1 +"/"+((EditText) findViewById(R.id.editText1)).getText()+"/"+ IdEquipe2 +"/"+((EditText) findViewById(R.id.editText2)).getText());

                // 8. Execute POST request to the given URL
                httpclient.execute(httpPost);
     
        	} catch (Exception e) {
        		//Log.d("InputStream", e.getLocalizedMessage());
        	}
			return null;
		}
		
		protected void onPostExecute(Void result) {
			new AlertDialog.Builder(Match.this)
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
	
	public class WebServiceAsync extends AsyncTask<Void, Integer, Void>{

		List<equipe> eq;
		
		@Override
		protected Void doInBackground(Void... arg0) {
			WebService wb = new WebService();
			eq = wb.getEquipe();
			return null;
		}
		
		protected void onPostExecute(Void result) {
			final Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
			final Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
			
			ArrayAdapter adapter = new ArrayAdapter(Match.this,android.R.layout.simple_spinner_item, eq);
			spin1.setAdapter(adapter);
			spin2.setAdapter(adapter);
		}
	}
}
