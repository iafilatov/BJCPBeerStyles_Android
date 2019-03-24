package io.github.rlshep.bjcp2015beerstyles;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.apache.commons.lang.StringUtils;

import java.util.Locale;

import io.github.rlshep.bjcp2015beerstyles.constants.BjcpConstants;

public abstract class BjcpActivity extends AppCompatActivity {
    protected void setupToolbar(int toolBarId, String title, boolean showIcon, boolean showUp) {
        Toolbar toolbar = findViewById(toolBarId);

        if (!StringUtils.isEmpty(title)) {
            toolbar.setTitle(title);
        }

        setSupportActionBar(toolbar);

        if (showUp) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (showIcon) {
            getSupportActionBar().setIcon(R.drawable.action_icon);
        }
    }

    public String getLanguage() {
        String language = "en";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            language = getLanguageFromMultipleLocales();
        } else if (BjcpConstants.allowedLanguages.contains(Locale.getDefault().getLanguage())) {
            language = Locale.getDefault().getLanguage();
        }

        return language;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLanguageFromMultipleLocales() {
        String language = "en";
        int i = 0;
        boolean found = false;
        LocaleList locales = this.getApplicationContext().getResources().getConfiguration().getLocales();

        while (!found && i < locales.size()) {
            String localeLanguage = locales.get(i).getLanguage();

            if (BjcpConstants.allowedLanguages.contains(localeLanguage)) {
                language = localeLanguage;
                found = true;
            }

            i++;
        }

        return language;
    }


    public String getCountry() {
        String country;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Locale primaryLocale = this.getApplicationContext().getResources().getConfiguration().getLocales().get(0);
            country = primaryLocale.getCountry();
        } else {
            country = Locale.getDefault().getCountry();
        }

        return country;
    }


    protected boolean isMetric() {
        boolean metric = false;
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String unitsPref = sharedPref.getString(BjcpConstants.UNIT, null); // getting String

        if (!StringUtils.isEmpty(unitsPref) && BjcpConstants.METRIC.equals(unitsPref)) {
            metric = true;
        }

        return metric;
    }

    protected void setUnitPreferences(String unit) {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(BjcpConstants.UNIT, unit);
        editor.commit();
    }
}
