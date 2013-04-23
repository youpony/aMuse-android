package com.youpony.amuse;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
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
	EditText emailForm,  nameForm;
	String emailString, nameString;
	Button send, cancel;
	Item oggetto;
	private String JSON;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_story);
		email = (TextView) findViewById(R.id.emailTitle);
		name = (TextView) findViewById(R.id.nameTitle);
		email.setText("Inserisci qui la mail del destinatario:");
		name.setText("inserisci qui il tuo nome:");
		emailForm = (EditText) findViewById(R.id.emailForm);
		nameForm = (EditText) findViewById(R.id.nameForm);
		
		send = (Button) findViewById(R.id.send);
		cancel = (Button) findViewById(R.id.cancel);
		
		cancel.setOnClickListener(cancelListener);
		send.setOnClickListener(sendListener);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_story, menu);
		return true;
	}
	
	OnClickListener cancelListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			close();
			
		}
	};
	
	OnClickListener sendListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			emailString = emailForm.getText().toString();
			nameString = nameForm.getText().toString();
			/*if(!emailString.contains("@") || !myEmailString.contains("@")){
				AlertDialog.Builder error = new AlertDialog.Builder(getApplicationContext());
				error.setTitle("Formato email non corretto!");
				error.setMessage("Per favore controlla il formato dei dati inseriti, le email non sono corrette.");
				Log.i("orrudebug", "sbagliato email");
			}
			else{*/
			JSONObject json = new JSONObject();
			try {
				json.put("email", emailString);
				json.put("name", nameString);
				JSONArray jsonarray = new JSONArray();
				for(int i=0; i<PageViewer.values.size(); i++){
					JSONObject jsonId = new JSONObject();
					jsonId.put("item_pk", PageViewer.values.get(i).id);
					jsonarray.put(jsonId);
				}
				json.put("posts", jsonarray);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new SendJSONAsync(){
				protected void onPostExecute(HttpResponse response){
					if(response != null){
						Log.i("orrudebug", "storia inviata correttamente");
						//clear della story e reset
						PageViewer.values.clear();
						PageViewer.leftItems.clear();
						PageViewer.rightItems.clear();
						Story.leftAdapter.clear();
						Story.rightAdapter.clear();
						Story.leftAdapter.notifyDataSetChanged();
						Story.rightAdapter.notifyDataSetChanged();
						close();
					}
					else{
						Log.i("orrudebug", "storia non inviata");
					}
				}
			}.execute(json);
			//}
		}
	};
	

	void close(){
		SendStory.this.finish();
		PageViewer.mViewPager.setCurrentItem(1);
	}
	

}
