package com.example.broadcastreceiver;

import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessagesAdapter extends BaseAdapter {

	private ArrayList<Message> mMessages;
	private Context mContext;
	private LayoutInflater mInflater;

	public MessagesAdapter(Context context) {
		mMessages = new ArrayList<Message>();
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mMessages.size();
	}

	@Override
	public Message getItem(int position) {
		return mMessages.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	@SuppressLint("DefaultLocale")
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = mInflater.inflate(R.layout.item_main, parent, false);

		TextView tvTitle = (TextView) convertView
				.findViewById(R.id.item_main_tvTitle);
		TextView tvMessage = (TextView) convertView
				.findViewById(R.id.item_main_tvMessage);
		TextView tvDate = (TextView) convertView
				.findViewById(R.id.item_main_tvDate);

		String title = getItem(position).getTitle();
		String message = getItem(position).getMessage();
		long timestamp = getItem(position).getTimestamp();

		tvTitle.setVisibility(title.isEmpty() ? View.GONE : View.VISIBLE);
		tvMessage.setVisibility(message.isEmpty() ? View.GONE : View.VISIBLE);
		tvDate.setVisibility(timestamp < 0 ? View.GONE : View.VISIBLE);
		
		tvTitle.setText(title.toUpperCase());
		tvMessage.setText(getItem(position).getMessage());
		tvDate.setText(new Date().toString());

		return convertView;
	}

	public void add(Message message) {
		mMessages.add(message);
		notifyDataSetChanged();
	}

}
