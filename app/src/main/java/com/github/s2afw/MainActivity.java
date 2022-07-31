package com.github.s2afw;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.github.s2afw.components.S2Button;
import com.github.s2afw.components.S2ButtonClickListener;
import com.github.s2afw.model.service.RelatorioDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinearLayout contentMainRoot;
    private RelatorioDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contentMainRoot = findViewById(R.id.contentMainRoot);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.setMargins(5, 10, 5, 10);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://node109038-afw-api.jelastic.saveincloud.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dao = retrofit.create(RelatorioDao.class);
        try {
            dao.getSetores().enqueue(new Callback<List<String>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    List<String> setores = response.body();
                    Collections.sort(setores);
                    setores.forEach(s -> {
                        S2Button btn = new S2Button(MainActivity.this, s, new S2ButtonClickListener() {
                            @Override
                            public void onClick(View v) {
                                S2Button btn = (S2Button) v;
                                onBtnSetorClicked(btn.getText().toString());
                            }
                        });


                        contentMainRoot.addView(btn, params);
                    });
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    doOnError(t.getMessage());
                }
            });
        } catch (Exception e) {
            doOnError(e.getMessage());
        }
    }

    private void doOnError(String error) {
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setTitle("Error!!").setMessage(error).show();
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

    public void btnOnClick(View v) {
        Button btn = (Button) v;
        onBtnSetorClicked(btn.getText().toString());
    }

    private void onBtnSetorClicked(String text) {
        Intent i = new Intent(this, SetorActivity.class);
        i.putExtra("setor", text);
        startActivity(i);
    }
}