package com.hakansahin.kredibasvurusuapp.formatter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class Formatter implements TextWatcher {

    private Intent intent;
    private String dataKey;
    protected TextView textView;

    public Formatter(TextView textView, Intent intent, String dataKey) {
        this.intent = intent;
        this.dataKey = dataKey;
        this.textView = textView;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        intent.putExtra(dataKey, textView.getText().toString());
    }
}
