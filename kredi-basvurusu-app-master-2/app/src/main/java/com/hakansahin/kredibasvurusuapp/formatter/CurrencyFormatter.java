package com.hakansahin.kredibasvurusuapp.formatter;

import android.content.Intent;
import android.text.Editable;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter extends Formatter {
    private String current = "";

    public CurrencyFormatter(EditText editText, Intent intent, String dataKey) {
        super(editText, intent, dataKey);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(!s.toString().equals(current)){
            ((EditText)textView).removeTextChangedListener(this);

            String cleanString = s.toString().replaceAll("[₺,. TL]", "");

            double parsed = Double.parseDouble(cleanString.toString());
            Locale tr = new Locale("tr", "TR");
            NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(tr);
            String formatted = currencyInstance.format((parsed/100));
            formatted = formatted.replaceAll("[^0123456789.,()-]","");

            current = formatted;
            ((EditText)textView).setText(formatted);

            ((EditText)textView).addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        ((EditText)textView).removeTextChangedListener(this);
        ((EditText)textView).setText(((EditText)textView).getText() + " TL");
        ((EditText)textView).setSelection(((EditText)textView).getText().length() - 3);
        ((EditText)textView).addTextChangedListener(this);
        super.afterTextChanged(editable);
    }
}
