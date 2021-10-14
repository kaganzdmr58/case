package com.cbagames.mobiroller_proje_odev;

import java.io.Serializable;

public class Urunler implements Serializable,Comparable<Urunler> {
    String urun_key;
    String urun_ad;
    String urun_kategori;
    String urun_aciklama;
    String urun_resim;
    String urun_tarih;
    Double urun_fiyat;

    public Urunler() {
    }

    public Urunler(String urun_key, String urun_ad, String urun_kategori, String urun_aciklama, String urun_resim, String urun_tarih, Double urun_fiyat) {
        this.urun_key = urun_key;
        this.urun_ad = urun_ad;
        this.urun_kategori = urun_kategori;
        this.urun_aciklama = urun_aciklama;
        this.urun_resim = urun_resim;
        this.urun_tarih = urun_tarih;
        this.urun_fiyat = urun_fiyat;
    }

    public String getUrun_key() {
        return urun_key;
    }

    public void setUrun_key(String urun_key) {
        this.urun_key = urun_key;
    }

    public String getUrun_ad() {
        return urun_ad;
    }

    public void setUrun_ad(String urun_ad) {
        this.urun_ad = urun_ad;
    }

    public String getUrun_kategori() {
        return urun_kategori;
    }

    public void setUrun_kategori(String urun_kategori) {
        this.urun_kategori = urun_kategori;
    }

    public String getUrun_aciklama() {
        return urun_aciklama;
    }

    public void setUrun_aciklama(String urun_aciklama) {
        this.urun_aciklama = urun_aciklama;
    }

    public String getUrun_resim() {
        return urun_resim;
    }

    public void setUrun_resim(String urun_resim) {
        this.urun_resim = urun_resim;
    }

    public String getUrun_tarih() {
        return urun_tarih;
    }

    public void setUrun_tarih(String urun_tarih) {
        this.urun_tarih = urun_tarih;
    }

    public Double getUrun_fiyat() {
        return urun_fiyat;
    }

    public void setUrun_fiyat(Double urun_fiyat) {
        this.urun_fiyat = urun_fiyat;
    }

    @Override
    public int compareTo(Urunler o) {
        return new String (this.urun_ad).compareTo(o.urun_ad);
    }


}
