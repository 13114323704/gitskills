package qqlog.qq;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class FragmentPage3 extends Fragment {

	private ListView listview;
	private String[] str = new String[] { "搜索项", "布局项", "空白标签一", "游戏", "看点",
			"京东购物", "阅读", "音乐", "直播", "热门活动", "空白便签二", "附近的群", "吃喝玩乐", "同城服务" };
	private int[] image = new int[] { R.drawable.rightlisticon1,
			R.drawable.rightlisticon2, R.drawable.rightlisticon3,
			R.drawable.rightlisticon4, R.drawable.rightlisticon5,
			R.drawable.rightlisticon6, R.drawable.rightlisticon7,
			R.drawable.rightlisticon8, R.drawable.rightlisticon9,
			R.drawable.rightlisticon10 };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_3, container, false);
		// return inflater.inflate(R.layout.fragment_1, null);
		listview = (ListView) view.findViewById(R.id.listview);
		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				LayoutInflater layoutInflater = LayoutInflater
						.from(getActivity());
				View view;

				if (getItemId(arg0) == 0) {
					view = layoutInflater.inflate(
							R.layout.fragment_3_searchview, null);
					view.findViewById(R.id.buttonsearch).setOnClickListener(
							new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									MainTabActivity ma = (MainTabActivity) getActivity();
									ma.linesearch();
								}
							});
				} else if (getItemId(arg0) == 1) {
					view = layoutInflater.inflate(R.layout.fragment_3_label,
							null);
					view.findViewById(R.id.textView1).setOnClickListener(
							new OnClickListener() {

								@SuppressWarnings("static-access")
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									CustomToast customToast = new CustomToast(
											getActivity()
													.getApplicationContext());
									customToast.makeText(getActivity()
											.getApplicationContext(), "好友动态",
											1500);

									customToast.setpositionbottom();
									customToast.show();
								}
							});
					view.findViewById(R.id.textView2).setOnClickListener(
							new OnClickListener() {

								@SuppressWarnings("static-access")
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									CustomToast customToast = new CustomToast(
											getActivity()
													.getApplicationContext());
									customToast.makeText(getActivity()
											.getApplicationContext(), "附近",
											1500);

									customToast.setpositionbottom();
									customToast.show();
								}
							});
					view.findViewById(R.id.textView3).setOnClickListener(
							new OnClickListener() {

								@SuppressWarnings("static-access")
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									CustomToast customToast = new CustomToast(
											getActivity()
													.getApplicationContext());
									customToast.makeText(getActivity()
											.getApplicationContext(), "兴趣部落",
											1500);

									customToast.setpositionbottom();
									customToast.show();
								}
							});

				} else if (getItemId(arg0) == 2 || getItemId(arg0) == 10) {
					view = layoutInflater.inflate(R.layout.label, null);
				} else {
					view = layoutInflater.inflate(
							R.layout.fragment_3_groupview, null);
					ImageView imageView2 = (ImageView) view
							.findViewById(R.id.imageview2);
					imageView2.setImageResource(R.drawable.rightlisticon11);
					ImageView imageView1 = (ImageView) view
							.findViewById(R.id.imageview1);
					if (arg0 < 10) {
						imageView1.setImageResource(image[arg0 - 3]);
					} else {
						imageView1.setImageResource(image[arg0 - 4]);
					}
					TextView textView = (TextView) view
							.findViewById(R.id.textview1);
					textView.setText(getItem(arg0).toString());
				}
				return view;
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return arg0;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return str[arg0];
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return str.length;
			}
		};
		listview.setAdapter(adapter);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		MainTabActivity ma = (MainTabActivity) getActivity();
		ma.flashtitledongtai();

		listview.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("static-access")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (arg2 == 3 || arg2 == 4 || arg2 == 5 || arg2 == 6
						|| arg2 == 7 || arg2 == 8 || arg2 == 9 || arg2 == 11
						|| arg2 == 12 || arg2 == 13) {
					CustomToast customToast = new CustomToast(getActivity()
							.getApplicationContext());
					customToast.makeText(getActivity().getApplicationContext(),
							"" + listview.getItemAtPosition(arg2), 1500);
					customToast.setpositionbottom();
					customToast.show();
				}

			}
		});
	}

}