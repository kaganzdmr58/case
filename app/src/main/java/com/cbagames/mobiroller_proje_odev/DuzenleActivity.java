package com.cbagames.mobiroller_proje_odev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DuzenleActivity extends AppCompatActivity {
    EditText editTextAd,editTextKategori,editTextAciklama,editTextTarih,editTextFiyat;
    Button buttonKaydet;
    ImageView imageViewUrunResim;
    Toolbar toolbar;
    Urunler urun;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duzenle);

        editTextAd = findViewById(R.id.editTextAd);
        editTextKategori = findViewById(R.id.editTextKategori);
        editTextAciklama = findViewById(R.id.editTextAciklama);
        editTextTarih = findViewById(R.id.editTextTarih);
        editTextFiyat = findViewById(R.id.editTextFiyat);
        buttonKaydet = findViewById(R.id.buttonTamam);
        imageViewUrunResim = findViewById(R.id.imageViewUrunResim);

        toolbar = findViewById(R.id.toolbar3);
        toolbar.setTitle("Proje Ödevi");
        toolbar.setSubtitle("Ürün Düzenle");
        toolbar.setLogo(R.drawable.mobirollericon);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Mobiroller");

        urun = (Urunler) getIntent().getSerializableExtra("nesne");

        editTextAd.setText(urun.urun_ad);
        editTextKategori.setText(urun.urun_kategori);
        editTextAciklama.setText(urun.urun_aciklama);
        editTextTarih.setText(urun.urun_tarih);
        editTextFiyat.setText(String.valueOf(urun.urun_fiyat));

        String url = "https://callbellapp.xyz/project/otherProjects/"+urun.urun_resim;
        Picasso.get().load(url).resize(250,150).placeholder(R.drawable.macbook).into(imageViewUrunResim);


        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // firebase ile kayıt güncelleme yapılacak işlem bitiminde ana sayfaya dönülecek.
                Map<String, Object> updateInfo = new HashMap<>();
                updateInfo.put("urun_ad",editTextAd.getText().toString());
                updateInfo.put("urun_kategori",editTextKategori.getText().toString());
                updateInfo.put("urun_aciklama",editTextAciklama.getText().toString());
                updateInfo.put("urun_tarih",editTextTarih.getText().toString());
                updateInfo.put("urun_fiyat",Double.valueOf(editTextFiyat.getText().toString()));

                myRef.child(urun.urun_key).updateChildren(updateInfo);

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.duzenle_silme_menusu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_delete:
                myRef.child(urun.urun_key).removeValue();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}