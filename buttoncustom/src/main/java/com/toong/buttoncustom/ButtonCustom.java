package com.toong.buttoncustom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ButtonCustom extends LinearLayout {
    private static final float DEFAULT_DRAWABLE_SIZE = 50;
    private static final int DEFAULT_TITLE_COLOR = Color.DKGRAY;
    private static final int DEFAULT_BACKGROUND_COLOR = Color.LTGRAY;
    private static final int DEFAULT_TITLE_SIZE = 20; // 20sp
    private static final int DEFAULT_TITLE_MARGIN_START = 5; // 5sp
    private static final int DEFAULT_CORNER = 0;
    private static final int DEFAULT_BORDER_WIDTH = 0;

    private ImageView ivLeft;
    private TextView tvTitle;

    private int backgroundColor;
    private int disableBackgroundColor;

    private int borderWidth;
    private int borderColor;
    private int disableBorderColor;

    private GradientDrawable gradientDrawable;

    float mDefaultAlpha;
    float mDisableAlpha;
    float mPressedAlpha;
    float mSelectedAlpha;

    public ButtonCustom(Context context) {
        this(context, null);
    }

    public ButtonCustom(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonCustom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_button, this, true);
        ivLeft = (ImageView) findViewById(R.id.image_left);
        tvTitle = (TextView) findViewById(R.id.text_title);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ButtonCustom);
        gradientDrawable = new GradientDrawable();
        displayTitle(ta);
        displayLeftDrawable(ta);
        displayBackgroundAndBorder(ta);
        displayCorner(ta);
        displayAlpha(ta);
        setEnableBackgroundAndBorder();

        ta.recycle();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            setEnableBackgroundAndBorder();
        } else {
            setDisableBackgroundAndBorder();
        }
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (pressed) {
            setAlpha(mPressedAlpha);
        } else {
            setAlpha(mDefaultAlpha);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return true;
        }
        if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
            Log.d("TouchTest", "Touch down");
            setPressed(true);
        } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
            Log.d("TouchTest", "Touch up");
            setPressed(false);
            float x = event.getX();
            float y = event.getY();
            boolean isInside = (x > 0 && x < getWidth() && y > 0 && y < getHeight());
            if (isInside) {
                performClick();
            }
        }
        return true;
    }

    private void displayLeftDrawable(TypedArray ta) {
        Drawable leftDrawable = ta.getDrawable(R.styleable.ButtonCustom_bc_leftDrawable);
        float drawableWidth =
                ta.getDimension(R.styleable.ButtonCustom_bc_drawableWidth, DEFAULT_DRAWABLE_SIZE);
        float drawableHeight =
                ta.getDimension(R.styleable.ButtonCustom_bc_drawableHeight, DEFAULT_DRAWABLE_SIZE);

        if (leftDrawable != null) {
            ivLeft.setImageDrawable(leftDrawable);
            ivLeft.getLayoutParams().width = (int) drawableWidth;
            ivLeft.getLayoutParams().height = (int) drawableHeight;
        } else {
            ivLeft.setVisibility(View.GONE);
        }
    }

    private void displayTitle(TypedArray ta) {
        String title = ta.getString(R.styleable.ButtonCustom_bc_title);
        int titleColor = ta.getColor(R.styleable.ButtonCustom_bc_titleColor, DEFAULT_TITLE_COLOR);

        int titleSize = ta.getDimensionPixelSize(R.styleable.ButtonCustom_bc_titleSize,
                (int) (DEFAULT_TITLE_SIZE * getResources().getDisplayMetrics().scaledDensity));
        int titleMarginStart =
                ta.getDimensionPixelSize(R.styleable.ButtonCustom_bc_titleMarginStart,
                        ivLeft.getVisibility() == VISIBLE && title != null && !title.isEmpty()
                                ? (int) (DEFAULT_TITLE_MARGIN_START
                                * getResources().getDisplayMetrics().scaledDensity) : 0);

        tvTitle.setText(title);
        tvTitle.setTextColor(titleColor);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        ((LinearLayout.LayoutParams) tvTitle.getLayoutParams()).setMarginStart(titleMarginStart);
    }

    private void displayBackgroundAndBorder(TypedArray ta) {
        if (getBackground() != null) {
            backgroundColor = ((ColorDrawable) getBackground()).getColor();
        } else {
            backgroundColor = DEFAULT_BACKGROUND_COLOR;
        }

        disableBackgroundColor =
                ta.getColor(R.styleable.ButtonCustom_bc_disableBackgroundColor, backgroundColor);

        borderColor = ta.getColor(R.styleable.ButtonCustom_bc_borderColor, -1);
        disableBorderColor =
                ta.getColor(R.styleable.ButtonCustom_bc_disableBorderColor, borderColor);

        borderWidth = (int) ta.getDimension(R.styleable.ButtonCustom_bc_borderWidth,
                DEFAULT_BORDER_WIDTH);
    }

    private void displayCorner(TypedArray ta) {
        float corner = ta.getDimension(R.styleable.ButtonCustom_bc_corner, DEFAULT_CORNER);
        float cornerTopLeft = ta.getDimension(R.styleable.ButtonCustom_bc_cornerTopLeft, corner);
        float cornerTopRight = ta.getDimension(R.styleable.ButtonCustom_bc_cornerTopRight, corner);
        float cornerBottomRight =
                ta.getDimension(R.styleable.ButtonCustom_bc_cornerBottomRight, corner);
        float cornerBottomLeft =
                ta.getDimension(R.styleable.ButtonCustom_bc_cornerBottomLeft, corner);
        gradientDrawable.setCornerRadii(new float[] {
                cornerTopLeft, cornerTopLeft, cornerTopRight, cornerTopRight, cornerBottomRight,
                cornerBottomRight, cornerBottomLeft, cornerBottomLeft
        });
    }

    private void displayAlpha(TypedArray ta) {
        mDefaultAlpha = getAlpha();
        mDisableAlpha = ta.getFloat(R.styleable.ButtonCustom_bc_disabled_alpha, mDefaultAlpha);
        mPressedAlpha = ta.getFloat(R.styleable.ButtonCustom_bc_pressed_alpha, mDefaultAlpha);
        mSelectedAlpha = ta.getFloat(R.styleable.ButtonCustom_bc_selected_alpha, mDefaultAlpha);
    }

    private void setEnableBackgroundAndBorder() {
        gradientDrawable.setColor(backgroundColor);
        gradientDrawable.setStroke(borderWidth, borderColor);
        this.setBackground(gradientDrawable);
        setAlpha(mDefaultAlpha);
    }

    private void setDisableBackgroundAndBorder() {
        gradientDrawable.setColor(disableBackgroundColor);
        gradientDrawable.setStroke(borderWidth, disableBorderColor);
        this.setBackground(gradientDrawable);
        setAlpha(mDisableAlpha);
    }
}