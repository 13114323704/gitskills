package qqlog.qq;

import android.text.TextPaint;
import android.text.style.URLSpan;

public class URLSpanNoUnderline extends URLSpan {
    public URLSpanNoUnderline(String url) {
        super(url);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        // TODO Auto-generated method stub
        super.updateDrawState(ds);


        ds.setUnderlineText(false);

    }

}
