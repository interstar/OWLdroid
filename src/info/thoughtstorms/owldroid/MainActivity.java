package info.thoughtstorms.owldroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Menu;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class MainActivity extends Activity {

	public WebView mWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView myWebView = (WebView) findViewById(R.id.webview);
		
		myWebView.setWebChromeClient(
			new WebChromeClient() {
			  public boolean onConsoleMessage(ConsoleMessage cm) {
			    Log.d("OWL", cm.message() + " -- From line " 
			                         + cm.lineNumber() + " of "
			                         + cm.sourceId() );
			    return true; 
			  }  
		}); 
		
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);

		myWebView.addJavascriptInterface(new PageStore(this,"/sdcard/OWL/"), "Android");
		
		AssetManager am = getApplicationContext().getAssets();
		String buffer, content = "";
		try {
			InputStream is = am.open("index.html"); 
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			while ((buffer = bufferedReader.readLine()) != null) {
				content = content + buffer; 
			}
		} catch (IOException e) { 
			content = "<h2>Uh oh!</h2><p>";
		}
  
		myWebView.loadDataWithBaseURL("file:///android_asset/",content,"text/html", "UTF-8",null);
		
	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
}
