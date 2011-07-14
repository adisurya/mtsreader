package id.co.mondial.android.mediaindonesia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReader;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChannelContents extends ListActivity {

	public static int contentId = 0;
	public static List<String> rssTitles = new ArrayList<String>();
	public static List<String> rssDescs = new ArrayList<String>();
	public static List<Date> rssPubDates = new ArrayList<Date>();
	public static List<Uri> rssUris = new ArrayList<Uri>();

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        String title = getResources().getStringArray(R.array.channels_title)[MediaIndonesiaChannels.channelId];

        //setListAdapter(new ArrayAdapter<String>(this, R.layout.channels, countries));
        setTitle(title);
        
        String url = getResources().getStringArray(R.array.channels_url)[MediaIndonesiaChannels.channelId];

        // get redirected url
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            HttpGet request = new HttpGet(url);

        	response = client.execute(request);
        	final int statusCode = response.getStatusLine().getStatusCode();
        	if (statusCode != HttpStatus.SC_OK) {
        		Header[] headers = response.getHeaders("Location");
        		if(headers != null && headers.length != 0) {
        			url = headers[headers.length - 1].getValue();
        		}
        	}

        } 
        catch (Exception e) {
        	Toast.makeText(this, "connection error", Toast.LENGTH_LONG).show();
        	return;
        }
        
        RSSReader reader = new RSSReader();
    	
        rssTitles.clear();
    	rssDescs.clear();
    	rssPubDates.clear();
    	rssUris.clear();
        
    	try {
        	RSSFeed feed = reader.load(url);
        	List<RSSItem> feedItems= feed.getItems();
        	Iterator<RSSItem> feedIterator = feedItems.iterator();
        	
        	while(feedIterator.hasNext()) {
        		RSSItem rssItem = feedIterator.next();
    			rssTitles.add(rssItem.getTitle());
    			rssDescs.add(rssItem.getDescription());
    			rssPubDates.add(rssItem.getPubDate());
    			rssUris.add(rssItem.getLink());
        	}
        }
        catch (Exception e) {
        	Toast.makeText(this, "rss parsing error", Toast.LENGTH_LONG).show();
        	return;
        }
        setListAdapter(new ArrayAdapter<String>(this, R.layout.channels, rssTitles));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	super.onListItemClick(l, v, position, id);
    	
    	contentId = position;
    	Intent contentDetailIntent = new Intent(this, ContentDetail.class);
    	startActivity(contentDetailIntent);

    }
    

}
