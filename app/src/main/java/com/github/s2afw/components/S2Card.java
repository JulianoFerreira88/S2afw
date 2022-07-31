package com.github.s2afw.components;

import android.content.Context;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

public class S2Card extends CardView {
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams params;

    public S2Card(@NonNull Context context, String text, S2ButtonClickListener onClick) {
        super(context);
        this.params = new LinearLayout.LayoutParams(-1, -2);
        params.setMargins(5, 10, 5, 10);
        this.linearLayout = new LinearLayout(context);
        this.linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        this.setLayoutParams(params);
        this.addView(this.linearLayout, params);
        linearLayout.addView(new S2Button(context, text, onClick));

    }
}
