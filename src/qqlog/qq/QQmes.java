package qqlog.qq;

import java.util.Random;

import qqlog.qq.Mydialog.OnClickBtnListener;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class QQmes extends Activity {

	private EditText edittext;
	private Mydialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mes);

		Button button1 = (Button) this.findViewById(R.id.button1);
		Button button3 = (Button) this.findViewById(R.id.button3);
		final Button button4 = (Button) this.findViewById(R.id.button4);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QQmes.this, QQ.class);
				QQmes.this.startActivity(intent);
				overridePendingTransition(R.anim.push_right_in,
						R.anim.push_right_out);
			}
		});

		button3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QQmes.this, QQregister2.class);

				QQmes.this.startActivity(intent);

			}
		});
		Intent intent = getIntent();

		String data = intent.getStringExtra("data_s");

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
							String mes2 = "";
							for (int i = 0; i < 6; i++) {
								mes2 = mes2 + mes[i];
							}
							mes2 = mes2 + "（HQ登陆验证码），请在20分钟内完成操作。";
							SmsManager sm = SmsManager.getDefault();
							PendingIntent pi = PendingIntent.getBroadcast(
									QQmes.this, 0, new Intent(), 0);
							sm.sendTextMessage(textnumber, null, mes2, pi, null);

							dialog = new Mydialog(QQmes.this,
									R.layout.dialog_layout, R.style.dialogTheme);
							dialog.setT("提示");
							dialog.setM("恭喜您验证成功");
							dialog.setButtonLeftText("立即登录HQ");
							dialog.setButtonRightText("取消");
							dialog.show();
							dialog.setOnClickBtnListener(new OnClickBtnListener() {

								@Override
								public void onClickOk() {
									// TODO Auto-generated method stub
									Intent intent = new Intent(QQmes.this,
											MainTabActivity.class);
									QQmes.this.startActivity(intent);

								}

								@Override
								public void onClickCancel() {
									// TODO Auto-generated method stub
									dialog.cancel();

								}
							});

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
			Intent intent = new Intent(QQmes.this, QQ.class);
			QQmes.this.startActivity(intent);
			overridePendingTransition(R.anim.push_right_in,
					R.anim.push_right_out);
			return true;
		}

		return super.onKeyDown(keyCode, event);

	}
}
