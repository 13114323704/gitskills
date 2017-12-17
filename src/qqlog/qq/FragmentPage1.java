package qqlog.qq;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class FragmentPage1 extends Fragment {

    private ListView lisetview;
    private View view;
    public List<String> list = new ArrayList<String>();
    private BaseAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (list.size() == 0) {
            list.add("搜索项");
        }

        view = inflater.inflate(R.layout.fragment_1, container, false);

        lisetview = (ListView) view.findViewById(R.id.listviewfrag1);
        adapter = new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                LayoutInflater layoutInflater = LayoutInflater
                        .from(getActivity());
                View view;
                if (getItemId(position) == 0) {
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
                } else {
                    view = layoutInflater.inflate(
                            R.layout.fragment_1_messagelayout, null);
                    TextView textname = (TextView) view
                            .findViewById(R.id.textname);

                    textname.setText(list.get(position));
                }

                return view;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return position;
            }

            @Override
            public Object getItem(int position) {
                // TODO Auto-generated method stub
                return list.get(position);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return list.size();
            }
        };
        lisetview.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        MainTabActivity ma = (MainTabActivity) getActivity();
        ma.flashtitlemessage();
    }

    public void refresh(String s) {
        boolean flag = true;
        for (String string : list) {
            if (string.equals(s)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            list.add(s);
        }
    }

}
