package com.hakansahin.kredibasvurusuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.hakansahin.kredibasvurusuapp.util.DataKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MeslekSecimEkrani extends AppCompatActivity {

    RecyclerView recyclerView;
    String isDurumu;
    Intent intent;

    RecyclerAdapter recyclerAdapter1;
    RecyclerAdapter recyclerAdapter2;
    RecyclerAdapter recyclerAdapter3;
    RecyclerAdapter recyclerAdapter4;
    RecyclerAdapter recyclerAdapter5;
    RecyclerAdapter recyclerAdapter6;
    RecyclerAdapter recyclerAdapter7;
    RecyclerAdapter recyclerAdapter8;
    RecyclerAdapter selectedRecyclerAdapter;


    List<String> meslekList1;
    List<String> meslekList2;
    List<String> meslekList3;
    List<String> meslekList4;
    List<String> meslekList5;
    List<String> meslekList6;
    List<String> meslekList7;
    List<String> meslekList8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meslek_secim_ekrani);
        getSupportActionBar().hide();

        intent = getIntent();
        isDurumu = intent.getStringExtra(DataKeys.IS_DURUMU);




        meslekList1 = new ArrayList<>();
        meslekList2 = new ArrayList<>();
        meslekList3 = new ArrayList<>();
        meslekList4 = new ArrayList<>();
        meslekList5 = new ArrayList<>();
        meslekList6 = new ArrayList<>();
        meslekList7 = new ArrayList<>();
        meslekList8 = new ArrayList<>();


        meslekList1.add("ÇİFTÇİ");
        meslekList1.add("DENETÇİ / MÜFETTİŞ");
        meslekList1.add("DÖVİZ BÜROSU SAHİBİ");
        meslekList1.add("EKONOMİST");
        meslekList1.add("EMLAK KOMİSYONCUSU");
        meslekList1.add("ESNAF / İŞ YERİ SAHİBİ");
        meslekList1.add("GALERİCİ");
        meslekList1.add("HALICI");
        meslekList1.add("HUKUK BÜROSU SAHİBİ");
        meslekList1.add("HUKUK ÇALIŞANI / AVUKAT");
        meslekList1.add("HUKUK MÜŞAVİRİ / DANIŞMANI");
        meslekList1.add("KUYUMCU / SARRAF");
        meslekList1.add("MALİ MÜŞAVİR");
        meslekList1.add("MEDYA ÇALIŞANI ");
        meslekList1.add("MEDYA KURULUŞU SAHİBİ, ORTAĞI, YÖNETİM KURULU ÜYESİ");
        meslekList1.add("MENAJER / YAPIMCI / YÖNETMEN / ORGANİZATÖR");
        meslekList1.add("MÜHENDİS / MİMAR / İÇ MİMAR / DEKORATÖR");
        meslekList1.add("NOTER");
        meslekList1.add("OTEL / PANSİYON / RESTORAN SAHİBİ - ORTAĞI");
        meslekList1.add("OTEL / PANSİYON YÖNETİCİSİ");
        meslekList1.add("PİLOT / KAPTAN");
        meslekList1.add("REKLAM VE HALKLA İLİŞKİLER");
        meslekList1.add("SAĞLIK - ECZACI");
        meslekList1.add("SAĞLIK - TIP DOKTORU / DİŞ HEKİMİ");
        meslekList1.add("SANATÇI / YAZAR / MÜZİSYEN / RESSAM / FOTOĞRAFÇI / TASARIMCI");
        meslekList1.add("SATIŞ / PAZARLAMA TEMSİLCİSİ");
        meslekList1.add("TEKNİK DİREKTÖR / ANTRENÖR / SPORCU / HAKEM");
        meslekList1.add("TEKNİK GÖREVLİ");
        meslekList1.add("TURİZM ACENTASI");

        meslekList2.add("ASKERİ PERSONEL");
        meslekList2.add("ASKERİ PERSONEL - ALBAY / YARBAY");
        meslekList2.add("ASKERİ PERSONEL - GENERAL / AMİRAL");
        meslekList2.add("BELEDİYE BAŞKANI / BELEDİYE BAŞKAN YARDIMCISI");
        meslekList2.add("DENETÇİ / MÜFETTİŞ");
        meslekList2.add("DİN İNSANI");
        meslekList2.add("DİPLOMAT / BÜROKRAT / KONSOLOS / BÜYÜKELÇİ");
        meslekList2.add("EĞİTİM GÖREVLİSİ");
        meslekList2.add("EKONOMİST");
        meslekList2.add("EMNİYET GÖREVLİSİ");
        meslekList2.add("FİNANS ÇALIŞANI / BANKACI");
        meslekList2.add("FİNANS ÇALIŞANI / DEALER / BROKER");
        meslekList2.add("HİZMET GÖREVLİSİ");
        meslekList2.add("HUKUK ÇALIŞANI - AVUKAT");
        meslekList2.add("HUKUK ÇALIŞANI - HAKİM / SAVCI");
        meslekList2.add("HUKUK MÜŞAVİRİ / DANIŞMANI");
        meslekList2.add("İSTATİSTİKÇİ, KİMYAGER, MATEMATİKÇİ, FİZİKÇ");
        meslekList2.add("İŞÇİ");
        meslekList2.add("MALİ MÜŞAVİR / MUHASEBECİ");
        meslekList2.add("MEDYA ÇALIŞANI ");
        meslekList2.add("MEDYA KURULUŞU SAHİBİ, ORTAĞI, YÖNETİM KURULU ÜYESİ");
        meslekList2.add("MEDYA (GENEL YAYIN YÖNETMENİ, İMTİYAZ SAHİBİ KİŞİ)");
        meslekList2.add("MEMUR");
        meslekList2.add("MENAJER / YAPIMCI / YÖNETMEN / ORGANİZATÖR");
        meslekList2.add("MİLLETVEKİLİ / BAKAN / SİYASİ PARTİ BAŞKANI / SİYASİ PARTİ BAŞKAN YARDIMCISI");
        meslekList2.add("MÜHENDİS / MİMAR / İÇ MİMAR / DEKORATÖR");
        meslekList2.add("OTEL / PANSİYON YÖNETİCİSİ");
        meslekList2.add("PİLOT / KAPTAN");
        meslekList2.add("REKLAM VE HALKLA İLİŞKİLER");
        meslekList2.add("SAĞLIK - ECZACI");
        meslekList2.add("SAĞLIK - TIP DOKTORU / DİŞ HEKİMİ");
        meslekList2.add("SAĞLIK GÖREVLİSİ");
        meslekList2.add("SANATÇI / YAZAR / MÜZİSYEN / RESSAM / FOTOĞRAFÇI / TASARIMCI");
        meslekList2.add("SATIŞ / PAZARLAMA TEMSİLCİSİ");
        meslekList2.add("ŞEF / UZMAN / YETKİLİ");
        meslekList2.add("TEKNİK DİREKTÖR / ANTRENÖR / SPORCU / HAKEM");
        meslekList2.add("TEKNİK GÖREVLİ");
        meslekList2.add("ÜST DÜZEY EMNİYET MENSUBU / BAŞKOMİSER / KOMİSER VB");
        meslekList2.add("VALİ / KAYMAKAM");


        meslekList3.add("DENETÇİ / MÜFETTİŞ");
        meslekList3.add("EĞİTİM GÖREVLİSİ");
        meslekList3.add("EKONOMİST ");
        meslekList3.add("EMLAK KOMİSYONCUSU");
        meslekList3.add("FİNANS ÇALIŞANI / BANKACI");
        meslekList3.add("FİNANS ÇALIŞANI / DEALER / BROKER");
        meslekList3.add("HUKUK BÜROSU SAHİBİ");
        meslekList3.add("HİZMET GÖREVLİSİ");
        meslekList3.add("HUKUK ÇALIŞANI - AVUKAT");
        meslekList3.add("HUKUK MÜŞAVİRİ / DANIŞMANI");
        meslekList3.add("İSTATİSTİKÇİ, KİMYAGER, MATEMATİKÇİ, FİZİKÇİ");
        meslekList3.add("İŞÇİ");
        meslekList3.add("MALİ MÜŞAVİR / MUHASEBECİ");
        meslekList3.add("MEDYA ÇALIŞANI");
        meslekList3.add("MEDYA KURULUŞU SAHİBİ, ORTAĞI, YÖNETİM KURULU ÜYESİ");
        meslekList3.add("MEDYA (GENEL YAYIN YÖNETMENİ, İMTİYAZ SAHİBİ KİŞİ)");
        meslekList3.add("MEMUR");
        meslekList3.add("MENAJER / YAPIMCI / YÖNETMEN / ORGANİZATÖR");
        meslekList3.add("MÜHENDİS / MİMAR / İÇ MİMAR / DEKORATÖR");
        meslekList3.add("OTEL / PANSİYON / RESTORAN SAHİBİ - ORTAĞI");
        meslekList3.add("OTEL / PANSİYON YÖNETİCİSİ");
        meslekList3.add("PİLOT / KAPTAN");
        meslekList3.add("REKLAM VE HALKLA İLİŞKİLER (REKLAMCI)");
        meslekList3.add("SAĞLIK - ECZACI ");
        meslekList3.add("SAĞLIK - TIP DOKTORU / DİŞ HEKİMİ");
        meslekList3.add("SAĞLIK GÖREVLİSİ");
        meslekList3.add("SANATÇI / YAZAR / MÜZİSYEN / RESSAM / FOTOĞRAFÇI / TASARIMCI");
        meslekList3.add("SATIŞ / PAZARLAMA TEMSİLCİSİ");
        meslekList3.add("ŞEF / UZMAN / YETKİLİ");
        meslekList3.add("TEKNİK DİREKTÖR / ANTRENÖR / SPORCU / HAKEM");
        meslekList3.add("TEKNİK GÖREVLİ");

        meslekList4.add("ASKERİ PERSONEL");
        meslekList4.add("ASKERİ PERSONEL - ALBAY / YARBAY");
        meslekList4.add("ASKERİ PERSONEL - GENERAL / AMİRAL");
        meslekList4.add("BELEDİYE BAŞKANI / BELEDİYE BAŞKAN YARDIMCISI");
        meslekList4.add("ÇİFTÇİ");
        meslekList4.add("DENETÇİ / MÜFETTİŞ");
        meslekList4.add("DİN İNSANI");
        meslekList4.add("DİPLOMAT / BÜROKRAT / KONSOLOS / BÜYÜKELÇİ");
        meslekList4.add("DÖVİZ BÜROSU SAHİBİ");
        meslekList4.add("EĞİTİM GÖREVLİSİ");
        meslekList4.add("EKONOMİST");
        meslekList4.add("EMLAK KOMİSYONCUSU");
        meslekList4.add("EMNİYET GÖREVLİSİ");
        meslekList4.add("ESNAF / İŞ YERİ SAHİBİ");
        meslekList4.add("FİNANS ÇALIŞANI / BANKACI");
        meslekList4.add("FİNANS ÇALIŞANI / DEALER / BROKER");
        meslekList4.add("GALERİCİ");
        meslekList4.add("HALICI");
        meslekList4.add("HİZMET GÖREVLİSİ");
        meslekList4.add("HUKUK BÜROSU SAHİBİ");
        meslekList4.add("HUKUK ÇALIŞANI - AVUKAT");
        meslekList4.add("HUKUK ÇALIŞANI - HAKİM / SAVCI");
        meslekList4.add("HUKUK MÜŞAVİRİ / DANIŞMANI");
        meslekList4.add("İSTATİSTİKÇİ, KİMYAGER, MATEMATİKÇİ, FİZİKÇİ ");
        meslekList4.add("İŞÇİ");
        meslekList4.add("KUYUMCU / SARRAF");
        meslekList4.add("MALİ MÜŞAVİR / MUHASEBECİ");
        meslekList4.add("MEDYA ÇALIŞANI");
        meslekList4.add("MEDYA KURULUŞU SAHİBİ, ORTAĞI, YÖNETİM KURULU ÜYESİ");
        meslekList4.add("MEDYA (GENEL YAYIN YÖNETMENİ, İMTİYAZ SAHİBİ KİŞİ) ");
        meslekList4.add("MEMUR");
        meslekList4.add("MENAJER / YAPIMCI / YÖNETMEN / ORGANİZATÖR");
        meslekList4.add("MİLLETVEKİLİ / BAKAN / SİYASİ PARTİ BAŞKANI / SİYASİ PARTİ BAŞKAN YARDIMCISI");
        meslekList4.add("MÜHENDİS / MİMAR / İÇ MİMAR / DEKORATÖR");
        meslekList4.add("NOTER");
        meslekList4.add("OTEL / PANSİYON / RESTORAN SAHİBİ - ORTAĞI");
        meslekList4.add("OTEL / PANSİYON YÖNETİCİSİ");
        meslekList4.add("PİLOT / KAPTAN");
        meslekList4.add("REKLAM VE HALKLA İLİŞKİLER (REKLAMCI) ");
        meslekList4.add("SAĞLIK - ECZACI");
        meslekList4.add("SAĞLIK - TIP DOKTORU / DİŞ HEKİMİ");
        meslekList4.add("SAĞLIK GÖREVLİSİ ");
        meslekList4.add("SANATÇI / YAZAR / MÜZİSYEN / RESSAM / FOTOĞRAFÇI / TASARIMCI ");
        meslekList4.add("SATIŞ / PAZARLAMA TEMSİLCİSİ");
        meslekList4.add("ŞEF / UZMAN / YETKİLİ");
        meslekList4.add("TEKNİK DİREKTÖR / ANTRENÖR / SPORCU / HAKEM");
        meslekList4.add("TEKNİK GÖREVLİ");
        meslekList4.add("TURİZM ACENTASI");
        meslekList4.add("ÜST DÜZEY EMNİYET MENSUBU / BAŞKOMİSER / KOMİSER VB. ");
        meslekList4.add("VALİ / KAYMAKAM");

        meslekList5.add("ÖĞRENCİ");

        meslekList6.add("EV HANIMI ");

        meslekList7.add("ÇALIŞMIYOR");

        meslekList8.add("EMEKLİ");

        // Büyük harfler küçük harfe çevriliyor.
        toLower(meslekList1);
        toLower(meslekList2);
        toLower(meslekList3);
        toLower(meslekList4);
        toLower(meslekList5);
        toLower(meslekList6);
        toLower(meslekList7);
        toLower(meslekList8);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter1 = new RecyclerAdapter(meslekList1, DataKeys.MESLEK, intent, this, SecondPage.class);
        recyclerAdapter2 = new RecyclerAdapter(meslekList2, DataKeys.MESLEK, intent, this, SecondPage.class);
        recyclerAdapter3 = new RecyclerAdapter(meslekList3, DataKeys.MESLEK, intent, this, SecondPage.class);
        recyclerAdapter4 = new RecyclerAdapter(meslekList4, DataKeys.MESLEK, intent, this, SecondPage.class);
        recyclerAdapter5 = new RecyclerAdapter(meslekList5, DataKeys.MESLEK, intent, this, SecondPage.class);
        recyclerAdapter6 = new RecyclerAdapter(meslekList6, DataKeys.MESLEK, intent, this, SecondPage.class);
        recyclerAdapter7 = new RecyclerAdapter(meslekList7, DataKeys.MESLEK, intent, this, SecondPage.class);
        recyclerAdapter8 = new RecyclerAdapter(meslekList8, DataKeys.MESLEK, intent, this, SecondPage.class);



        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        if(isDurumu.equals("Serbest Meslek Sahibi")){
            selectedRecyclerAdapter=recyclerAdapter1;
        }
        else if(isDurumu.equals("Ücretli - Kamu Sektörü")){
            selectedRecyclerAdapter=recyclerAdapter2;
        }
        else if(isDurumu.equals("Ücretli - Özel Sektör")){
            selectedRecyclerAdapter=recyclerAdapter3;
        }
        else if(isDurumu.equals("Emekli + Çalışıyor")){
            selectedRecyclerAdapter=recyclerAdapter4;
        }
        else if(isDurumu.equals("Öğrenci")){
            selectedRecyclerAdapter=recyclerAdapter5;
        }
        else if(isDurumu.equals("Ev Hanımı")){
            selectedRecyclerAdapter=recyclerAdapter6;
        }
        else if(isDurumu.equals("Çalışmıyor")){
            selectedRecyclerAdapter=recyclerAdapter7;

        }
        else if(isDurumu.toUpperCase(Locale.ROOT).equals("Emekli")){
            selectedRecyclerAdapter=recyclerAdapter8;


        }
        recyclerView.setAdapter(selectedRecyclerAdapter);




        // Arama Cubugu
        EditText editText = findViewById(R.id.searchTextMeslek);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<String> filteredList = new ArrayList<>();

        for (String item : selectedRecyclerAdapter.list) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        selectedRecyclerAdapter.filterList(filteredList);
    }


    public void previousPageButton(View view){
        Intent intent = new Intent(MeslekSecimEkrani.this, SecondPage.class);
        intent.putExtras(this.intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public List<String> toLower(List<String> list){
        for(int i = 0; i < list.size(); i++){
            list.set(i,capitalizeString(list.get(i)));
        }
        return list;
    }

    public static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean willUpper = true;
        for (int i = 0; i < chars.length; i++) {
            if (willUpper && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                willUpper = false;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') {
                willUpper = true;
            }
        }
        return String.valueOf(chars);
    }

}