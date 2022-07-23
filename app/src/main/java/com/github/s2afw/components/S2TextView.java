package com.github.s2afw.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import com.github.s2afw.R;

public class S2TextView extends androidx.appcompat.widget.AppCompatTextView {
    private String text;

    @SuppressLint("ResourceAsColor")
    public S2TextView(Context context, String text) {
        super(context);
        this.text = text;
        this.setText(this.text);
        this.setPadding(5,5,5,5);
        this.setBackgroundColor(R.color.S2TextView);
        this.setTextSize(14f);
    }
}
