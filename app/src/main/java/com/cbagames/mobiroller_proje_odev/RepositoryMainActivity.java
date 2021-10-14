package com.cbagames.mobiroller_proje_odev;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RepositoryMainActivity {
    static RepositoryMainActivity instance;
    private ArrayList<Urunler> urunlerArrayListesi = new ArrayList<>();

    static Context mContext;
    static LoadDataListener loadDataListener;

    public static RepositoryMainActivity getInstance(Context context){
        mContext = context;
        if (instance == null){
            instance = new RepositoryMainActivity();
        }
        loadDataListener = (LoadDataListener)mContext;
        return instance;
    }

    public MutableLiveData<ArrayList<Urunler>> getUrunler (){

        urunleriYukle();

        MutableLiveData<ArrayList<Urunler>> urunler = new MutableLiveData<>();
        urunler.setValue(urunlerArrayListesi);

        return urunler;
    }

    private void urunleriYukle(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Mobiroller");

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

                    urunlerArrayListesi.add(u);
                }
                loadDataListener.onProductLoaded();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Hata",error.toException().toString());
            }


        });

    }

}
