package com.hakansahin.kredibasvurusuapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

import com.hakansahin.kredibasvurusuapp.formatter.CurrencyFormatter;
import com.hakansahin.kredibasvurusuapp.formatter.Formatter;
import com.hakansahin.kredibasvurusuapp.util.DataKeys;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ThirdPage extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Intent intent;
    Calendar def;


    private final int MAX_KREDI_MIKTARI = 25000;

    EditText krediMiktariText;
    TextView vadeTextView;
    TextView textViewFaizOranı;
    TextView dateText;

    String kredi;
    String vade;
    String date;
    String faiz;
    int gün;
    Date tarih;
    String aylikTutar;

    Handler taksitHandler = new Handler();
    Runnable taksitHesaplayıcı;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);
        /*
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        */

        krediMiktariText = findViewById(R.id.editTextKredi);
        vadeTextView = findViewById(R.id.buttonVade);
        textViewFaizOranı = findViewById(R.id.textViewFaizOranı);

        getSupportActionBar().hide();

        intent = getIntent();

        dateText = findViewById(R.id.editTextTaksitTarihi);
        dateText.addTextChangedListener(new Formatter(dateText, intent, DataKeys.ILK_TAKSIT_TARIHI));

        tarih = new Date();

        def = Calendar.getInstance();
        def.add(Calendar.MONTH, 1);
        int m = def.get(Calendar.MONTH) + 1;
        tarih.setTime(def.getTimeInMillis());
        dateText.setText(def.get(Calendar.DAY_OF_MONTH) + "/"+ (def.get(Calendar.MONTH) + 1) + "/" + def.get(Calendar.YEAR));


        krediMiktariText.addTextChangedListener(new CurrencyFormatter(krediMiktariText, intent, DataKeys.KREDI_MIKTARI));

        String k = intent.getStringExtra(DataKeys.KREDI_MIKTARI);
        if(k != null){
            krediMiktariText.setText(k);
        }

        String v = intent.getStringExtra(DataKeys.VADE);
        if(v != null){
            vadeTextView.setText(v);
        }

        Calendar def = Calendar.getInstance();
        def.add(Calendar.MONTH, 1);

        String d = intent.getStringExtra(DataKeys.ILK_TAKSIT_TARIHI);
        if(d != null){
            dateText.setText(d);
        }


        taksitHesaplayıcı = new Runnable() {
            @Override
            public void run() {
                if(!dateText.getText().toString().isEmpty()
                        && !vadeTextView.getText().toString().isEmpty()
                        && !krediMiktariText.getText().toString().isEmpty()){

                    hesapla();
                    ((TextView)findViewById(R.id.textViewTaksitTutarı)).setText(aylikTutar+"");
                }
                taksitHandler.postDelayed(taksitHesaplayıcı, 500);
            }
        };

        startTaksitHandler();
    }


    void startTaksitHandler() {
        taksitHandler.postDelayed(taksitHesaplayıcı, 0);
    }

    void stopTaksitHandler() {
        taksitHandler.removeCallbacks(taksitHesaplayıcı);
    }


    public void hesapla(){
        kredi=krediMiktariText.getText().toString();
        vade=vadeTextView.getText().toString();
        date=dateText.getText().toString();
        faiz=textViewFaizOranı.getText().toString();

        // kredi tutarının sonundaki Tl ve son 2 hane işleme hazır hale gelmesi için siliniyor.
        String sb = kredi.replaceAll("[₺,. TL]", "");
        float f = Float.parseFloat(sb)/100;

        //Vade sonundaki ay stringi ve boşluk siliniyor,.
        String sb2= vade.replaceAll("[ Ay]", "");

        float hesap = krediHesabi(f, Integer.parseInt(sb2));

        Locale tr = new Locale("tr", "TR");
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(tr);
        String formatted = currencyInstance.format(hesap);
        aylikTutar = formatted.replaceAll("[^0123456789.,()-]","");
        aylikTutar = aylikTutar + " TL";
        System.out.println(aylikTutar);
    }


    public void nextPageButton(View v) {
        if (krediMiktariText.getText().toString().equals("") | vadeTextView.getText().toString().equals("")) {
            showErrorMessage("Lütfen boş alanları doldurunuz!");
        }
        else if (Float.parseFloat(krediMiktariText.getText().toString().replaceAll("[₺,. TL]", ""))/100 > MAX_KREDI_MIKTARI) {
            showErrorMessage("En fazla " + MAX_KREDI_MIKTARI + " TL kredi çekebilirsiniz.");
        }
        else {
            hesapla();
            stopTaksitHandler();
            Intent intent = new Intent(ThirdPage.this, FourthPage.class);
            intent.putExtras(this.intent);
            intent.putExtra(DataKeys.FAIZ, faiz);
            intent.putExtra(DataKeys.AYLIK_TAKSIT_TUTARI,String.valueOf(aylikTutar));
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    public void previousPageButton(View v){
        Intent intent = new Intent(ThirdPage.this, SecondPage.class);
        intent.putExtras(this.intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void expiryButton(View v){
        stopTaksitHandler();
        Intent intent = new Intent(ThirdPage.this, VadeSecmeEkrani.class);
        intent.putExtras(this.intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void dateButton(View v){
        showDatePickerDialog();
    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                def.get(Calendar.YEAR),
                def.get(Calendar.MONTH),
                def.get(Calendar.DAY_OF_MONTH));


        Calendar min = Calendar.getInstance();
        min.add(Calendar.DAY_OF_MONTH, 1);
        long minDate = min.getTimeInMillis();

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 2);
        max.add(Calendar.DAY_OF_MONTH, -1);

        long maxDate = max.getTimeInMillis();

        datePickerDialog.getDatePicker().setMinDate(minDate);
        datePickerDialog.getDatePicker().setMaxDate(maxDate);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        int m = month + 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);

        tarih.setTime(cal.getTimeInMillis());
        dateText.setText(day + "/" + m + "/" + year);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // gün değişkeni o günden kredinin ödemesinin yapılacağı zamana kaç gün olduğunu ifade ediyor
    public float krediHesabi(float tutar,int vade){
        Date bugun = new Date(Calendar.getInstance().getTimeInMillis());

        long diff = tarih.getTime() - bugun.getTime();
        TimeUnit time = TimeUnit.DAYS;
        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
        gün = (int) (diffrence);

        float aylik=0;
        int i;
        float temp=0;
        for (i=1;i<=vade;i++){

            temp+= (float) (1/(Math.pow((1+0.0136+0.000015+0.000005),(i*(30/30)))));

        }
        aylik=tutar/temp;

        intent.putExtra(DataKeys.TOPLAM_TUTAR, String.valueOf(aylik*vade));

        return aylik;
    }

    private void showErrorMessage(String errorMessage) {
        Dialog dialog;
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_text_input));
        dialog.setCancelable(false);
        TextView textView = dialog.findViewById(R.id.HataAciklamaText);
        textView.setText(errorMessage);
        Button tamam =dialog.findViewById(R.id.tamamButton);
        tamam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}