package com.marauder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Set;

import org.apache.http.cookie.Cookie;
import org.json.JSONException;

import android.os.Bundle;
import android.text.StaticLayout;
import android.util.Log;
import android.widget.Toast;

public class DataTrans {
	// Use for the verification of user's authority
	private static String cookie;
	// Target URL
	private StringBuffer target;

	public DataTrans(String target) {
		this.target = new StringBuffer(target);
	}

	public String transDataByGet(Bundle key_values) throws IOException, JSONException
    {
        target.append("?");
        if (key_values != null)
        {
            Set<String> keys = key_values.keySet();
            for (String key : keys)
            {
                try
                {
                	String temp = key_values.getString(key).trim();
                    if (temp != null && temp.length() != 0)
                    {
                        target.append(key);
                        target.append("=");
                        target.append(URLEncoder.encode(key_values.getString(key), "utf-8"));
                        target.append("&");
                    }
                }
                catch (NullPointerException e)
                {
                    break;
                }
            }
            target.deleteCharAt(target.length() - 1);

            Log.d("debug", "DataTrans Target URL : " + target.toString());
            URL url = new URL(target.toString());
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");
            urlConn.setDoOutput(true);
            try
            {
            	if (cookie != null)
                urlConn.setRequestProperty("Cookie", cookie);
            if (cookie == null)
            {
                cookie = urlConn.getHeaderField("Set-Cookie");
                String[] strings = cookie.split(";");
                cookie = strings[0];
            }
            Log.d("debug", "Cookie : " + cookie);
            }
            catch (NullPointerException e)
            {
                
            }  
            
            InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String result = "";
            String readerLine = null;
            while ((readerLine = br.readLine()) != null)
            {
                result += readerLine;
            }
            in.close();
            urlConn.disconnect();
            return result;

        }
        else
        {
            URL url = new URL(target.toString());
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String result = "";
            String readerLine = null;
            while ((readerLine = br.readLine()) != null)
            {
                result += readerLine;
            }
            in.close();
            urlConn.disconnect();
            return result;
        }
    }
}
