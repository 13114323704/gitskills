package qqlog.qq;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class FragmentPage2 extends Fragment {

	private ExpandableListView expandListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_2, container, false);

		// return inflater.inflate(R.layout.fragment_2, null);

		expandListView = (ExpandableListView) view
				.findViewById(R.id.expandlistview);

		ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

			private String[] armTypes = new String[] { "搜索项", "布局项", "空白标签一",
					"特别关心", "常用群聊", "空白标签二", "分组一", "分组二", "分组三", "分组四", "分组五",
					"空白标签三", "手机通讯录", "我的设备" };
			private String[][] arms = new String[][] { { "" }, { "" }, { "" },
					{ "开心1号", "开心2号", "开心3号", "开心4号" },
					{ "开心5号", "开心6号", "开心7号", "开心8号" }, { "" },
					{ "开心9号", "开心10号", "开心11号", "开心12号" },
					{ "开心13号", "开心14号", "开心15号", "开心16号" },
					{ "开心17号", "开心18号", "开心19号", "开心20号" },
					{ "开心21号", "开心22号", "开心23号", "开心24号" },
					{ "开心25号", "开心26号", "开心27号", "开心28号" }, { "" },
					{ "哥哥", "姐姐" }, { "我的电脑", "发现新设备" }, };

			// 获取指定组位置、指定子列表项处的子列表项数据
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				return arms[groupPosition][childPosition];
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				return childPosition;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				return arms[groupPosition].length;
			}

			// 该方法决定每个子选项的外观
			@Override
			public View getChildView(final int groupPosition,
					final int childPosition, boolean isLastChild,
					View convertView, ViewGroup parent) {

				LayoutInflater layoutInflater = LayoutInflater
						.from(getActivity().getApplicationContext());
				View view = layoutInflater.inflate(
						R.layout.fragment_2_childview, null);
				TextView textview1 = (TextView) view
						.findViewById(R.id.textview1);
				textview1.setText(getChild(groupPosition, childPosition)
						.toString());
				view.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try {
							FragmentPage1 fragmentPage1 = (FragmentPage1) getActivity()
									.getSupportFragmentManager()
									.findFragmentByTag("消息");
							fragmentPage1.refresh(getChild(groupPosition,
									childPosition).toString());
						} catch (Exception e) {
							// TODO: handle exception
						}

					}
				});
				return view;
			}

			// 获取指定组位置处的组数据
			@Override
			public Object getGroup(int groupPosition) {
				return armTypes[groupPosition];
			}

			@Override
			public int getGroupCount() {
				return armTypes.length;
			}

			@Override
			public long getGroupId(int groupPosition) {
				return groupPosition;
			}

			// 该方法决定每个组选项的外观
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {

				LayoutInflater layoutInflater = LayoutInflater
						.from(getActivity().getApplicationContext());
				View view;
				if (getGroupId(groupPosition) == 0) {
					view = layoutInflater.inflate(
							R.layout.fragment_2_groupview_search, null);

					view.findViewById(R.id.buttonsearch).setOnClickListener(
							new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									MainTabActivity ma = (MainTabActivity) getActivity();
									ma.linesearch();
								}
							});

				} else if (getGroupId(groupPosition) == 1) {
					view = layoutInflater.inflate(
							R.layout.fragment_2_groupview_label, null);
					view.findViewById(R.id.textView1).setOnClickListener(
							new OnClickListener() {

								@SuppressWarnings("static-access")
								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									CustomToast customToast = new CustomToast(
											getActivity()
													.getApplicationContext());
									customToast.makeText(getActivity()
											.getApplicationContext(), "新朋友",
											1500);

									customToast.setpositionbottom();
									customToast.show();

								}
							});
					view.findViewById(R.id.textView2).setOnClickListener(
							new OnClickListener() {

								@SuppressWarnings("static-access")
								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									CustomToast customToast = new CustomToast(
											getActivity()
													.getApplicationContext());
									customToast.makeText(getActivity()
											.getApplicationContext(), "特别关心",
											1500);
									customToast.setpositionbottom();
									customToast.show();

								}
							});
					view.findViewById(R.id.textView3).setOnClickListener(
							new OnClickListener() {

								@SuppressWarnings("static-access")
								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									CustomToast customToast = new CustomToast(
											getActivity()
													.getApplicationContext());
									customToast.makeText(getActivity()
											.getApplicationContext(), "群组",
											1500);
									customToast.setpositionbottom();
									customToast.show();

								}
							});
					view.findViewById(R.id.textView4).setOnClickListener(
							new OnClickListener() {

								@SuppressWarnings("static-access")
								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									CustomToast customToast = new CustomToast(
											getActivity()
													.getApplicationContext());
									customToast.makeText(getActivity()
											.getApplicationContext(), "公众号",
											1500);
									customToast.setpositionbottom();
									customToast.show();

								}
							});
				} else if (getGroupId(groupPosition) == 2
						|| getGroupId(groupPosition) == 5
						|| getGroupId(groupPosition) == 11) {
					view = layoutInflater.inflate(R.layout.label, null);
				} else {
					view = layoutInflater.inflate(
							R.layout.fragment_2_groupview, null);
					ImageView imageview1 = (ImageView) view
							.findViewById(R.id.imageview1);
					TextView textview1 = (TextView) view
							.findViewById(R.id.textview1);
					textview1.setText(getGroup(groupPosition).toString());
					if (isExpanded)// 大组展开时
						imageview1.setImageDrawable(getResources().getDrawable(
								R.drawable.listicon2));
					else
						// 大组合并时
						imageview1.setImageDrawable(getResources().getDrawable(
								R.drawable.listicon1));

				}

				return view;
			}

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				return true;
			}

			@Override
			public boolean hasStableIds() {
				return true;
			}
		};

		expandListView.setGroupIndicator(null);

		expandListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (arg2 == 0 || arg2 == 1 || arg2 == 2 || arg2 == 5
						|| arg2 == 11) {
					return true;
				}
				return false;
			}
		});

		expandListView.setAdapter(adapter);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		MainTabActivity ma = (MainTabActivity) getActivity();
		ma.flashtitleperson();

	}

}