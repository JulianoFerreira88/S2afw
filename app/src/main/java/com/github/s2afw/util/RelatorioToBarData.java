package com.github.s2afw.util;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.s2afw.model.Relatorio;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class RelatorioToBarData {
    private Relatorio relatorio;

    public RelatorioToBarData(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public BarData getBarData() {
        BarData barData = new BarData();
        List<BarEntry> entries = new ArrayList<>();

        relatorio.getData().forEach(new BiConsumer<String, Float>() {
            @Override
            public void accept(String s, Float aFloat) {
                BarEntry e = new BarEntry(Float.parseFloat(s), aFloat);
                entries.add(e);
            }
        });
        BarDataSet dataSet = new BarDataSet(entries, relatorio.getNome());
        barData.addDataSet(dataSet);
        return barData;
    }
}
