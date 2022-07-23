package com.github.s2afw.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.github.s2afw.R;

public class S2Button extends androidx.appcompat.widget.AppCompatButton {
    private String text;

    @SuppressLint("ResourceAsColor")
    public S2Button(Context context, String text, S2ButtonClickListener onCLick) {
        super(context);
        this.text = text;
        this.setText(text);
        this.setTextSize(14f);
        this.setPadding(5, 5, 5, 5);
        this.setOnClickListener(onCLick);
        this.setBackgroundColor(getResources().getColor(R.color.S2TextView));

    }
}
