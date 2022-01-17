package com.hakansahin.kredibasvurusuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.hakansahin.kredibasvurusuapp.util.DataKeys;

import java.util.ArrayList;
import java.util.List;

public class VadeSecmeEkrani extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    List<String> vadeList;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vade_secme_ekrani);
        getSupportActionBar().hide();

        vadeList = new ArrayList<>();

        intent = getIntent();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(vadeList, DataKeys.VADE, intent, this, ThirdPage.class);

        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        for (int i = 1; i <= 48; i++) {
            vadeList.add(i + " Ay");
        }

        // Arama Cubugu
        EditText editText = findViewById(R.id.searchText);
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

        for (String item : vadeList) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerAdapter.filterList(filteredList);
    }

    public void previousPageButton(View view){
        Intent intent = new Intent(VadeSecmeEkrani.this, ThirdPage.class);
        intent.putExtras(this.intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}