package com.marauder;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends Activity implements OnClickListener
{
    private String userName = null;
    private String password = null;
    private HttpThread httpThread;
    private Thread thread = null;
    private Intent login_main;
    private Context context;
    private ProgressDialog progressDialog = null;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        context = getApplicationContext();
        //Get views
        Button loginButton = (Button) findViewById(R.id.login_button);
        Button registButton = (Button) findViewById(R.id.regist_button);
        //Set click listener
        loginButton.setOnClickListener(this);
        registButton.setOnClickListener(this);
        httpThread = new HttpThread();
        httpThread.setUrl(getString(R.string.main_url) + "login-json");
        httpThread.setHandler(new LoginHandler());
        
        
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if (thread != null)
            thread.interrupt();
        thread = null;
    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.login_button:
                userName = ((EditText) findViewById(R.id.userName)).getText().toString();
                password = ((EditText) findViewById(R.id.password)).getText().toString();
                login_main = new Intent(this, Main_Activity.class);
                
                // Verify submitted info
                if (userName != null && password != null)
                {
                	progressDialog = ProgressDialog.show(Login_Activity.this, "登陆", "正在登陆,请稍候！");
                    Bundle info = new Bundle();
                    info.putString("userName", userName);
                    info.putString("password", password);
                    login_main.putExtras(info);
                    httpThread.setData(info);
                    thread = new Thread(httpThread);
                    thread.start();
                }
                else
                {
                    Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.regist_button:
                Intent intent = new Intent(this, Regist_Activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    class LoginHandler extends Handler
    {

        public LoginHandler()
        {
            super();
        }

        public LoginHandler(Looper looper)
        {
            super(looper);
        }

        public void handleMessage(Message message)
        {
            Boolean foo = true;
            try
            {
                //Log.d("debug", "Received login info : " + message.obj.toString());
                JSONObject jsonObject = new JSONObject(message.obj.toString());
                foo = jsonObject.isNull("USER");
                if (!foo)
                {
                	login_main.putExtra("city", "石家庄");
                    startActivity(login_main);
                    progressDialog.dismiss(); 
                    finish();
                }
                else
                {
                	
                    Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
            catch (JSONException e)
            {
                Toast.makeText(context, "登陆失败，可能由于密码错误或用户不存在", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                progressDialog.dismiss();
            }
            catch (NullPointerException e)
            {
                Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }  
            
        }
    }

}
