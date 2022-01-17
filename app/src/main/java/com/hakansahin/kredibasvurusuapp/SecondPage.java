package com.hakansahin.kredibasvurusuapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hakansahin.kredibasvurusuapp.formatter.CurrencyFormatter;
import com.hakansahin.kredibasvurusuapp.util.DataKeys;

public class SecondPage extends AppCompatActivity {

    Intent intent;
    EditText gelir;
    TextView egitimDurumu;
    TextView isDurumu;
    TextView meslek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        gelir = findViewById(R.id.aylıkGelirEditText);
        egitimDurumu = findViewById(R.id.egitimDurumuTextView);
        isDurumu = findViewById(R.id.isDurumuTextView);
        meslek = findViewById(R.id.meslekTextView);

        /*
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        */

        getSupportActionBar().hide();

        findViewById(R.id.filter).setAlpha(0.5f);
        intent = getIntent();

        gelir.addTextChangedListener(new CurrencyFormatter(gelir, intent, DataKeys.GELIR));

        String g = intent.getStringExtra(DataKeys.GELIR);
        if(g != null){
            gelir.setText(g);
        }

        String e = intent.getStringExtra(DataKeys.EGITIM_DURUMU);
        if(e != null){
            egitimDurumu.setText(e);
        }

        String i = intent.getStringExtra(DataKeys.IS_DURUMU);
        if(i != null){
            isDurumu.setText(i);
        }

        String m = intent.getStringExtra(DataKeys.MESLEK);
        if(m != null){
            meslek.setText(m);
        }
    }



    public void basvur(View view){

        //Pop-up
        if (gelir.getText().toString().equals("") | egitimDurumu.getText().toString().equals("") |
                meslek.getText().toString().equals("")| isDurumu.getText().toString().equals("")) {
            showErrorMessage("Lütfen boş alanları doldurunuz!");
        }
        else{
            Dialog dialog;
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_yes_no_dialog);
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_text_input));
            dialog.setCancelable(false);

            Button evetButton = dialog.findViewById(R.id.evetButton);
            Button hayirButton = dialog.findViewById(R.id.hayirButton);

            evetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    findViewById(R.id.filter).setVisibility(View.VISIBLE);
                    findViewById(R.id.loading).setVisibility(View.VISIBLE);
                    setComponents(false);

                    //7 saniye bekle sonraki sayfaya geç
                    Handler handler = new Handler();

                    handler.postDelayed(new NextPage(intent), 7000);


                    dialog.dismiss();
                }
            });

            hayirButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();

            /*
            AlertDialog.Builder alert= new AlertDialog.Builder(this);
            alert.setTitle("Başvur");
            alert.setMessage("Başvuruyu onaylıyor musunuz ?");
            alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    findViewById(R.id.filter).setVisibility(View.VISIBLE);
                    findViewById(R.id.loading).setVisibility(View.VISIBLE);
                    findViewById(R.id.buttonBasvur).setEnabled(false);

                    //7 saniye bekle sonraki sayfaya geç
                    Handler handler = new Handler();
                    handler.postDelayed(new NextPage(intent), 7000);

                }
            });
            alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.show(); */
        }
    }

    public void setComponents(boolean bool){
        egitimDurumu.setEnabled(bool);
        findViewById(R.id.imageButton2).setEnabled(bool);
        isDurumu.setEnabled(bool);
        findViewById(R.id.imageButton3).setEnabled(bool);
        meslek.setEnabled(bool);
        findViewById(R.id.imageButton4).setEnabled(bool);
        gelir.setEnabled(bool);
        findViewById(R.id.buttonBasvur).setEnabled(bool);
    }


    public void EgitimButton(View view){
        Intent intent = new Intent(SecondPage.this, Egitim_durumu.class);
        intent.putExtras(this.intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void isButton(View view) {
        if(this.intent.getStringExtra(DataKeys.EGITIM_DURUMU) != null){
            Intent intent = new Intent(SecondPage.this, Is_Bilgisi.class);
            intent.putExtras(this.intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }else {
            showErrorMessage("Önce Eğitim Bilgisi Giriniz!");
        }


    }

    public void meslekSecimEkraniButton(View view){
        if(this.intent.getStringExtra(DataKeys.IS_DURUMU) != null){
            Intent intent = new Intent(SecondPage.this,MeslekSecimEkrani.class);
            intent.putExtras(this.intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }else {
            showErrorMessage("Önce İş Bilgisi Giriniz!");
        }
    }

    public void previousPageButton(View view){
        Intent intent = new Intent(SecondPage.this, Egitim_durumu.class);
        intent.putExtras(this.intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
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


    public class NextPage implements Runnable{

        Intent intent;

        public NextPage(Intent intent) {
            this.intent = intent;
        }

        @Override
        public void run() {
            findViewById(R.id.filter).setVisibility(View.INVISIBLE);
            findViewById(R.id.loading).setVisibility(View.INVISIBLE);
            setComponents(true);
            Intent intent = new Intent(SecondPage.this,ThirdPage.class);
            intent.putExtras(this.intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }
}