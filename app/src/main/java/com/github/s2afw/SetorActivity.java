package com.github.s2afw;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.github.s2afw.components.S2Button;
import com.github.s2afw.components.S2ButtonClickListener;
import com.github.s2afw.model.Relatorio;
import com.github.s2afw.model.service.RelatorioDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class SetorActivity extends AppCompatActivity implements Callback<List<String>> {
    private String nmSetor;
    private LinearLayout rootSetor;
    private LinearLayout.LayoutParams params;
    private RelatorioDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.nmSetor = (String) getIntent().getExtras().get("setor");
        rootSetor = (LinearLayout) findViewById(R.id.rootSetor);
        params = new LinearLayout.LayoutParams(-1, -2);
        params.setMargins(5, 10, 5, 10);
        setTitle(this.nmSetor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.200:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dao = retrofit.create(RelatorioDao.class);
        dao.getRelatorios(nmSetor).enqueue(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
        List<String> relatorios = response.body();
        relatorios.forEach(r -> {
            rootSetor.addView(new S2Button(this, r, new S2ButtonClickListener() {
                @Override
                public void onClick(View v) {
                    S2Button btn = (S2Button) v;
                    try {
                        dao.getRelatorio(btn.getText().toString()).enqueue(new Callback<Relatorio>() {
                            @Override
                            public void onResponse(Call<Relatorio> call, Response<Relatorio> response) {
                                Relatorio r = response.body();
                                Intent i = new Intent(SetorActivity.this, ChartActivity.class);
                                i.putExtra("relatorio", r);
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(Call<Relatorio> call, Throwable t) {
                                AlertDialog.Builder d = new AlertDialog.Builder(SetorActivity.this);
                                d.setTitle("Error!!!");
                                d.setMessage(t.getMessage());
                                d.show();
                            }
                        });
                    } catch (Exception e) {
                        AlertDialog.Builder d = new AlertDialog.Builder(SetorActivity.this);
                        d.setTitle("Error!!!");
                        d.setMessage(e.getMessage());
                        d.show();
                    }

                }
            }), params);
        });
    }

    @Override
    public void onFailure(Call<List<String>> call, Throwable t) {
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setTitle("Error!!!");
        d.setMessage(t.getMessage());
        d.show();
    }
}