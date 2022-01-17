package com.hakansahin.kredibasvurusuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hakansahin.kredibasvurusuapp.formatter.Formatter;
import com.hakansahin.kredibasvurusuapp.formatter.PhoneFormatter;
import com.hakansahin.kredibasvurusuapp.util.DataKeys;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    EditText name;
    EditText tc;
    EditText phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();


        /*
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        */

        getSupportActionBar().hide();

        name = ((EditText)findViewById(R.id.editTextAdsoyad));
        name.addTextChangedListener(new Formatter(name, intent, DataKeys.ISIM));

        tc = ((EditText)findViewById(R.id.editTextTckn));
        tc.addTextChangedListener(new Formatter(tc, intent, DataKeys.TC_KIMLIK_NO));

        phone = ((EditText)findViewById(R.id.editTextTextTelno));
        phone.addTextChangedListener(new PhoneFormatter(phone, "+90 (###) ###-####", intent, DataKeys.TELEFON_NO));
    }

    public void nextPageButton(View view){
        //Intent intent=new Intent(MainActivity.this,SecondPage.class);
        if (name.getText().toString().equals("") | tc.getText().toString().equals("") |
                phone.getText().toString().equals("")) {
            showErrorMessage("Lütfen boş alanları doldurunuz");
        }
        else if (!name.getText().toString().toUpperCase().matches("^[A-ZÇÖĞÜŞIİ]+( [A-ZÇÖĞÜŞIİ]+)+\\s*$")) {
            showErrorMessage("Ad Soyad alanında sadece harf bulunabilir, " +
                    "sayı veya noktalama işareti bulunamaz!");
        }
        else if (tc.getText().toString().length() != 11) {
            showErrorMessage("TC numarası 11 haneli olmalı!");
        }
        else if (phone.getText().toString().length() != 18) {
            showErrorMessage("Telefon numarasını tekrar kontrol ediniz!");
        }
        else {
            Intent intent = new Intent(MainActivity.this, SecondPage.class);
            intent.putExtras(this.intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }
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