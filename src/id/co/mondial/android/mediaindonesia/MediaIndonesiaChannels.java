package id.co.mondial.android.mediaindonesia;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MediaIndonesiaChannels extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] countries = getResources().getStringArray(R.array.channels_title);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.channels, countries));        

    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	super.onListItemClick(l, v, position, id);
    	
    	// get the item that was click
    	String url = getResources().getStringArray(R.array.channels_url)[position];
    	Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
    }
}