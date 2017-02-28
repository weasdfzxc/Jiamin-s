package com.marauder;

import org.json.JSONArray;

public interface MessageUpdatedListener {
	public void GetMessage(JSONArray data, int pagenum);
}
