package com.cbagames.mobiroller_proje_odev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetayActivity extends AppCompatActivity {
    TextView textViewAd,textViewKategori,textViewAciklama,textViewTarih,textViewFiyat;
    Button buttonTamam;
    ImageView imageViewUrunResim;
    Toolbar toolbar;
    Urunler urun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        textViewAd = findViewById(R.id.textViewAd);
        textViewKategori = findViewById(R.id.textViewKategori);
        textViewAciklama = findViewById(R.id.textViewAciklama);
        textViewTarih = findViewById(R.id.textViewTarih);
        textViewFiyat = findViewById(R.id.textViewFiyat);
        buttonTamam = findViewById(R.id.buttonTamam);
        imageViewUrunResim = findViewById(R.id.imageViewUrunResim);

        toolbar = findViewById(R.id.toolbar4);
        toolbar.setTitle("Proje Ödevi");
        toolbar.setSubtitle("Ürün Detayı");
        toolbar.setLogo(R.drawable.mobirollericon);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        setSupportActionBar(toolbar);

        urun = (Urunler) getIntent().getSerializableExtra("nesne");

        textViewAd.setText(urun.urun_ad);
        textViewKategori.setText(urun.urun_kategori);
        textViewAciklama.setText(urun.urun_aciklama);
        textViewTarih.setText(urun.urun_tarih);
        textViewFiyat.setText(String.valueOf(urun.urun_fiyat));

        String url = "https://callbellapp.xyz/project/otherProjects/"+urun.urun_resim;
        Picasso.get().load(url).resize(250,150).placeholder(R.drawable.macbook).into(imageViewUrunResim);

        buttonTamam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });



    }
}