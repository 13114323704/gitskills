package qqlog.qq;

import java.util.Random;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QQregister extends Activity {
	private TextView textview1;
	private EditText edittext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		textview1 = (TextView) this.findViewById(R.id.textview1);
		SpannableString st = new SpannableString("我已阅读并同意使用条款和隐私政策");
		st.setSpan(new URLSpanNoUnderline(
				"http://zc.qq.com/phone/agreement_chs.html"), 7, 16,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		textview1.setText(st);
		textview1.setMovementMethod(LinkMovementMethod.getInstance());

		Button button1 = (Button) this.findViewById(R.id.button1);
		Button button3 = (Button) this.findViewById(R.id.button3);
		final Button button4 = (Button) this.findViewById(R.id.button4);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QQregister.this, QQ.class);
				QQregister.this.startActivity(intent);
				overridePendingTransition(R.anim.push_right_in,
						R.anim.push_right_out);
			}
		});

		button3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QQregister.this, QQregister2.class);
				intent.putExtra("register", "re");
				QQregister.this.startActivity(intent);

			}
		});

		Intent intent = getIntent();
		String data = intent.getStringExtra("data_s");
		final String data2 = intent.getStringExtra("data_s2");
		if (data != null) {
			button3.setText(data);
		} else {
			button3.setText("中国 +86");
		}

		edittext = (EditText) this.findViewById(R.id.edittext1);

		TextWatcher watcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

				// 输入十一个数字之后做的事情

				// Pattern p = Pattern.compile("[0-9]{11}");
				// Matcher m = p.matcher(text);
				// if (m.matches()) {
				// button4.setBackgroundResource(R.drawable.button_bg);
				// button4.setTextColor(getResources().getColor(R.color.white));
				// }
				final String textnumber = edittext.getText().toString();

				if (textnumber.length() == 11) {

					button4.setBackgroundResource(R.drawable.button_bg);
					button4.setTextColor(getResources().getColor(R.color.white));

					button4.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Random random = new Random();
							int[] mes = new int[6];
							for (int i = 0; i < 6; i++) {
								mes[i] = (int) (random.nextInt(9) + 1);
							}
							String mes2 = "", messagepassword = "";
							for (int i = 0; i < 6; i++) {
								mes2 = mes2 + mes[i];
							}
							for (int i = 0; i < 6; i++) {
								messagepassword = messagepassword + mes[i];
							}
							mes2 = mes2 + "（HQ注册验证码），请在20分钟内完成注册。如非本人操作，请忽略。";
							SmsManager sm = SmsManager.getDefault();
							PendingIntent pi = PendingIntent.getBroadcast(
									QQregister.this, 0, new Intent(), 0);
							sm.sendTextMessage(textnumber, null, mes2, pi, null);

							Intent intent = new Intent(QQregister.this,
									QQsendms.class);
							intent.putExtra("messagepassword", messagepassword);
							if (data2 != null) {
								intent.putExtra("data_phone1", data2);
							} else {

								intent.putExtra("data_phone1", "+86");
							}
							intent.putExtra("data_phone2", textnumber);

							QQregister.this.startActivity(intent);
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

		edittext.addTextChangedListener(watcher);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(QQregister.this, QQ.class);
			QQregister.this.startActivity(intent);
			overridePendingTransition(R.anim.push_right_in,
					R.anim.push_right_out);
			return true;
		}

		return super.onKeyDown(keyCode, event);

	}
}
