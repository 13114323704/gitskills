package qqlog.qq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qqslidingmenu.SlidingMenu;

import qqlog.qq.Mydialog.OnClickBtnListener;

import android.os.Bundle;
import android.animation.ObjectAnimator;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class MainTabActivity extends FragmentActivity implements
        OnClickListener, OnTouchListener {

    private android.support.v4.app.FragmentManager fm;
    private android.support.v4.app.FragmentTransaction ft;

    private View viewBg;
    private ListView listview;
    private ImageButton imagebutton1;
    private TextView textvip;
    private PopupWindow popupwindow;
    private LinearLayout line_search, line_show;
    private RelativeLayout relativelayout1, relativelayout2, relativelayout3;
    private Button button2, buttonadd, buttonmore, buttonmessage, buttonphone,
            button_cancel;
    private EditText edittext1;
    private SlidingMenu mLeftMenu;
    private Mydialog dialog;
    private LayoutInflater layoutInflater;
    private FragmentTabHost mTabHost;
    private int[] images = new int[]{R.drawable.icon1, R.drawable.icon2,
            R.drawable.icon3, R.drawable.icon4, R.drawable.icon5};
    private String[] names = new String[]{"多人聊天", "加好友", "扫一扫", "面对面快传", "付款"};
    @SuppressWarnings("rawtypes")
    private Class fragmentArray[] = {FragmentPage1.class, FragmentPage2.class,
            FragmentPage3.class};
    private int mImageViewArray[] = {R.drawable.tab_message_btn,
            R.drawable.tab_person_btn, R.drawable.tab_trend_btn,};
    private String mTextviewArray[] = {"消息", "联系人", "动态"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_tab_layout);
        viewBg = (View) findViewById(R.id.ViewBg);
        line_search = (LinearLayout) this.findViewById(R.id.line_search);
        line_show = (LinearLayout) this.findViewById(R.id.line_show);
        relativelayout1 = (RelativeLayout) this
                .findViewById(R.id.relativelayout1);
        relativelayout2 = (RelativeLayout) this
                .findViewById(R.id.relativelayout2);
        relativelayout3 = (RelativeLayout) this
                .findViewById(R.id.relativelayout3);
        textvip = (TextView) this.findViewById(R.id.textvip);
        buttonmessage = (Button) this.findViewById(R.id.btnmessage);
        buttonphone = (Button) this.findViewById(R.id.btnphone);
        button2 = (Button) this.findViewById(R.id.button2);
        imagebutton1 = (ImageButton) this.findViewById(R.id.imagebutton1);
        buttonadd = (Button) this.findViewById(R.id.btnadd);
        buttonmore = (Button) this.findViewById(R.id.btnmore);
        button_cancel = (Button) this.findViewById(R.id.button_cancel);
        edittext1 = (EditText) this.findViewById(R.id.editText1);
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        buttonmessage.setOnClickListener(this);
        buttonphone.setOnClickListener(this);
        buttonadd.setOnClickListener(this);
        button_cancel.setOnClickListener(this);
        imagebutton1.setOnClickListener(this);
        textvip.setOnClickListener(this);
        mLeftMenu.setOnTouchListener(this);

        initView();
        mTabHost.setCurrentTab(1);
        viewBg.setVisibility(View.GONE);

    }

    private void initView() {
        // 实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        // 实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 得到fragment的个数
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中,
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.selector_tab_background);
        }

    }

    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            // mLeftMenu.toggle();
            if (mLeftMenu.open()) {
                mLeftMenu.closeMenu();
            } else {
                dialog = new Mydialog(MainTabActivity.this,
                        R.layout.dialog_layout, R.style.dialogTheme);
                dialog.setT("系统提示");
                dialog.setM("返回登录界面？");
                dialog.setButtonLeftText("确定");
                dialog.setButtonRightText("取消");
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                dialog.setOnClickBtnListener(new OnClickBtnListener() {

                    @Override
                    public void onClickOk() {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(MainTabActivity.this,
                                QQ.class);
                        MainTabActivity.this.startActivity(intent);
                        overridePendingTransition(R.anim.push_right_in,
                                R.anim.push_right_out);
                    }

                    @Override
                    public void onClickCancel() {
                        // TODO Auto-generated method stub
                        dialog.cancel();

                    }
                });
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    // 点击切换左侧菜单使用
    public void toggleMenu(View view) {
        if (mLeftMenu.open()) {
            imagebutton1.setAlpha(255);
        } else {
            imagebutton1.setAlpha(0);
        }
        mLeftMenu.toggle();

    }

    public void flashtitlemessage() {
        relativelayout1.setVisibility(View.GONE);
        relativelayout2.setVisibility(View.VISIBLE);
        relativelayout3.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        buttonadd.setVisibility(View.VISIBLE);
        buttonmore.setVisibility(View.GONE);
    }

    public void flashtitleperson() {
        relativelayout1.setVisibility(View.VISIBLE);
        relativelayout2.setVisibility(View.GONE);
        relativelayout3.setVisibility(View.GONE);
        button2.setVisibility(View.VISIBLE);
        buttonadd.setVisibility(View.GONE);
        buttonmore.setVisibility(View.GONE);
    }

    public void flashtitledongtai() {
        relativelayout1.setVisibility(View.GONE);
        relativelayout2.setVisibility(View.GONE);
        relativelayout3.setVisibility(View.VISIBLE);
        button2.setVisibility(View.GONE);
        buttonadd.setVisibility(View.GONE);
        buttonmore.setVisibility(View.VISIBLE);
    }

    public void lineshow() {
        line_show.setVisibility(View.VISIBLE);
        line_search.setVisibility(View.GONE);

        hideSoftInput();
    }

    public void linesearch() {
        line_show.setVisibility(View.GONE);
        line_search.setVisibility(View.VISIBLE);
        edittext1.setFocusable(true);
        edittext1.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) MainTabActivity.this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        switch (arg0.getId()) {
            case R.id.btnadd:
                ObjectAnimator rAnimator = ObjectAnimator.ofFloat(buttonadd,
                        "rotation", 0f, 225f);
                rAnimator.setDuration(300);
                rAnimator.start();

                LayoutInflater layoutInflater = LayoutInflater
                        .from(MainTabActivity.this);
                View view = layoutInflater.inflate(R.layout.popupadd, null);
                listview = (ListView) view.findViewById(R.id.listview1);

                List<Map<String, Object>> listitems = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < names.length; i++) {
                    Map<String, Object> listitem = new HashMap<String, Object>();
                    listitem.put("icon", images[i]);
                    listitem.put("text", names[i]);
                    listitems.add(listitem);
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(
                        MainTabActivity.this, listitems, R.layout.popupadditem,
                        new String[]{"icon", "text"}, new int[]{R.id.icon,
                        R.id.text});
                listview.setAdapter(simpleAdapter);
                listview.setOnItemClickListener(new OnItemClickListener() {

                    @SuppressWarnings("static-access")
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int arg2, long arg3) {
                        // TODO Auto-generated method stub
                        CustomToast customToast = new CustomToast(
                                MainTabActivity.this);
                        customToast.makeText(MainTabActivity.this,
                                "" + names[arg2], 1500);
                        customToast.setpositionbottom();
                        customToast.show();
                        popupwindow.dismiss();

                    }
                });

                popupwindow = new PopupWindow(view, 480, LayoutParams.WRAP_CONTENT,
                        true);
                popupwindow.setFocusable(true);
                popupwindow.setBackgroundDrawable(new BitmapDrawable());
                popupwindow.setOutsideTouchable(true);
                popupwindow.setAnimationStyle(R.style.addstyle);
                popupwindow.showAsDropDown(arg0);
                viewBg.setVisibility(View.VISIBLE);
                viewBg.startAnimation(AnimationUtils.loadAnimation(
                        MainTabActivity.this,
                        R.anim.anim_bookshelf_folder_editer_enter));
                popupwindow.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        // TODO Auto-generated method stub
                        viewBg.startAnimation(AnimationUtils.loadAnimation(
                                MainTabActivity.this,
                                R.anim.anim_bookshelf_folder_editer_exit));
                        viewBg.setVisibility(View.GONE);
                        ObjectAnimator rAnimator = ObjectAnimator.ofFloat(
                                buttonadd, "rotation", 135f, 360f);
                        rAnimator.setDuration(300);
                        rAnimator.start();
                    }
                });
                break;
            case R.id.imagebutton1:
                toggleMenu(arg0);

                break;

            case R.id.btnmessage:

                buttonmessage.setBackgroundResource(R.drawable.btnbeautify2);
                buttonphone.setBackgroundResource(R.drawable.btnbeautify3);
                ft.replace(R.id.realtabcontent, new FragmentPage1());
                ft.commit();
                break;
            case R.id.btnphone:
                buttonmessage.setBackgroundResource(R.drawable.btnbeautify4);
                buttonphone.setBackgroundResource(R.drawable.btnbeautify5);
                ft.replace(R.id.realtabcontent, new FragmentPage1_phone());
                ft.commit();
                break;
            case R.id.textvip:
                Toast.makeText(MainTabActivity.this, "开通会员", 1500).show();
                break;
            case R.id.button_cancel:
                lineshow();
                break;
        }

    }

    @Override
    public boolean onTouch(View arg0, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {

            int[] location = new int[2];
            imagebutton1.getLocationOnScreen(location);
            imagebutton1.setAlpha((255 - (((location[0] - 20) * 255) / 554)));

        }
        return false;
    }

    // 隐藏输入法
    private void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
