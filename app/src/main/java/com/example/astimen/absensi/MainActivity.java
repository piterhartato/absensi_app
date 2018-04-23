package com.example.astimen.absensi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import com.example.astimen.absensi.fragment.frg_abs_masuk;
import com.example.astimen.absensi.fragment.frg_abs_pulang;
import com.example.astimen.absensi.fragment.frg_berita;
import com.example.astimen.absensi.fragment.frg_izin;
import com.example.astimen.absensi.fragment.frg_report;
import com.example.astimen.absensi.fragment.frg_home;
import com.example.astimen.absensi.fragment.frg_setting;
import com.example.astimen.absensi.fragment.frg_input_karyawan;
import com.example.astimen.absensi.fragment.frg_profile;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    String uid, username, nama;
    private TextView emailView, namaView;
    private abs_masuk_Activity frgAbsMasuk;
    private frg_abs_pulang frgAbsPulang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        uid = getIntent().getStringExtra("uid");
        username = getIntent().getStringExtra("username");
        nama = getIntent().getStringExtra("nama");
        Bundle bundle = new Bundle();
        bundle.putString("uidvalue",uid );
        frg_home fragHome = new frg_home();
        fragHome.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, fragHome);
        ft.commit();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);
        namaView = (TextView)header.findViewById(R.id.namaView);
        namaView.setText(nama);
        emailView = (TextView)header.findViewById(R.id.emailView);
        emailView.setText(username);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            //return true;

            pesan_toast("Fragment frg_settings ditampilkan ...");

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, new frg_setting());
            ft.commit();

            getSupportActionBar().setTitle("Settings");
        }
        if (id == R.id.action_exit) {
            //return true;
            pesan_toast("Exit ditampilkan ...");
            keluar();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_report) {
            getSupportActionBar().setTitle("Report");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, frg_report.newInstance("Parameter1","Parameter2"))
                    .commit();
        } else if (id == R.id.nav_profile) {
            getSupportActionBar().setTitle("Profile");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, frg_profile.newInstance("Parameter1","Parameter2"))
                    .commit();
        } else if (id == R.id.nav_slideshow) {
            getSupportActionBar().setTitle("Home");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, new frg_home())
                    .commit();
        } else if (id == R.id.nav_home) {
            getSupportActionBar().setTitle("Settings");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, new frg_setting())
                    .commit();
        } else if(id == R.id.nav_abs_masuk){
            frgAbsMasuk = new abs_masuk_Activity();
           // frgAbsMasuk.setUid(uid);
           // frgAbsMasuk.setUsername(username);
            getSupportActionBar().setTitle("Absen Masuk");
            //getSupportFragmentManager()
                  //  .beginTransaction()
                 //   .replace(R.id.content_main, frgAbsMasuk)
                 //   .commit();
            Intent i = new Intent(MainActivity.this,abs_masuk_Activity.class);
            i.putExtra("uidvalue",uid);
            startActivity(i);
        } else if (id == R.id.nav_berita) {
            getSupportActionBar().setTitle("Berita");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, new frg_berita())
                    .commit();
        } else if (id == R.id.nav_izin) {
            getSupportActionBar().setTitle("Izin");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, new frg_izin())
                    .commit();
        } else if (id == R.id.nav_abs_pulang) {
            frgAbsPulang = new frg_abs_pulang();
            frgAbsPulang.setUid(uid);
            frgAbsPulang.setUsername(username);
            getSupportActionBar().setTitle("Absen Pulang");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, frgAbsPulang)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void keluar () {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah anda akan keluar aplikasi ?").setPositiveButton("Ya",dialogClickListener)
                .setNegativeButton("Tidak",dialogClickListener).show();
    }

    public void pesan_toast ( final String pesan){
        Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_LONG).show();
    }
}
