package com.example.ortoba;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
		
		WebService wb = new WebService();
		List<equipe> eq = new ArrayList();
		eq = wb.getEquipe();
		
		ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, eq);
		spin1.setAdapter(adapter);
		spin2.setAdapter(adapter);
		
		
		Button valider = (Button) findViewById(R.id.valide);
		valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
  
                new AlertDialog.Builder(Match.this)
                .setTitle("Confirmation")
                .setMessage("Etes-vous sure de vouloir inscrire ce match?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	
                        try {
        	            	// 1. create HttpClient
        	                HttpClient httpclient = new DefaultHttpClient();
        	                
        	                // 2. make POST request to the given URL
        	                HttpPost httpPost = new HttpPost("http://192.168.160.16/match/add/"+((equipe)spin1.getSelectedItem()).id_equipe+"/"+((EditText) findViewById(R.id.editText1)).getText()+"/"+((equipe)spin2.getSelectedItem()).id_equipe+"/"+((EditText) findViewById(R.id.editText2)).getText());

        	                // 8. Execute POST request to the given URL
        	                httpclient.execute(httpPost);
        	     
                    	} catch (Exception e) {
                    		//Log.d("InputStream", e.getLocalizedMessage());
                    	}
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
}
