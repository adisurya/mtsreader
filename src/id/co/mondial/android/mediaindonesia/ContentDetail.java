package id.co.mondial.android.mediaindonesia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class ContentDetail extends Activity {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);

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
    	//Toast.makeText(this, ChannelContents.rssUris.get(ChannelContents.contentId).toString(), Toast.LENGTH_SHORT).show();
    	String uri = ChannelContents.rssUris.get(ChannelContents.contentId).toString();
    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    	startActivity(browserIntent);
    }


}
