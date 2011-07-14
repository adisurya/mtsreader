package id.co.mondial.android.mediaindonesia;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MediaIndonesiaChannels extends ListActivity {
    
	public static int channelId = 0;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Media Indonesia");


        String[] channels = getResources().getStringArray(R.array.channels_title);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.channels, channels));        

    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	super.onListItemClick(l, v, position, id);
    	
    	channelId = position;
    	Intent channelContentsIntent = new Intent(this, ChannelContents.class);
    	startActivity(channelContentsIntent);

    }
}