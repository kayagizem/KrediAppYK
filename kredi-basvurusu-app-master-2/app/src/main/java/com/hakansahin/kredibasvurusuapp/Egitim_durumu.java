package com.hakansahin.kredibasvurusuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hakansahin.kredibasvurusuapp.util.DataKeys;

import java.util.ArrayList;
import java.util.List;

public class Egitim_durumu extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Intent intent;

    List<String> EducationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egitim_durumu);
        getSupportActionBar().hide();

        intent = getIntent();

        EducationList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(EducationList, DataKeys.EGITIM_DURUMU, intent, this, SecondPage.class);

        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        EducationList.add("İlköğretim");
        EducationList.add("Lise");
        EducationList.add("Ön Lisans");
        EducationList.add("Lisans");
        EducationList.add("Master/Doktora");
    }

    private void filter(String text) {
        ArrayList<String> filteredList = new ArrayList<>();

        for (String item : EducationList) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerAdapter.filterList(filteredList);
    }

    public void pPageButton(View view){
        Intent intent = new Intent(Egitim_durumu.this, SecondPage.class);
        intent.putExtras(this.intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}