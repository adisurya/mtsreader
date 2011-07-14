package id.co.mondial.android.mediaindonesia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class ContentDetail extends Activity {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);
        setTitle("Media Indonesia");

        
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(ChannelContents.rssTitles.get(ChannelContents.contentId));
        TextView pubDate = (TextView) findViewById(R.id.pubDate);
        pubDate.setText(ChannelContents.rssPubDates.get(ChannelContents.contentId).toLocaleString());

        WebView mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        String data = ChannelContents.rssDescs.get(ChannelContents.contentId);
        mWebView.loadData(data, "text/html", "utf-8");
    }
    
    public void openBrowser(View v) {
    	openBrowser();
    }
    
    public void openBrowser() {
    	String uri = ChannelContents.rssUris.get(ChannelContents.contentId).toString();
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
	        default:
                return super.onOptionsItemSelected(item);
        }
    }

}
