package id.co.mondial.android.rss.mtsreader;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RssArrayAdapter extends ArrayAdapter<String> {
	private final Activity context;
	private final List<String> titles;
	private final List<Date> dates;

	public RssArrayAdapter(Activity context, List<String> titles, List<Date> dates) {
		super(context, R.layout.list_item, titles);
		this.context = context;
		this.titles = titles;
		this.dates = dates;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.list_item, null, true);
		TextView textTitle = (TextView) rowView.findViewById(R.id.label);
		TextView textDate = (TextView) rowView.findViewById(R.id.date);
		textTitle.setText(titles.get(position));
		textDate.setText(dates.get(position).toLocaleString());
		return rowView;
	}
}
