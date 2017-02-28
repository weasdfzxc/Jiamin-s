package com.marauder;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class HttpThread implements Runnable
{
    private String url = "";
    private Bundle values;
    private Handler handler;
    private String sessionID = null;

    public HttpThread()
    {
        super();
    }

    public HttpThread(String url, Bundle values, Handler handler)
    {
        super();
        this.url = url;
        this.values = values;
        this.handler = handler;
    }

    @Override
    public void run()
    {
        Log.d("debug","Requested HttpThread URL : " + url);
        DataTrans trans = new DataTrans(url);
        String response = null;
        try
        {
            response = trans.transDataByGet(values);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        Message message = handler.obtainMessage(1, (Object) response);
        handler.sendMessage(message);
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setData(Bundle values)
    {
        this.values = values;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    public void setSessionID(String sessionID)
    {
        this.sessionID = sessionID;
    }

}
