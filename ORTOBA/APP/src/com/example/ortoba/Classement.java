package com.example.ortoba;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Classement extends Activity {

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
		
		WebService wb = new WebService();
		List<equipe> eq = new ArrayList();
		eq = wb.getEquipe();

		final RelativeLayout rl=(RelativeLayout) findViewById(R.id.ClassmentRLayout);
	    final TextView[] tv = new TextView[eq.size()];
		
	    for(int i=0;i<eq.size();i++)
	    {
			tv[i] = new TextView(this);   
	        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams((int)LayoutParams.WRAP_CONTENT,(int)LayoutParams.WRAP_CONTENT);
	        params.leftMargin=10;
	        params.topMargin=i*50;
	        tv[i].setText("nom :  "+eq.get(i).nom_equipe + "\n" +"ville : " +eq.get(i).ville_equipe + "\n" + "score : " +eq.get(i).score_equipe);
	        tv[i].setTextSize((float) 12);
	        tv[i].setPadding(20, 50, 20, 50);
	        tv[i].setLayoutParams(params);
	        rl.addView(tv[i]);
	    }
	    
	}
}
