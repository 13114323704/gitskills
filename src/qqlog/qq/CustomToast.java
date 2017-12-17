package qqlog.qq;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast extends Toast {

    static Toast toast;
    private int a = 1;

    public CustomToast(Context context) {
        // TODO Auto-generated constructor stub
        super(context);
    }

    public static Toast makeText(Context context, CharSequence text, int duration) {
        toast = new Toast(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.textview1);
        textView.setText(text);

        toast.setView(view);
        toast.setGravity(Gravity.TOP, 0, 30);
        toast.setDuration(duration);
        return toast;
    }

    public void show() {
        toast.show();
    }

    public static void setpositioncenter() {
        toast.setGravity(Gravity.CENTER, 0, 50);
    }

    public static void setpositionbottom() {
        toast.setGravity(Gravity.BOTTOM, 0, 50);
    }

}
