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

public class Is_Bilgisi extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    List<String> isList;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_bilgisi);
        getSupportActionBar().hide();

        intent = getIntent();

        isList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(isList, DataKeys.IS_DURUMU, intent, this, SecondPage.class);

        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        isList.add("Serbest Meslek Sahibi");
        isList.add("Ücretli - Kamu Sektörü");
        isList.add("Ücretli - Özel Sektör");
        isList.add("Emekli + Çalışıyor");
        isList.add("Öğrenci");
        isList.add("Ev Hanımı");
        isList.add("Çalışmıyor");
        isList.add("Emekli");




        // Arama Cubugu
        EditText editText = findViewById(R.id.searchTextIs);
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

        for (String item : isList) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerAdapter.filterList(filteredList);
    }

    public void previousPageButton(View view){
        Intent intent = new Intent(Is_Bilgisi.this, SecondPage.class);
        intent.putExtras(this.intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}