package com.hakansahin.kredibasvurusuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hakansahin.kredibasvurusuapp.util.DataKeys;

import java.text.NumberFormat;
import java.util.Locale;

public class FourthPage extends AppCompatActivity {

    Intent intent;

    TextView krediTutar;
    TextView Vade;
    TextView ilkTaksitTarihi;
    TextView faizOrani;
    TextView taksitTutari;
    TextView toplamTutarText;

    String kredi;
    String vade;
    String date;
    String faiz;
    String aylikTutar;
    String toplamTutar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_page);
        getSupportActionBar().hide();

        intent = getIntent();

        kredi = intent.getStringExtra(DataKeys.KREDI_MIKTARI);
        vade = intent.getStringExtra(DataKeys.VADE);
        date = intent.getStringExtra(DataKeys.ILK_TAKSIT_TARIHI);
        faiz = intent.getStringExtra(DataKeys.FAIZ);
        aylikTutar = intent.getStringExtra(DataKeys.AYLIK_TAKSIT_TUTARI);
        toplamTutar = intent.getStringExtra(DataKeys.TOPLAM_TUTAR);

        // 4.Sayfadakilere atanıyor
        krediTutar = findViewById(R.id.KrediTutari);
        Vade = findViewById(R.id.Vade);
        ilkTaksitTarihi = findViewById(R.id.ilkTaksitTarihi);
        faizOrani = findViewById(R.id.faizOrani);
        taksitTutari = findViewById(R.id.taksitTutari);
        toplamTutarText = findViewById(R.id.toplamOdenecekTutar);

        Locale tr = new Locale("tr", "TR");
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(tr);
        String formatted = currencyInstance.format(Float.parseFloat(toplamTutar));
        toplamTutar = formatted.replaceAll("[^0123456789.,()-]","");
        toplamTutar = toplamTutar + " TL";

        krediTutar.setText("Kredi Miktarı: " + kredi);
        Vade.setText("Vade: "+ vade);
        ilkTaksitTarihi.setText("İlk Taksit Tarihi: " + date);
        faizOrani.setText("Faiz Oranı: " + faiz);
        taksitTutari.setText("Taksit Tutarı: " + aylikTutar);
        toplamTutarText.setText("Toplam Ödenecek Tutar: \n" + toplamTutar);




    }


    public void tamamla(View view){
        Intent intent = new Intent(FourthPage.this,final_page.class);
        intent.putExtras(this.intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finishAffinity();

        //test etmek için
        System.out.println(DataKeys.ISIM + " " + intent.getStringExtra(DataKeys.ISIM));
        System.out.println(DataKeys.TC_KIMLIK_NO + " " + intent.getStringExtra(DataKeys.TC_KIMLIK_NO));
        System.out.println(DataKeys.TELEFON_NO + " " + intent.getStringExtra(DataKeys.TELEFON_NO));

        System.out.println(DataKeys.EGITIM_DURUMU + " " + intent.getStringExtra(DataKeys.EGITIM_DURUMU));
        System.out.println(DataKeys.IS_DURUMU + " " + intent.getStringExtra(DataKeys.IS_DURUMU));
        System.out.println(DataKeys.MESLEK + " " + intent.getStringExtra(DataKeys.MESLEK));
        System.out.println(DataKeys.GELIR + " " + intent.getStringExtra(DataKeys.GELIR));
        System.out.println(DataKeys.KREDI_MIKTARI + " " + intent.getStringExtra(DataKeys.KREDI_MIKTARI));
        System.out.println(DataKeys.VADE + " " + intent.getStringExtra(DataKeys.VADE));
        System.out.println(DataKeys.ILK_TAKSIT_TARIHI + " " + intent.getStringExtra(DataKeys.ILK_TAKSIT_TARIHI));
        System.out.println(DataKeys.FAIZ + " " + intent.getStringExtra(DataKeys.FAIZ));
        System.out.println(DataKeys.AYLIK_TAKSIT_TUTARI + " " + intent.getStringExtra(DataKeys.AYLIK_TAKSIT_TUTARI));
    }
}