package com.hbk.sinaweibodemo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import com.hbk.sinaweibodemo.R;
import com.hbk.sinaweibodemo.util.AppLogger;
import com.hbk.sinaweibodemo.util.Utility;
import org.apache.commons.lang3.StringUtils;


/**
 * Created by HongBinKim on 14/8/10.
 */
public class TabButton extends RadioButton {

    Drawable image;
    Drawable checkedImage;

    public TabButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TabButton);
        image = array.getDrawable(R.styleable.TabButton_image);
        checkedImage = array.getDrawable(R.styleable.TabButton_checkedImage);
        setCompoundDrawablesWithIntrinsicBounds(null, image, null, null);
        array.recycle();
    }

    @Override
    public void setChecked(boolean checked) {

        boolean isEmpty = StringUtils.isEmpty(getText());
        if (!isEmpty){
            super.setChecked(checked);
        }

        if (checked) {
            setCompoundDrawablesWithIntrinsicBounds(null, checkedImage, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, image, null, null);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableLeft = drawables[1];
            if (drawableLeft != null) {
                final float textWidth = getPaint().measureText(getText().toString());


                final int drawableHeight = drawableLeft.getIntrinsicHeight();


                int height = getHeight();


                if (textWidth == 0) {
                    canvas.translate(0, (height - drawableHeight) / 2);
                } else {
                    canvas.translate(0, 7);
                }

            }
        }
        super.onDraw(canvas);
    }
}
