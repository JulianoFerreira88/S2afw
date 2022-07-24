package com.github.s2afw;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.github.s2afw.model.Relatorio;
import com.github.s2afw.model.service.RelatorioDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Callback<Relatorio> {
    private LinearLayout contentMainRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.setMargins(5, 10, 5, 10);*/
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.200:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RelatorioDao dao = retrofit.create(RelatorioDao.class);*/
        /*dao.getRelatorios().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> relatorios = response.body();
                for (int i = 0; i < relatorios.size(); i++) {
                    *//*
                    contentMainRoot.addView(new S2Button(MainActivity.this, relatorios.get(i), new S2ButtonClickListener() {
                        @Override
                        public void onClick(View v) {
                            S2Button btn = (S2Button) v;
                            //Toast.makeText(MainActivity.this, btn.getText(), Toast.LENGTH_SHORT).show();
                            dao.getRelatorio(btn.getText().toString()).enqueue(MainActivity.this);
                        }
                    }), params);

                     *//*
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
                d.setTitle("Error!!").setMessage(t.getMessage()).show();
            }
        });*/


        contentMainRoot = findViewById(R.id.contentMainRoot);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(Call<Relatorio> call, Response<Relatorio> response) {
        Relatorio r = response.body();
        onRelatorio(r);
    }

    private void onRelatorio(Relatorio r) {
        Intent i = new Intent(MainActivity.this, ChartActivity.class);
        i.putExtra("relatorio", r);
        startActivity(i);
    }

    @Override
    public void onFailure(Call<Relatorio> call, Throwable t) {
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setTitle("Error!!").setMessage(t.getMessage()).show();
    }

    public void btnOnClick(View v) {
        Button btn = (Button) v;
        onBtnSetorClicked(btn.getText().toString());


    }

    private void onBtnSetorClicked(String text) {
        Intent i = new Intent(this,SetorActivity.class);
        i.putExtra("setor",text);
        startActivity(i);
    }
}