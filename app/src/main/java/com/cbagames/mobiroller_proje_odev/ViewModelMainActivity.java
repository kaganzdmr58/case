package com.cbagames.mobiroller_proje_odev;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ViewModelMainActivity extends ViewModel {

    private MutableLiveData<ArrayList<Urunler>> urunlerArrayListesi ;

    public void init (Context context){

        if (urunlerArrayListesi != null){
            return;
        }

        urunlerArrayListesi = RepositoryMainActivity.getInstance(context).getUrunler();
    }

    public MutableLiveData<ArrayList<Urunler>> getUrunlerArrayListesi() {
        return urunlerArrayListesi;
    }


}
