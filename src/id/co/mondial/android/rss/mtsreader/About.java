package id.co.mondial.android.rss.mtsreader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class About extends Activity {
	GoogleAnalyticsTracker tracker;

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	tracker = GoogleAnalyticsTracker.getInstance();
    	tracker.start(getResources().getString(R.string.analytics_tracker_id), this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    	setContentView(R.layout.about);

    	tracker.trackPageView("/" + getResources().getString(R.string.tracker_about));
    	tracker.dispatch();
    }

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	
    	tracker.stop();
    }

}
