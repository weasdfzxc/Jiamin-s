package com.marauder;

import java.io.UnsupportedEncodingException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;






import android.widget.Toast;

import java.sql.Timestamp;
//日期控件引入
import java.util.Calendar;  
 



import java.util.Date;

import android.app.DatePickerDialog;  
import android.app.Dialog;  
import android.app.ProgressDialog;
import android.app.TimePickerDialog; 
import android.widget.DatePicker; 
import android.widget.TimePicker; 

@SuppressLint("HandlerLeak")
public class Release_Activity extends Activity implements OnClickListener
{
    private String titleString;
    private String contentString;
    private String locationString;
    private String name;
    private String deadline;
    private String startline;
    private Context context;
    
    private HttpThread httpThread;
    private Thread thread;
    private Intent intent; 
    private Button pickstartDate; 
    private Button pickstartTime;
    private Button pickdeadDate; 
    private Button pickdeadTime;
    private ProgressDialog progressDialog = null;
    
    
    private static final int startSHOW_DATAPICK = 0;   
    private static final int startDATE_DIALOG_ID = 1;    
    private static final int startSHOW_TIMEPICK = 2;  
    private static final int startTIME_DIALOG_ID = 3;
    private static final int deadSHOW_DATAPICK = 4;   
    private static final int deadDATE_DIALOG_ID = 5;    
    private static final int deadSHOW_TIMEPICK = 6;  
    private static final int deadTIME_DIALOG_ID = 7;  
      
    private int mYear;    
    private int mMonth;  
    private int mDay;   
    private int mHour;  
    private int mMinute;  

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release);
        context = getApplicationContext();

        locationString = getIntent().getStringExtra("location");
        name =getIntent().getStringExtra("userName");
        
        Button confirm = (Button) findViewById(R.id.confirm);
        Button cancel = (Button) findViewById(R.id.cancel);
        //日期控件引入
        pickstartDate = (Button) findViewById(R.id.startlinedate);
        pickstartTime = (Button)findViewById(R.id.startlinetime);
        pickdeadDate = (Button) findViewById(R.id.deadlinedate);
        pickdeadTime = (Button)findViewById(R.id.deadlinetime);
        
        
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
        pickstartDate.setOnClickListener(this);
        pickstartTime.setOnClickListener(this);
        pickdeadDate.setOnClickListener(this);
        pickdeadTime.setOnClickListener(this);
        httpThread = new HttpThread();
        httpThread.setUrl(getString(R.string.main_url) + "release-json");
        intent = new Intent(this, Main_Activity.class);
        intent.putExtra("userName", name);
        httpThread.setHandler(new Handler() 
        {
            public void handleMessage(Message message)
            {
                startActivity(intent);
                progressDialog.dismiss(); 
                finish();
            }
        });
        

        final Calendar c = Calendar.getInstance();  
        mYear = c.get(Calendar.YEAR);    
        mMonth = c.get(Calendar.MONTH);    
        mDay = c.get(Calendar.DAY_OF_MONTH);  
          
        mHour = c.get(Calendar.HOUR_OF_DAY);  
        mMinute = c.get(Calendar.MINUTE);  
          
        setDatestartTime();   
        setstartTimeOfDay();  
        setDatedeadTime();   
        setdeadTimeOfDay(); 
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
            case R.id.confirm:
            	
                EditText title = (EditText) findViewById(R.id.title);
                EditText content = (EditText) findViewById(R.id.content);
                titleString = title.getText().toString();
                contentString = content.getText().toString();
                Bundle values = new Bundle();
                values.putString("title",titleString);
                values.putString("content", contentString);
                values.putString("location", locationString);
                
                char [] cs = startline.toCharArray();
                char [] cd = deadline.toCharArray();
                cs[10]=' ';
                cd[10]=' ';
                 
                String s=new String(cs)+":00.000000000";
                String de=new String(cd)+":00.000000000";
                Timestamp startdate = Timestamp.valueOf(s);
                Timestamp enddate = Timestamp.valueOf(de);
                values.putString("startdate", startdate.toString());
                values.putString("enddate", enddate.toString());
                try
                {
                	progressDialog = ProgressDialog.show(Release_Activity.this, "发布", "正在提交信息,请稍候！");
                	httpThread.setData(values);
                    thread = new Thread(httpThread);
                    thread.start();
                    
                }
                catch (Exception e)
                {
                	Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();
                	//startActivity(intent);
                    finish();
                }  

                
                break;
            case R.id.cancel:
                //startActivity(intent);
                finish();
            case R.id.startlinedate:
            	Message msg = new Message();   
                if (pickstartDate.equals((Button) v)) {    
                   msg.what = Release_Activity.startSHOW_DATAPICK;  }
                Release_Activity.this.dateandtimeHandler.sendMessage(msg);
                break;
            case R.id.startlinetime: 
            	 Message msg1 = new Message();   
                 if (pickstartTime.equals((Button) v)) {    
                    msg1.what = Release_Activity.startSHOW_TIMEPICK;    
                 }    
                 Release_Activity.this.dateandtimeHandler.sendMessage(msg1);
                 break;
            case R.id.deadlinedate:
            	Message msg2 = new Message();   
                if (pickdeadDate.equals((Button) v)) {    
                   msg2.what = Release_Activity.deadSHOW_DATAPICK;  }
                Release_Activity.this.dateandtimeHandler.sendMessage(msg2);
                break;
            case R.id.deadlinetime: 
            	 Message msg3 = new Message();   
                 if (pickdeadTime.equals((Button) v)) {    
                    msg3.what = Release_Activity.deadSHOW_TIMEPICK;    
                 }    
                 Release_Activity.this.dateandtimeHandler.sendMessage(msg3);
                 break;
            default:
                break;
        }
    }
    
    
  
    /**  
     * 设置日期  
     */  
    private void setDatedeadTime(){  
       final Calendar c = Calendar.getInstance();    
         
       mYear = c.get(Calendar.YEAR);    
       mMonth = c.get(Calendar.MONTH);    
       mDay = c.get(Calendar.DAY_OF_MONTH); 
       updateDatedeadDisplay();   
    }  

    /**  
     * 更新日期显示  
     */  
    private void updateDatedeadDisplay(){  
    	pickdeadDate.setText(new StringBuilder().append(mYear).append("-")  
               .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")  
               .append((mDay < 10) ? "0" + mDay : mDay));
    	deadline = pickdeadDate.getText() + "-" +pickdeadTime.getText();
    	startline = pickstartDate.getText() + "-" +pickstartTime.getText();
    }  
      
    /**   
     * 日期控件的事件   
     */    
    private DatePickerDialog.OnDateSetListener mDatedeadSetListener = new DatePickerDialog.OnDateSetListener() {    
    
       public void onDateSet(DatePicker view, int year, int monthOfYear,    
              int dayOfMonth) {    
           mYear = year;    
           mMonth = monthOfYear;    
           mDay = dayOfMonth;    
  
           updateDatedeadDisplay();  
       }    
    };   
      
    /**  
     * 设置时间  
     */  
    private void setdeadTimeOfDay(){  
       final Calendar c = Calendar.getInstance();   
       mHour = c.get(Calendar.HOUR_OF_DAY);  
       mMinute = c.get(Calendar.MINUTE);  
       updatedeadTimeDisplay();  
    }  
      
    /**  
     * 更新时间显示  
     */  
    private void updatedeadTimeDisplay(){  
    	pickdeadTime.setText(new StringBuilder().append(mHour).append(":")  
               .append((mMinute < 10) ? "0" + mMinute : mMinute));
    	deadline = pickdeadDate.getText() + "-" +pickdeadTime.getText();
    	startline = pickstartDate.getText() + "-" +pickstartTime.getText();
    }  
      
    /**  
     * 时间控件事件  
     */  
    private TimePickerDialog.OnTimeSetListener mTimedeadSetListener = new TimePickerDialog.OnTimeSetListener() {  
          
        @Override  
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {  
            mHour = hourOfDay;  
            mMinute = minute;  
              
            updatedeadTimeDisplay();  
        }  
    }; 
    /**  
     * 设置日期  
     */  
    private void setDatestartTime(){  
       final Calendar c = Calendar.getInstance();    
         
       mYear = c.get(Calendar.YEAR);    
       mMonth = c.get(Calendar.MONTH);    
       mDay = c.get(Calendar.DAY_OF_MONTH); 
       updateDatestartDisplay();   
    }  

    /**  
     * 更新日期显示  
     */  
    private void updateDatestartDisplay(){  
    	pickstartDate.setText(new StringBuilder().append(mYear).append("-")  
               .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")  
               .append((mDay < 10) ? "0" + mDay : mDay));   
    	deadline = pickdeadDate.getText() + "-" +pickdeadTime.getText();
    	startline = pickstartDate.getText() + "-" +pickstartTime.getText();
    }  
      
    /**   
     * 日期控件的事件   
     */    
    private DatePickerDialog.OnDateSetListener mDatestartSetListener = new DatePickerDialog.OnDateSetListener() {    
    
       public void onDateSet(DatePicker view, int year, int monthOfYear,    
              int dayOfMonth) {    
           mYear = year;    
           mMonth = monthOfYear;    
           mDay = dayOfMonth;    
  
           updateDatestartDisplay();  
       }    
    };   
      
    /**  
     * 设置时间  
     */  
    private void setstartTimeOfDay(){  
       final Calendar c = Calendar.getInstance();   
       mHour = c.get(Calendar.HOUR_OF_DAY);  
       mMinute = c.get(Calendar.MINUTE);  
       updatestartTimeDisplay();  
    }  
      
    /**  
     * 更新时间显示  
     */  
    private void updatestartTimeDisplay(){  
    	pickstartTime.setText(new StringBuilder().append(mHour).append(":")  
               .append((mMinute < 10) ? "0" + mMinute : mMinute));
    	deadline = pickdeadDate.getText() + "-" +pickdeadTime.getText();
    	startline = pickstartDate.getText() + "-" +pickstartTime.getText();
    }  
      
    /**  
     * 时间控件事件  
     */  
    private TimePickerDialog.OnTimeSetListener mTimestartSetListener = new TimePickerDialog.OnTimeSetListener() {  
          
        @Override  
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {  
            mHour = hourOfDay;  
            mMinute = minute;  
              
            updatestartTimeDisplay();  
        }  
    };  
      
    @Override    
    protected Dialog onCreateDialog(int id) {    
       switch (id) {    
       case startDATE_DIALOG_ID:    
           return new DatePickerDialog(this, mDatestartSetListener, mYear, mMonth,    
                  mDay);  
       case startTIME_DIALOG_ID:  
           return new TimePickerDialog(this, mTimestartSetListener, mHour, mMinute, true);
       case deadDATE_DIALOG_ID:    
           return new DatePickerDialog(this, mDatedeadSetListener, mYear, mMonth,    
                  mDay);  
       case deadTIME_DIALOG_ID:  
           return new TimePickerDialog(this, mTimedeadSetListener, mHour, mMinute, true);    
       }  
             
       return null;    
    }    
    
    @Override    
    protected void onPrepareDialog(int id, Dialog dialog) {    
       switch (id) {    
       case startDATE_DIALOG_ID:    
           ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);    
           break;  
       case startTIME_DIALOG_ID:  
           ((TimePickerDialog) dialog).updateTime(mHour, mMinute);  
           break;
       case deadDATE_DIALOG_ID:    
           ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);    
           break;  
       case deadTIME_DIALOG_ID:  
           ((TimePickerDialog) dialog).updateTime(mHour, mMinute);  
           break; 
       }  
    }    
    
    /**   
     * 处理日期和时间控件的Handler   
     */    
    Handler dateandtimeHandler = new Handler() {  
    
	@Override    
       public void handleMessage(Message msg) {    
           switch (msg.what) {    
           case Release_Activity.startSHOW_DATAPICK:    
               showDialog(startDATE_DIALOG_ID);    
               break;   
           case Release_Activity.startSHOW_TIMEPICK:  
               showDialog(startTIME_DIALOG_ID);  
               break;
           case Release_Activity.deadSHOW_DATAPICK:    
               showDialog(deadDATE_DIALOG_ID);    
               break;   
           case Release_Activity.deadSHOW_TIMEPICK:  
               showDialog(deadTIME_DIALOG_ID);  
               break;  
           }    
       }    
    
    };
    
    
}
