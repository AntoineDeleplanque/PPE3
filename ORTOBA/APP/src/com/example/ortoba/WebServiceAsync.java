package com.example.ortoba;

import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import android.widget.RelativeLayout;
import android.os.AsyncTask;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;

public class WebServiceAsync extends AsyncTask<Void, Integer, Void>{

	WebServiceAsync(){
		//final RelativeLayout rl=(RelativeLayout) findViewById(R.id.ClassmentRLayout);
	}
	
	
	@Override
	protected Void doInBackground(Void... arg0) {
		WebService wb = new WebService();
		List<equipe> eq = wb.getEquipe();
		return null;
	}
	
	protected void onPostExecute(Void result) {
		
	}

}
