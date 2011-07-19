package id.co.mondial.android.rss.mtsreader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class RssItemDetail extends Activity {
	GoogleAnalyticsTracker tracker;
	
	public static final String SERIALIZED_NAME = "id.co.mondial.android.rss.mtsreader.rss_item_detail";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.item_detail);
    	
    	//serialized
    	SharedPreferences serialized = getSharedPreferences(SERIALIZED_NAME, 0);
    	
    	String titleText = serialized.getString("title", "");
    	String pubDateText = serialized.getString("pubDate", "");
    	String descText = serialized.getString("desc", "");
    	String uri = serialized.getString("uri", "");
    	
    	if (titleText == "") {
    	  	SharedPreferences.Editor serializedEditor = serialized.edit();
	    	
	    	titleText = RssItems.rssTitles.get(RssItems.contentId);
	    	serializedEditor.putString("title", titleText);
	    	
	    	pubDateText = RssItems.rssPubDates.get(RssItems.contentId).toLocaleString();
	    	serializedEditor.putString("pubDate", pubDateText);
	    	
	    	descText = RssItems.rssDescs.get(RssItems.contentId);
	    	serializedEditor.putString("desc", descText);
	    	
	    	uri = RssItems.rssUris.get(RssItems.contentId).toString();
	    	serializedEditor.putString("uri", uri);
	    	
	    	serializedEditor.commit();
    	}
    	
    	// google analytics
    	tracker = GoogleAnalyticsTracker.getInstance();
    	tracker.start("UA-22304301-3", this);
    	
    	uri = uri.replaceFirst("http://", "/");
    	tracker.trackPageView("/" + getResources().getString(R.string.tracker_prefix) + uri);
    	tracker.dispatch();

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(titleText);
        TextView pubDate = (TextView) findViewById(R.id.pubDate);
        pubDate.setText(pubDateText);

        WebView mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadData(descText, "text/html", "utf-16");
        
    }
    
    public void openBrowser(View v) {
    	openBrowser();
    }
    
    public void openBrowser() {
    	String uri = RssItems.rssUris.get(RssItems.contentId).toString();
    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    	startActivity(browserIntent);    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.content_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        	case R.id.openBrowser:
        		openBrowser();
        		return true;
        	case R.id.about:
            	Intent aboutIntent = new Intent(this, About.class);
            	startActivity(aboutIntent);
            	return true;
	        default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	
    	tracker.stop();
    }

}
