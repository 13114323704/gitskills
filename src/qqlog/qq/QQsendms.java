package qqlog.qq;

import java.util.Random;

import qqlog.qq.Mydialog.OnClickBtnListener;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QQsendms extends Activity {

	private SharedPreferences sharedPreferences;
	private Button button1;
	private Button button3;
	private Button button4;
	private TextView textview1;
	private EditText edittext1;
	private Mydialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sendms);

		sharedPreferences = getSharedPreferences("mylogname", MODE_PRIVATE);

		button1 = (Button) this.findViewById(R.id.button1);
		button3 = (Button) this.findViewById(R.id.button3);
		button4 = (Button) this.findViewById(R.id.button4);
		textview1 = (TextView) this.findViewById(R.id.textview1);
		edittext1 = (EditText) this.findViewById(R.id.edittext1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QQsendms.this, QQregister.class);
				QQsendms.this.startActivity(intent);
				overridePendingTransition(R.anim.push_right_in,
						R.anim.push_right_out);

			}
		});
		Intent intent = getIntent();
		String s1 = intent.getStringExtra("data_phone1");
		final String s2 = intent.getStringExtra("data_phone2");
		final String s3 = intent.getStringExtra("messagepassword");
		SpannableString st = new SpannableString("HQ 已给你的手机号码 " + s1 + " - "
				+ s2 + " " + "发送了一条验证短信。");
		st.setSpan(new ForegroundColorSpan(getResources()
				.getColor(R.color.blue)), 12, 15 + s1.length() + s2.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		textview1.setText(st);

		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Random random = new Random();
				int[] mes = new int[6];
				for (int i = 0; i < 6; i++) {
					mes[i] = (int) (random.nextInt(9) + 1);
				}
				String mes2 = "验证码为：";
				for (int i = 0; i < 6; i++) {
					mes2 = mes2 + mes[i];
				}
				SmsManager sm = SmsManager.getDefault();
				PendingIntent pi = PendingIntent.getBroadcast(QQsendms.this, 0,
						new Intent(), 0);
				sm.sendTextMessage(s2, null, mes2, pi, null);
				MyCount mc = new MyCount(60000, 1000);
				mc.start();

			}
		});

		TextWatcher watcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

				String str = edittext1.getText().toString();
				if (str.length() >= 4) {

					button4.setBackgroundResource(R.drawable.button_bg);
					button4.setTextColor(getResources().getColor(R.color.white));

					button4.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							String edittext = edittext1.getText().toString();
							if (edittext.equals(s3)) {
								Random random = new Random();
								final int[] name = new int[9];
								final int[] password = new int[6];
								for (int i = 0; i < 9; i++) {
									name[i] = (int) (random.nextInt(9) + 1);
								}
								for (int i = 0; i < 6; i++) {
									password[i] = (int) (random.nextInt(9) + 1);
								}
								String mes2 = "【HQ】恭喜您已完成HQ注册，您的用户名为：";

								for (int i = 0; i < 9; i++) {
									mes2 = mes2 + name[i];
								}
								mes2 = mes2 + "，密码为：";
								for (int i = 0; i < 6; i++) {
									mes2 = mes2 + password[i];
								}
								mes2 = mes2 + "。请妥善保管！";
								SmsManager sm = SmsManager.getDefault();
								PendingIntent pi = PendingIntent.getBroadcast(
										QQsendms.this, 0, new Intent(), 0);
								sm.sendTextMessage(s2, null, mes2, pi, null);

								dialog = new Mydialog(QQsendms.this,
										R.layout.dialog_layout,
										R.style.dialogTheme);
								dialog.setT("提示");
								dialog.setM("恭喜您注册成功");
								dialog.setButtonLeftText("立即登录");
								dialog.setButtonRightText("取消");
								dialog.show();
								dialog.setOnClickBtnListener(new OnClickBtnListener() {

									@Override
									public void onClickOk() {
										// TODO Auto-generated method stub
										Intent intent = new Intent(
												QQsendms.this, QQ.class);

										Editor editor = sharedPreferences
												.edit();
										String a = "";
										for (int i = 0; i < 9; i++) {
											a = a + name[i];
										}
										String b = "";
										for (int i = 0; i < 6; i++) {
											b = b + password[i];
										}
										editor.putString("name", a);
										editor.putString("password", b);
										editor.commit();

										QQsendms.this.startActivity(intent);
										overridePendingTransition(
												R.anim.push_right_in,
												R.anim.push_right_out);
									}

									@Override
									public void onClickCancel() {
										// TODO Auto-generated method stub
										dialog.dismiss();
									}
								});

							} else {
								final Mydialog dialog = new Mydialog(
										QQsendms.this, R.layout.dialog_layout,
										R.style.dialogTheme);
								dialog.setT("提示");
								dialog.setM("验证码输入错误");
								dialog.setButtonLeftText("重新输入");
								dialog.setButtonRightText("退出注册");
								dialog.show();
								dialog.setOnClickBtnListener(new OnClickBtnListener() {

									@Override
									public void onClickOk() {
										// TODO Auto-generated method stub
										dialog.dismiss();
									}

									@Override
									public void onClickCancel() {
										// TODO Auto-generated method stub
										Intent intent = new Intent(
												QQsendms.this, QQ.class);
										QQsendms.this.startActivity(intent);
										overridePendingTransition(
												R.anim.push_right_in,
												R.anim.push_right_out);
									}
								});
							}
						}
					});

				} else {

					button4.setBackgroundResource(R.drawable.btnbeautify7);
					button4.setTextColor(getResources().getColor(R.color.grey));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		};
		edittext1.addTextChangedListener(watcher);

	}

	class MyCount extends CountDownTimer {

		public MyCount(long millisInFuture, long countDownlnterval) {
			super(millisInFuture, countDownlnterval);
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			button3.setTextColor(getResources().getColor(R.color.blue));
			button3.setEnabled(true);
			button3.setText("重新发送");

		}

		@Override
		public void onTick(long arg0) {
			// TODO Auto-generated method stub
			button3.setTextColor(getResources().getColor(R.color.grey));
			button3.setEnabled(false);
			button3.setText((arg0 / 1000) + "秒后重试");
		}
	}

}
