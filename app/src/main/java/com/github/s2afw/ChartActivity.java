package com.github.s2afw;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.s2afw.components.S2Dialog;
import com.github.s2afw.model.Relatorio;
import com.github.s2afw.util.RelatorioToBarData;

public class ChartActivity extends AppCompatActivity {
    private Relatorio relatorio;
    private BarChart chart;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        relatorio = (Relatorio) getIntent().getExtras().get("relatorio");
        setTitle(relatorio.getNome());
        chart = (BarChart) findViewById(R.id.chart);
        try {
            chart.setData(new RelatorioToBarData(relatorio).getBarData());
        } catch (Exception e) {
            S2Dialog d = new S2Dialog(this);
            d.setTitle("Error!");
            d.setMessage(e.getMessage());
            d.show();
        }
        chart.getDescription().setEnabled(false);
        chart.getRendererRightYAxis().getPaintAxisLabels().setColor(Color.WHITE);
        chart.setBackgroundColor(getResources().getColor(R.color.bg_chart_color));
        XAxis xAxis = chart.getXAxis();

        //xAxis.setTextColor(Color.WHITE);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int v = (int) value;
                return String.valueOf(v);
            }
        });
    }
}