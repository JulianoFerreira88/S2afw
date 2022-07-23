package com.github.s2afw.components;

import android.content.Context;

public class S2Button extends androidx.appcompat.widget.AppCompatButton {
    private String text;

    public S2Button(Context context, String text, S2ButtonClickListener onCLick) {
        super(context);
        this.text = text;
        this.setText(text);
        this.setTextSize(14f);
        this.setPadding(5, 5, 5, 5);
        this.setOnClickListener(onCLick);

    }
}
