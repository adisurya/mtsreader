package id.co.mondial.android.mediaindonesia;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ContentDetail extends Activity {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);

        WebView mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        String data = ChannelContents.rssDescs.get(ChannelContents.contentId);
        mWebView.loadData(data, "text/html", "utf-8");
    }


}
