package id.co.mondial.android.mediaindonesia;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ChannelContents extends ListActivity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        String title = getResources().getStringArray(R.array.channels_title)[MediaIndonesiaChannels.channelId];
        //setListAdapter(new ArrayAdapter<String>(this, R.layout.channels, countries));
        setTitle(title);

    }
    

}
