package qqlog.qq;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class FragmentPage1_phone extends Fragment implements OnClickListener {

	private Button buttonsearch;
	private RelativeLayout relativelayout1, relativelayout2;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_1_phone, container, false);
		buttonsearch = (Button) view.findViewById(R.id.buttonsearch);
		relativelayout1 = (RelativeLayout) view
				.findViewById(R.id.relativelayout1);
		relativelayout2 = (RelativeLayout) view
				.findViewById(R.id.relativelayout2);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		buttonsearch.setOnClickListener(this);
		relativelayout1.setOnClickListener(this);
		relativelayout2.setOnClickListener(this);
	}

	@SuppressWarnings("static-access")
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.buttonsearch:
			MainTabActivity ma = (MainTabActivity) getActivity();
			ma.linesearch();
			break;
		case R.id.relativelayout1:
			CustomToast customToast = new CustomToast(getActivity()
					.getApplicationContext());
			customToast.makeText(getActivity().getApplicationContext(), "电话黄页",
					1500);
			customToast.setpositionbottom();
			customToast.show();
			break;
		case R.id.relativelayout2:
			CustomToast customToast2 = new CustomToast(getActivity()
					.getApplicationContext());
			customToast2.makeText(getActivity().getApplicationContext(), "通讯录",
					1500);
			customToast2.setpositionbottom();
			customToast2.show();
			break;
		}
	}
}
