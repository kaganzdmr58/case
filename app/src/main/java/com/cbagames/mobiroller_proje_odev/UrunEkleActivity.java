package com.cbagames.mobiroller_proje_odev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UrunEkleActivity extends AppCompatActivity {
    EditText editTextAd,editTextKategori,editTextAciklama,editTextTarih,editTextFiyat;
    Button buttonKaydet;
    ImageView imageViewUrunResim;
    Toolbar toolbar;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String ad = " ",kategori = " ",aciklama = " ", tarih = " ",fiyat = "0.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ekle);

        editTextAd = findViewById(R.id.editTextAd);
        editTextKategori = findViewById(R.id.editTextKategori);
        editTextAciklama = findViewById(R.id.editTextAciklama);
        editTextTarih = findViewById(R.id.editTextTarih);
        editTextFiyat = findViewById(R.id.editTextFiyat);
        buttonKaydet = findViewById(R.id.buttonTamam);
        imageViewUrunResim = findViewById(R.id.imageViewUrunResim);

        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Proje Ödevi");
        toolbar.setSubtitle("Ürün Ekle");
        toolbar.setLogo(R.drawable.mobirollericon);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Mobiroller");



        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // firebase ile kayıt yapılacak işlem bitiminde bilgi verilecek
                ad =editTextAd.getText().toString();
                kategori = editTextKategori.getText().toString();
                aciklama = editTextAciklama.getText().toString();
                tarih = editTextTarih.getText().toString();
                fiyat = editTextFiyat.getText().toString();
                if (fiyat.equals("")){
                    fiyat = "0.0";
                }

                String key = myRef.push().getKey();
                Urunler urun = new Urunler(key,ad,kategori,aciklama,"macbook.png",tarih,Double.valueOf(fiyat));
                myRef.child(key).setValue(urun);

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });



    }
}