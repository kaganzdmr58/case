package com.cbagames.mobiroller_proje_odev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements LoadDataListener{
    Toolbar toolbar;
    RecyclerView rv;
    FloatingActionButton fabAdd ;
    UrunlerRvAdapter adapter;
    ArrayList<Urunler> urunlerArrayList = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference myRef;
    ViewModelMainActivity viewModelMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAdd = findViewById(R.id.fabAdd);
        rv = findViewById(R.id.rv);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Proje Ödevi");
        toolbar.setSubtitle("Ürünler");
        toolbar.setLogo(R.drawable.mobirollericon);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Mobiroller");

        urunlerArrayList = new ArrayList<>();


        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        //veriYukleme();
        viewModelMainActivity = ViewModelProviders.of(MainActivity.this).get(ViewModelMainActivity.class);
        viewModelMainActivity.init(MainActivity.this);

        urunlerArrayList = viewModelMainActivity.getUrunlerArrayListesi().getValue();

        adapter = new UrunlerRvAdapter(MainActivity.this,urunlerArrayList);
        rv.setAdapter(adapter);


        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UrunEkleActivity.class));
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_siralama_menusu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_isim_z_a:
                Collections.sort(urunlerArrayList);
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void veriYukleme(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()){

                    Urunler urun = postSnapshot.getValue(Urunler.class);
                    Urunler u = new Urunler(
                             urun.urun_key
                            ,urun.urun_ad
                            ,urun.urun_kategori
                            ,urun.urun_aciklama
                            ,urun.urun_resim
                            ,urun.urun_tarih
                            ,Double.valueOf(urun.urun_fiyat) );

                    urunlerArrayList.add(u);
                }
                adapter = new UrunlerRvAdapter(MainActivity.this,urunlerArrayList);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Hata",error.toException().toString());
            }


        });



    }


    @Override
    public void onProductLoaded() {
        viewModelMainActivity.getUrunlerArrayListesi().observe(this, new Observer<ArrayList<Urunler>>() {
            @Override
            public void onChanged(ArrayList<Urunler> urunlers) {
                adapter.notifyDataSetChanged();
            }
        });
    }
}