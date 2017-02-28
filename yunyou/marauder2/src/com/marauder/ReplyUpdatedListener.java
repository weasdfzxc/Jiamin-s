package com.marauder;

import org.json.JSONArray;

public interface ReplyUpdatedListener
{
    public void GetReply(JSONArray data, int pagenum);
}
