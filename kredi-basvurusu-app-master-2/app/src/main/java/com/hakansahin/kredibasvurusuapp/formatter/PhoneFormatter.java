package com.hakansahin.kredibasvurusuapp.formatter;

import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.widget.EditText;

public class PhoneFormatter extends Formatter {

    private String mPattern;

    public PhoneFormatter(EditText editText, String pattern, Intent intent, String dataKey) {
        super(editText, intent, dataKey);
        mPattern = pattern;
        int maxLength = pattern.length();
        ((EditText)textView).setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        StringBuilder phone = new StringBuilder(s);

        if (count > 0 && !isValid(phone.toString())) {
            for (int i = 0; i < phone.length(); i++) {
                char c = mPattern.charAt(i);

                if ((c != '#') && (c != phone.charAt(i))) {
                    phone.insert(i, c);
                }
            }

            ((EditText)textView).setText(phone);
            ((EditText)textView).setSelection(((EditText)textView).getText().length());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        super.afterTextChanged(s);
    }

    private boolean isValid(String phone)
    {
        for (int i = 0; i < phone.length(); i++) {
            char c = mPattern.charAt(i);

            if (c == '#') continue;

            if (c != phone.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
