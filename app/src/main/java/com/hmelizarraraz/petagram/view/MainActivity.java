package com.hmelizarraraz.petagram.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.adapter.PageAdapter;
import com.hmelizarraraz.petagram.fragment.ListaMascotasFragment;
import com.hmelizarraraz.petagram.fragment.PerfilFragment;
import com.hmelizarraraz.petagram.presentador.IMainActivityPresenter;
import com.hmelizarraraz.petagram.presentador.MainActivityPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private IMainActivityPresenter presenter;
    private View vSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabsLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        vSnack = findViewById(android.R.id.content);

        setUpViewPager();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        if (getIntent().getStringExtra("notification") != null) {
            viewPager.setCurrentItem(1);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();

        switch (item.getItemId()){

            case R.id.mFav:

                intent = new Intent(MainActivity.this, MascotasFavoritas.class);
                startActivity(intent);
                break;

            case R.id.mContacto:

                intent = new Intent(MainActivity.this, ContactoActivity.class);
                startActivity(intent);
                break;

            case R.id.mAbout:

                intent = new Intent(MainActivity.this, AcercaDeActivity.class);
                startActivity(intent);
                break;
            
            case R.id.mConfig:

                intent = new Intent(MainActivity.this, ConfiguracionActivity.class);
                startActivity(intent);
                //finish();
                break;

            case R.id.mNotif:
                //Toast.makeText(this, "Traer id token", Toast.LENGTH_SHORT).show();
                presenter = new MainActivityPresenter(this, getApplicationContext(), vSnack);
                break;

            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragment()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_tab);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_profile_tab);

    }

    private ArrayList<Fragment> agregarFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new ListaMascotasFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    @Override
    public String obtenerToken() {
        String token = FirebaseInstanceId.getInstance().getToken();
        return token;
    }

}
