package com.marauder;

import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import android.os.AsyncTask;

public class Connection extends AsyncTask<URL, String, String>
{

    @Override
    protected String doInBackground(URL... params)
    {
        URL url = params[0]; 
      //  DataTrans trans = new DataTrans(url.toString());
        Map<String,String> key_values = new HashMap<String, String>();
        key_values.put("code", "39,199");
        String response = null;
//        try
//        {
//           // response = trans.transDataByGet(key_values);
//        }
//        catch (IOException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        catch (JSONException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return response;
    }

}
