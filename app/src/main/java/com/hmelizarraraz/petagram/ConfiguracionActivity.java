package com.hmelizarraraz.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hmelizarraraz.petagram.presentador.ConfiguracionActivityPresenter;
import com.hmelizarraraz.petagram.presentador.IConfiguracionActivityPresenter;

public class ConfiguracionActivity extends AppCompatActivity implements IConfiguracionActivityView{
    private Toolbar toolbar;
    private EditText etConfig;
    private Button btnSaveConfig;
    private IConfiguracionActivityPresenter presenter;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        toolbar = (Toolbar) findViewById(R.id.miToolbarConfig);
        presenter = new ConfiguracionActivityPresenter(this, getApplicationContext());

        btnSaveConfig = (Button) findViewById(R.id.btnSaveConfig);
        btnSaveConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etConfig = (EditText) findViewById(R.id.etConfig);
                presenter.guardarPreferencia(etConfig.getText().toString());
                finish();
            }
        });

    }

    @Override
    public void generarToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
