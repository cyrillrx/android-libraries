package com.cyrillrx.android.widget.progress;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.cyrillrx.android.R;

import androidx.annotation.NonNull;

/**
 * @author Cyril Leroux
 *         Created on 02/01/15
 */
public class TextProgressBar extends ProgressBar {

    private static final String DEFAULT_TEXT = "";
    private static final int DEFAULT_COLOR = Color.BLACK;
    private static final float DEFAULT_TEXT_SIZE = 16f;

    private Paint textPaint;
    private Rect textBounds;

    private String text;
    private int textColor;
    private float textSize;

    public TextProgressBar(Context context) {
        super(context);
        init();
    }

    public TextProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TextProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TextProgressBar,
                0, 0);

        try {
            text = a.getString(R.styleable.TextProgressBar_text);
            textColor = a.getColor(R.styleable.TextProgressBar_textColor, DEFAULT_COLOR);
            textSize = a.getDimension(R.styleable.TextProgressBar_textSize, DEFAULT_TEXT_SIZE);
        } finally {
            a.recycle();
        }

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);

        textBounds = new Rect();
    }

    private void init() {
        text = DEFAULT_TEXT;
        textColor = DEFAULT_COLOR;
        textSize = DEFAULT_TEXT_SIZE;

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);

        textBounds = new Rect();
    }

    @Override
    protected synchronized void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        if (text == null || text.isEmpty()) {
            return;
        }

        // Calculate text bounds
        textPaint.getTextBounds(text, 0, text.length(), textBounds);

        // Calculate center position
        int x = getWidth() / 2 - textBounds.centerX();
        int y = getHeight() / 2 - textBounds.centerY();

        // Drawing the text
        canvas.drawText(text, x, y, textPaint);
    }

    public String getText() { return text; }

    public synchronized void setText(String text) { this.text = text; }

    public int getTextColor() { return textColor; }

    public synchronized void setTextColor(int textColor) {
        this.textColor = textColor;
        textPaint.setColor(this.textColor);
    }

    public float getTextSize() { return textSize; }

    public synchronized void setTextSize(float textSize) {
        this.textSize = textSize;
        textPaint.setTextSize(this.textSize);
    }
}
