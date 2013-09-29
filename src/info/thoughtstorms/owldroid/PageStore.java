package info.thoughtstorms.owldroid;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.os.Environment;
import android.webkit.JavascriptInterface;

public class PageStore {

    Context mContext;
    String directory;
	boolean mExternalStorageAvailable = false;
	boolean mExternalStorageWriteable = false;

    PageStore(Context c, String d) {
        mContext = c;
        directory = d;
    }

    public void checkExternal() {
    	String state = Environment.getExternalStorageState();

    	if (Environment.MEDIA_MOUNTED.equals(state)) {
    	    mExternalStorageAvailable = mExternalStorageWriteable = true;
    	} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
    	    mExternalStorageAvailable = true;
    	    mExternalStorageWriteable = false;
    	} else {
    	    // Something else is wrong. It may be one of many other states, but all we need
    	    //  to know is we can neither read nor write
    	    mExternalStorageAvailable = mExternalStorageWriteable = false;
    	}
    }
    
    @JavascriptInterface
	public String writePage(String pageName, String body) {
    	checkExternal();
    	if (mExternalStorageWriteable) {
			try {		    
				FileOutputStream fos = new FileOutputStream(directory+pageName+".opml");
                fos.write(body.getBytes());
                fos.close();
                return "OK";
			} catch (java.io.IOException e) {
			    //do something if an IOException occurs.
				return e.toString();
			}
    	} else {
    		// storage not available
    		return "STORAGE NOT AVAILABLE";
    	}
	} 
 
    @JavascriptInterface
	public String readPage(String pageName) { 
    	checkExternal();
    	
		String s = "";
		
		try {
			FileInputStream fis = new FileInputStream(directory+pageName + ".opml");
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );
            String strLine;
            while ((strLine = br.readLine()) != null) {
                s = s + strLine;
            }
            in.close();
			return s;
		} catch (java.io.FileNotFoundException e) {
			return "NO FILE";
		} catch (java.io.IOException e) {
			return e.toString();
		}
	}
    
    
}