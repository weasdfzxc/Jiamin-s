package com.marauder;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Content_Activity extends ListActivity implements OnClickListener {
	public final static int NOTIFY_MESSAGE = 1;
	String mid = null;
	// Record the latest reply date you readed
	int latestrid = 0;
	ReplyUpdater updater;
	ReplyUpdatedListener listener;
	JSONArray cache = null;
	Handler handler = new Handler();
	// Data list items
	ArrayList<HashMap<String, Object>> listitems = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get data from last activity
		Intent data = getIntent();
		String title = data.getStringExtra("title");
		String name = data.getStringExtra("name");
		String date = data.getStringExtra("date");
		date = date.substring(0, 10);
		String content = data.getStringExtra("content");
		mid = data.getStringExtra("mid");
		setContentView(R.layout.content);
		// Set Button's OnClick event
		Button reply = (Button) findViewById(R.id.reply);
		reply.setOnClickListener(this);
		Button cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(this);
		// Show the content
		TextView titleview = (TextView) findViewById(R.id.title);
		titleview.setText(title);
		TextView nameview = (TextView) findViewById(R.id.author);
		nameview.setText(name);
		TextView dateview = (TextView) findViewById(R.id.date);
		dateview.setText(date);
		TextView contentview = (TextView) findViewById(R.id.content);
		contentview.setText(content);
		cache = new JSONArray();
		// get reply
		listener = new ReplyUpdatedListener() {
			@Override
			public void GetReply(final JSONArray data, final int pagenum) {
				final Runnable uiupdater = new Runnable() {
					@Override
					public void run() {
						try {
							// Set ListView's adapter to show message list
							ListView list = (ListView) findViewById(android.R.id.list);
							listitems = new ArrayList<HashMap<String, Object>>();
							// Build data
							for (int i = 0; i < data.length(); ++i) {
								HashMap<String, Object> map = new HashMap<String, Object>();

								map.put("name", data.getJSONObject(i)
										.getString("name"));
								String content = "Re:";
								content += data.getJSONObject(i)
										.getString("content");
								map.put("content", content);
								String date = "时间：";
								date += data.getJSONObject(i).getString("date")
										.split("T")[0];
								map.put("date", date);
								listitems.add(map);
								// Add message to cache
								cache.put(data.getJSONObject(i));
							}
							list.setAdapter(new SimpleAdapter(getApplication(),
									listitems, R.layout.replyitem,
									new String[] { "content", "name", "date" },
									new int[] { R.id.title, R.id.name,
											R.id.date }));
							this.setListViewHeightBasedOnChildren(list);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					private void setListViewHeightBasedOnChildren(ListView listView) {
						// TODO Auto-generated method stub
						// 获取ListView对应的Adapter
						ListAdapter listAdapter = listView.getAdapter();
						if (listAdapter == null) {
							return;
						}

						int totalHeight = 0;
						for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
							// listAdapter.getCount()返回数据项的数目
							View listItem = listAdapter.getView(i, null, listView);
							// 计算子项View 的宽高
							listItem.measure(0, 0);
							// 统计所有子项的总高度
							totalHeight += listItem.getMeasuredHeight();
						}

						ViewGroup.LayoutParams params = listView.getLayoutParams();
						params.height = totalHeight
								+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
						// listView.getDividerHeight()获取子项间分隔符占用的高度
						// params.height最后得到整个ListView完整显示需要的高度
						listView.setLayoutParams(params);
					}

				};
				new Thread() {
					public void run() {
						handler.post(uiupdater);
					}
				}.start();
			}
		};
		
		updater = new ReplyUpdater(this);
		Bundle bundle = new Bundle();
		bundle.putString("mid", mid);
		updater.Update(listener, 1, bundle);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cancel:
			finish();
			break;
		case R.id.reply:

			EditText content = (EditText) findViewById(R.id.replytext);
			Bundle bundle = new Bundle();
			bundle.putString("mid", mid);
			bundle.putString("content", content.getText().toString());

			content.setText("");
			Toast.makeText(this, "回复成功", Toast.LENGTH_LONG).show();
			updater.Update(listener, 1, bundle);
		default:
			break;
		}
	}

	/*public void setListViewHeightBasedOnChildren(ListView listView) {
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
			// listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			// 计算子项View 的宽高
			listItem.measure(0, 0);
			// 统计所有子项的总高度
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}*/
}
