package com.rogama.tareasasincronas;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	public ProgressBar progress;
	public Button launchAsync;
	myDownloadTask downloadTask;
	
	String uri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		uri = "";
		progress = (ProgressBar) findViewById(R.id.progress);
		launchAsync = (Button) findViewById(R.id.launch_async);
	}

	public void lanzaAsincrona(View view) {
		downloadTask = new myDownloadTask();
		downloadTask.execute(uri);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public class myDownloadTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			String uri = params[0];
			String result = "";
			
			for (int i = 1; i < 10; i++) {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i *10);
				progress.setProgress(i *10);
				result += i;
			}
			return result;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			launchAsync.setVisibility(View.GONE);
			progress.setVisibility(View.VISIBLE);
		}


		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Toast.makeText(MainActivity.this, "Finalización del tratamiento en segundo plano", Toast.LENGTH_LONG).show();
			
			launchAsync.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
		}

	}

}
