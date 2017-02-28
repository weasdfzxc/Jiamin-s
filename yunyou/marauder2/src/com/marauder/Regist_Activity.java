package com.marauder;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Regist_Activity extends Activity implements OnClickListener {
	private String userName;
	private String password;
	private String repassword;
	private String email;
	private HttpThread httpThread;
	private Thread thread = null;
	private Context context;
	private Intent regist_login;
	private ProgressDialog progressDialog = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist);
		context = getApplicationContext();

		Button confirmButton = (Button) findViewById(R.id.confirm);
		confirmButton.setOnClickListener(this);

		httpThread = new HttpThread();
		httpThread.setUrl(getString(R.string.main_url) + "regist-json");
		httpThread.setHandler(new RegistHandler());
	}

	protected void onDestroy() {
		super.onDestroy();
		// 注意线程的stop方法不能真正的结束线程
		if (thread != null)
			thread.interrupt();
		thread = null;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.confirm:

			userName = ((EditText) findViewById(R.id.userName)).getText()
					.toString();
			password = ((EditText) findViewById(R.id.password)).getText()
					.toString();
			repassword = ((EditText) findViewById(R.id.repassword)).getText()
					.toString();
			email = ((EditText) findViewById(R.id.email)).getText().toString();
			regist_login = new Intent(this, Login_Activity.class);
			
			// 验证数据并将数据传入，此处仅作了简单的验证
			if (!userName.isEmpty() && !password.isEmpty() && !(email.isEmpty())) {
				if (userName.length() <= 12) {
					if (password.equals(repassword)) {
						if (email.indexOf("@") != -1) {
							progressDialog = ProgressDialog.show(Regist_Activity.this, "注册",
									"正在提交申请,请稍候！");
							Bundle values = new Bundle();
							values.putString("userName", userName);
							values.putString("password", password);
							values.putString("repassword", repassword);
							values.putString("email", email);
							regist_login.putExtras(values);
							httpThread.setData(values);
							thread = new Thread(httpThread);
							thread.start();
						}

						else {
							Toast.makeText(this, "邮箱格式错误", Toast.LENGTH_SHORT)
									.show();
						}
					} else {
						Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT)
								.show();
					}
				} else {
					Toast.makeText(this, "用户名大于12个字符", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				Toast.makeText(this, "请填完表单", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}

	}

	class RegistHandler extends Handler {

		public RegistHandler() {
			super();
		}

		public RegistHandler(Looper looper) {
			super(looper);
		}

		public void handleMessage(Message message) {
			String reason = null;
			try {
				JSONObject jsonObject = new JSONObject((String) message.obj);
				reason = jsonObject.optString("reason");
			} catch (JSONException e) {
				Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();
				progressDialog.dismiss();
				e.printStackTrace();
			} catch (NullPointerException e) {
				Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();
				progressDialog.dismiss();
			}
			if (!reason.equals("null")) {
				Toast.makeText(context, reason, Toast.LENGTH_SHORT).show();
				progressDialog.dismiss();
			} else {
				Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
				startActivity(regist_login);
				progressDialog.dismiss();
				finish();
			}

		}
	}
}
