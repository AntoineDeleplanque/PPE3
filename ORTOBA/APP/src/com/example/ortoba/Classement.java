package com.example.ortoba;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Classement extends Activity {

	List<equipe> eq = new ArrayList();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classement);

		Button retour = (Button) findViewById(R.id.returnButton);
		retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(Classement.this, MainActivity.class);
            	startActivity(intent);
            }
        });
		
		WebServiceAsync wba = new WebServiceAsync(this);
		wba.execute();
	    
	}
	
	public class WebServiceAsync extends AsyncTask<Void, Integer, Void>{

		final RelativeLayout rl;
		Context ctx;
		List<equipe> eq;
		
		WebServiceAsync(Context ctxA){
			rl=(RelativeLayout) findViewById(R.id.ClassmentRLayout);
			ctx = ctxA;
		}
		
		
		@Override
		protected Void doInBackground(Void... arg0) {
			WebService wb = new WebService();
			eq = wb.getEquipe();
			return null;
		}
		
		protected void onPostExecute(Void result) {
			
			 new AlertDialog.Builder(Classement.this)
             .setTitle("Reception")
             .setMessage("Récéption des données Terminé")
             .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { 
                        // do nothing
                    }
                 })
             .show();
			
			final TextView[] tv = new TextView[eq.size()];
			
		    for(int i=0;i<eq.size();i++)
		    {
				tv[i] = new TextView(ctx);   
		        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams((int)LayoutParams.WRAP_CONTENT,(int)LayoutParams.WRAP_CONTENT);
		        params.leftMargin=10;
		        params.topMargin=i*80;
		        tv[i].setText("nom :  "+eq.get(i).nom_equipe + "\n" +"ville : " +eq.get(i).ville_equipe + "\n" + "score : " +eq.get(i).score_equipe);
		        tv[i].setTextSize((float) 12);
		        tv[i].setPadding(20, 50, 20, 50);
		        tv[i].setLayoutParams(params);
		        rl.addView(tv[i]);
		    }
		}
	}
}
