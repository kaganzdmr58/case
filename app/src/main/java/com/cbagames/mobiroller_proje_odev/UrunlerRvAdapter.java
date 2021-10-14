package com.cbagames.mobiroller_proje_odev;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UrunlerRvAdapter extends RecyclerView.Adapter<UrunlerRvAdapter.CardViewTasarimNesneleriniTutucu>{
    private Context mContext;
    private List<Urunler> disaridanGelenUrunler;

    public UrunlerRvAdapter(Context mContext, List<Urunler> disaridanGelenUrunler) {
        this.mContext = mContext;
        this.disaridanGelenUrunler = disaridanGelenUrunler;
    }

    public class CardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder{
        public TextView textViewUrunAd;
        public ImageView imageViewEdit;
        public CardView cardiViewUrunler;

        public CardViewTasarimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            this.textViewUrunAd = itemView.findViewById(R.id.textViewUrunAd);
            this.imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
            this.cardiViewUrunler = itemView.findViewById(R.id.cardiViewUrunler);
        }
    }


    @NonNull
    @Override
    public CardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tasarim_urunler,parent,false);
        return new CardViewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimNesneleriniTutucu holder, int position) {
        final Urunler urun = disaridanGelenUrunler.get(position);
        holder.textViewUrunAd.setText(urun.urun_ad);
        holder.cardiViewUrunler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // urun nesnesi ile detay sayfasına geçiş.
                Intent intent = new Intent(mContext,DetayActivity.class);
                intent.putExtra("nesne",urun);
                mContext.startActivity(intent);
            }
        });



        holder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Urun nesnesi ile beraber Düzenleme sayfasına geçiş
                Intent intent = new Intent(mContext,DuzenleActivity.class);
                intent.putExtra("nesne",urun);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return disaridanGelenUrunler.size();
    }


    public void veriSil(Urunler urun){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Mobiroller");

        myRef.child(urun.urun_key).removeValue();


    }
}
