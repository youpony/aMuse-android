package com.youpony.amuse;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendStory extends Activity {

	TextView email, myEmail, name;
	EditText emailForm, myEmailForm, nameForm;
	String emailString, myEmailString, nameString;
	Button send;
	Item oggetto;
	private String JSON;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_story);
		email = (TextView) findViewById(R.id.emailTitle);
		myEmail = (TextView) findViewById(R.id.myemailTitle);
		name = (TextView) findViewById(R.id.nameTitle);
		email.setText("Inserisci qui la mail del destinatario:");
		myEmail.setText("inserisci qui la tua mail:");
		name.setText("inserisci qui il tuo nome:");
		emailForm = (EditText) findViewById(R.id.emailForm);
		myEmailForm = (EditText) findViewById(R.id.myemailForm);
		nameForm = (EditText) findViewById(R.id.nameForm);
		
		send = (Button) findViewById(R.id.send);
		
		send.setOnClickListener(sendListener);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_story, menu);
		return true;
	}
	
	OnClickListener sendListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			emailString = emailForm.getText().toString();
			myEmailString = myEmailForm.getText().toString();
			nameString = nameForm.getText().toString();
			/*if(!emailString.contains("@") || !myEmailString.contains("@")){
				AlertDialog.Builder error = new AlertDialog.Builder(getApplicationContext());
				error.setTitle("Formato email non corretto!");
				error.setMessage("Per favore controlla il formato dei dati inseriti, le email non sono corrette.");
				Log.i("orrudebug", "sbagliato email");
			}
			else{*/
			JSON = new String("{ \"items\": [");
			for(int i=0; i<PageViewer.values.size(); i++){
				oggetto = PageViewer.values.get(i);
				String id = new String(oggetto.id);
				if(i == PageViewer.values.size() - 1){
					JSON = JSON.concat("{ \"item_id\": \""+ id + "\"}");
				}
				else{
					JSON = JSON.concat("{ \"item_id\": \""+ id + "\"},");
				}
			}
			JSON = JSON.concat("] }");
			new SendJSONAsync(){
				protected void onPostExecute(HttpResponse response){
					if(response != null){
						Log.i("orrudebug", "storia inviata correttamente");
					}
					else{
						Log.i("orrudebug", "storia non inviata");
					}
				}
			}.execute(JSON);
			//}
		}
	};

}
