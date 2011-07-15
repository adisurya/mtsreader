package id.co.mondial.android.rss.mediaindonesia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

public class RssItemDetail extends Activity {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    	setContentView(R.layout.item_detail);
        
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(RssItems.rssTitles.get(RssItems.contentId));
        TextView pubDate = (TextView) findViewById(R.id.pubDate);
        pubDate.setText(RssItems.rssPubDates.get(RssItems.contentId).toLocaleString());

        WebView mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        String data = RssItems.rssDescs.get(RssItems.contentId);
        mWebView.loadData(data, "text/html", "utf-8");
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

}
