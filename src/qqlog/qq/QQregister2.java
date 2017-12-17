package qqlog.qq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class QQregister2 extends Activity {
	private ListView list;

	Button button1;

	String[] st = { "中国", "香港", "澳门", "台湾", "美国", "英国", "澳大利亚", "加拿大", "德国",
			"意大利", "俄罗斯", "白俄罗斯", "波兰", "丹麦", "法国", "测试数据"

	};
	String[] st2 = { "+86", "+87", "+88", "+89", "+90", "+91", "+92", "+93",
			"+94", "+95", "+96", "+97", "+98", "+99", "+100",
			"+9999999999999999"

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register2);

		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < st.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();

			listItem.put("country", st[i]);
			listItem.put("number", st2[i]);
			listItems.add(listItem);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.array_item, new String[] { "country", "number" },
				new int[] { R.id.textview1, R.id.textview2 });
		list = (ListView) findViewById(R.id.list);

		list.setAdapter(simpleAdapter);

		button1 = (Button) this.findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QQregister2.this, QQregister.class);
				QQregister2.this.startActivity(intent);
				overridePendingTransition(R.anim.push_right_in,
						R.anim.push_right_out);

			}
		});

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				TextView text1 = (TextView) arg1.findViewById(R.id.textview1);

				TextView text2 = (TextView) arg1.findViewById(R.id.textview2);
				String s = text1.getText().toString() + " "
						+ text2.getText().toString();
				String textnumber2 = text2.getText().toString();

				Intent intent = null;
				Intent intent2 = getIntent();
				String str = intent2.getStringExtra("register");
				if (str != null) {
					intent = new Intent(QQregister2.this, QQregister.class);

				} else {
					intent = new Intent(QQregister2.this, QQmes.class);

				}
				intent.putExtra("data_s", s);
				intent.putExtra("data_s2", textnumber2);
				QQregister2.this.startActivity(intent);
				overridePendingTransition(R.anim.push_right_in,
						R.anim.push_right_out);
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(QQregister2.this, QQregister.class);
			QQregister2.this.startActivity(intent);
			overridePendingTransition(R.anim.push_right_in,
					R.anim.push_right_out);
			return true;
		}

		return super.onKeyDown(keyCode, event);

	}
}
