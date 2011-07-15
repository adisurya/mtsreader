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
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RssItems extends ListActivity implements Runnable {

	public static int contentId = 0;
	public static List<String> rssTitles = new ArrayList<String>();
	public static List<String> rssDescs = new ArrayList<String>();
	public static List<Date> rssPubDates = new ArrayList<Date>();
	public static List<Uri> rssUris = new ArrayList<Uri>();
	public ProgressDialog dialog;

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateChannels();
    	String title = getResources().getStringArray(R.array.channels_title)[RssFeeds.channelId];

        setTitle(getResources().getText(R.string.app_name) + " - " + title);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	super.onListItemClick(l, v, position, id);
    	
    	contentId = position;
    	Intent contentDetailIntent = new Intent(this, RssItemDetail.class);
    	startActivity(contentDetailIntent);

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rss_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        	case R.id.update:
        		updateChannels();
	            return true;
        	case R.id.about:
            	Intent aboutIntent = new Intent(this, About.class);
            	startActivity(aboutIntent);
            	return true;
        	default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void updateChannels() {
    	dialog = ProgressDialog.show(this, "Working..", "Updating data");
    	Thread thread = new Thread(this);
    	thread.start();
    }
    
    public void run() {        
        String url = getResources().getStringArray(R.array.channels_url)[RssFeeds.channelId];

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
        	showToast("connection error");
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
        	showToast("rss parsing error");
        	return;
        }

    	closeDialog();
    }
    
    private void closeDialog() {
    	runOnUiThread(new Runnable() {
    		public void run() {
    			dialog.dismiss();
    			updateListView();
    		}
    	});    	
    }
        
    private void updateListView() {
		setListAdapter(new ArrayAdapter<String>(this, R.layout.channels, rssTitles));
    }

    private void showToast(String msg, int length) {
    	final String _msg = msg;
    	final int _length = length;

    	runOnUiThread(new Runnable() {
    		public void run() {
	            Toast.makeText(
						RssItems.this, 
						_msg,
						_length
					).show();
    		}
    	});

    }

    private void showToast(String msg) {
    	showToast(msg, Toast.LENGTH_SHORT);
    }


}
