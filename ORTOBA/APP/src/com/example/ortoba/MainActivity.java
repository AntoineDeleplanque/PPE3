package com.example.ortoba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button inscription = (Button) findViewById(R.id.InscriptionButton);
		inscription.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(MainActivity.this, Inscription.class);
            	startActivity(intent);
            }
        });
		
		Button match = (Button) findViewById(R.id.MatchButton);
		match.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(MainActivity.this, Match.class);
            	startActivity(intent);
            }
        });
		
		Button classement = (Button) findViewById(R.id.ClassementButton);
		classement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(MainActivity.this, Classement.class);
            	startActivity(intent);
            }
        });

	}
}
