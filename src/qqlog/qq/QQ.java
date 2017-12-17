package qqlog.qq;

import com.ant.liao.GifView;

import qqlog.qq.Mydialog.OnClickBtnListener;

import android.animation.ObjectAnimator;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class QQ extends Activity {

    private SharedPreferences sharedPreferences;
    private View viewBg;
    private Button btn1, btn2, btn3;
    private MediaPlayer mediaPlayer;
    private ToggleButton toggleButton;
    private EditText editText1, editText2;
    private ImageView imageview1, imageView2, imageView3;
    private Mydialog dialog, dialog2;
    private PopupWindow popupWindow, popupWindowlog;
    private LinearLayout linearlayout2, linearlayout3;
    private Animation translateAnim, alphaAnim, alphaAnim2;
    private Handler hander;
    private int runcount = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq);

        sharedPreferences = getSharedPreferences("mylogname", MODE_PRIVATE);
        viewBg = (View) findViewById(R.id.ViewBg);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        imageview1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageview2);
        imageView3 = (ImageView) findViewById(R.id.imageview3);
        toggleButton = (ToggleButton) findViewById(R.id.togglebutton);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        linearlayout2 = (LinearLayout) findViewById(R.id.linearlayout2);
        linearlayout3 = (LinearLayout) findViewById(R.id.linearlayout3);

        editText1.setText(sharedPreferences.getString("name", ""));
        editText1.setSelection(editText1.length());
        editText2.setText(sharedPreferences.getString("password", ""));
        editText2.setSelection(editText2.length());

        LayoutInflater Inflater2 = LayoutInflater.from(QQ.this);
        View viewloging = Inflater2.inflate(R.layout.popoploging, null);
        GifView gifloging = (GifView) viewloging.findViewById(R.id.gifloging);
        gifloging.setGifImage(R.drawable.loging);
        gifloging.setShowDimension(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        popupWindowlog = new PopupWindow(viewloging, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        hander = new Handler();

        translateAnim = new TranslateAnimation(0, 0, 250, 0);
        alphaAnim = new AlphaAnimation(0.0f, 1.0f);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(alphaAnim);
        set.addAnimation(translateAnim);
        set.setDuration(1500);
        linearlayout2.setAnimation(set);
        linearlayout3.setAnimation(set);
        // btn1.setAnimation(set);
        alphaAnim2 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnim2.setDuration(2000);
        imageview1.setAnimation(alphaAnim2);
        // 掉下弹起效果
        ObjectAnimator yObjectAnimator = ObjectAnimator.ofFloat(btn1, "y", btn1.getY(), 805.0f);

        yObjectAnimator.setDuration(2200);
        yObjectAnimator.setInterpolator(new BounceInterpolator());
        yObjectAnimator.setRepeatCount(0);
        yObjectAnimator.start();

        OnCheckedChangeListener listener = new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    LayoutInflater Inflater = LayoutInflater.from(QQ.this);
                    View v = Inflater.inflate(R.layout.popup2, null);
                    showpopupwindow2(v);

                }
            }
        };
        toggleButton.setOnCheckedChangeListener(listener);

        TextWatcher watcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                String textname = editText1.getText().toString();
                if (textname.length() > 0 && editText1.isFocused()) {
                    imageView2.setVisibility(View.VISIBLE);
                    imageView2.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            // TODO Auto-generated method stub
                            editText1.setText("");
                            imageView2.setVisibility(View.INVISIBLE);
                        }
                    });
                } else {
                    imageView2.setVisibility(View.INVISIBLE);
                }

                if (textname.equals("942845204")) {
                    imageview1.setImageDrawable(getResources().getDrawable(R.drawable.image2));
                } else {

                    imageview1.setImageDrawable(getResources().getDrawable(R.drawable.image1));
                }

                String textpassword = editText2.getText().toString();
                if (textpassword.length() > 0 && editText2.isFocused()) {
                    imageView3.setVisibility(View.VISIBLE);
                    imageView3.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            // TODO Auto-generated method stub
                            editText2.setText("");
                            imageView3.setVisibility(View.INVISIBLE);
                        }
                    });
                } else {
                    imageView3.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        };
        editText1.addTextChangedListener(watcher);
        editText2.addTextChangedListener(watcher);

        btn1.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("static-access")
            @Override
            public void onClick(View v) {
                PlayMusic(R.raw.dang);

                String name = ((EditText) findViewById(R.id.editText1)).getText().toString();
                String psd = ((EditText) findViewById(R.id.editText2)).getText().toString();

                if ((name.equals("942845204") && psd.equals("hhxxttxs")) || (name.equals(sharedPreferences.getString("name", "")) && psd.equals(sharedPreferences.getString("password", "")))) {

                    popupWindowlog.setAnimationStyle(R.style.fade);
                    popupWindowlog.showAtLocation(v, Gravity.CENTER, 0, 0);
                    viewBg.setVisibility(View.VISIBLE);
                    viewBg.startAnimation(AnimationUtils.loadAnimation(QQ.this, R.anim.anim_bookshelf_folder_editer_enter));
                    Intent intent = new Intent(QQ.this, MainTabActivity.class);
                    timing(intent);
                    // overridePendingTransition(R.anim.fade, R.anim.hold);
                    // overridePendingTransition(R.anim.alpha_scale,R.anim.my_alpha_action);
                    // overridePendingTransition(R.anim.my_scale_action,R.anim.my_alpha_action);
                    // overridePendingTransition(R.anim.wave_scale,R.anim.my_alpha_action);

                } else {
                    CustomToast customToast = new CustomToast(QQ.this);
                    customToast.makeText(QQ.this, "你输入的用户名或密码不正确", Toast.LENGTH_SHORT);
                    customToast.show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopupWindow();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QQ.this, QQregister.class);
                QQ.this.startActivity(intent);
            }
        });
        viewBg.setVisibility(View.GONE);
    }

    @SuppressWarnings("deprecation")
    private void showpopupwindow2(View v) {
        final TextView textperson;

        textperson = (TextView) v.findViewById(R.id.textview1);
        WindowManager wm1 = (WindowManager) this.getWindowManager();
        final int width = wm1.getDefaultDisplay().getWidth();

        popupWindow = new PopupWindow(v, width, 100, true);
        popupWindow.setFocusable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.fade);
        popupWindow.showAsDropDown(QQ.this.findViewById(R.id.linearlayout2), 0, 1);
        textperson.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String stt = textperson.getText().toString();
                editText1.setText(stt);
                editText1.setSelection(stt.length());
                editText2.setText("hhxxttxs");
                imageview1.setImageDrawable(getResources().getDrawable(R.drawable.image2));
                imageview1.setAnimation(alphaAnim);
                popupWindow.dismiss();
                if (toggleButton.isChecked()) {
                    toggleButton.setChecked(false);
                }

            }
        });
        v.findViewById(R.id.imageview2).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                popupWindow.dismiss();
                if (toggleButton.isChecked()) {
                    toggleButton.setChecked(false);
                }
                dialog = new Mydialog(QQ.this, R.layout.dialog_layout, R.style.dialogTheme);
                dialog.setT("删除账号");
                dialog.setM("你确定删除HQ账号942845204？");
                dialog.setButtonLeftText("取消");
                dialog.setButtonRightText("删除");
                dialog.show();
                dialog.setOnClickBtnListener(new OnClickBtnListener() {

                    @Override
                    public void onClickOk() {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }

                    @Override
                    public void onClickCancel() {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                });
            }
        });
    }

    private void showpopupWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(QQ.this);
        View view = layoutInflater.inflate(R.layout.popup, null);
        WindowManager wm1 = (WindowManager) this.getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        //测量view的宽度和高度
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);

        int height = view.getMeasuredHeight();
        final PopupWindow popupWindow2 = new PopupWindow(view, width - 20, height, true);
        popupWindow2.setFocusable(true);
        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        view.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://aq.qq.com/cn2/findpsw/mobile_v2/mobile_web_find_input_account?source_id=2756"));
                QQ.this.startActivity(intent);

            }
        });
        view.findViewById(R.id.button2).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(QQ.this, QQmes.class);
                QQ.this.startActivity(intent);
            }
        });

        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setOutsideTouchable(true);
        popupWindow2.setAnimationStyle(R.style.mystyle);
        popupWindow2.showAtLocation(QQ.this.findViewById(R.id.linearlayout1), Gravity.BOTTOM, 0, 0);
        viewBg.setVisibility(View.VISIBLE);
        viewBg.startAnimation(AnimationUtils.loadAnimation(QQ.this, R.anim.anim_bookshelf_folder_editer_enter));
        popupWindow2.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                viewBg.startAnimation(AnimationUtils.loadAnimation(QQ.this, R.anim.anim_bookshelf_folder_editer_exit));
                viewBg.setVisibility(View.GONE);
            }
        });
    }

    private void PlayMusic(int MusicId) {

        mediaPlayer = MediaPlayer.create(this, MusicId);
        mediaPlayer.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dialog2 = new Mydialog(QQ.this, R.layout.dialog_layout, R.style.dialogTheme);
            dialog2.setT("系统提示");
            dialog2.setM("退出HQ？");
            dialog2.setButtonLeftText("狠心退出");
            dialog2.setButtonRightText("还是算了");
            dialog2.show();
            dialog2.setOnClickBtnListener(new OnClickBtnListener() {

                @Override
                public void onClickOk() {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                    dialog2.dismiss();
                }

                @Override
                public void onClickCancel() {
                    // TODO Auto-generated method stub
                    dialog2.cancel();
                }
            });
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (toggleButton.isChecked()) {
            toggleButton.setChecked(false);
        }
        return super.onTouchEvent(event);
    }

    // 登录中效果
    public void timing(final Intent intent) {
        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (runcount == 1) {
                    if (popupWindowlog != null && popupWindowlog.isShowing()) {
                        popupWindowlog.dismiss();
                        viewBg.startAnimation(AnimationUtils.loadAnimation(QQ.this, R.anim.anim_bookshelf_folder_editer_exit));
                        viewBg.setVisibility(View.GONE);
                        QQ.this.startActivity(intent);
                        overridePendingTransition(R.anim.fade, R.anim.hold);
                    }

                    hander.removeCallbacks(this);
                }
                hander.postDelayed(this, 0);
                runcount++;
            }
        };
        hander.postDelayed(runnable, 2000);
    }

}
