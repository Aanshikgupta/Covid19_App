package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private  int positionCountry;
    TextView countryName,countryCases,countryRecovered,countryCritical,countryActive,countryTodayCases,tvTotalDeaths,countryTodayDeaths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+AffectedCountries.countryModelsList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        countryName = findViewById(R.id.CountryName);
        countryCases = findViewById(R.id.CountryCases);
        countryRecovered = findViewById(R.id.CountryRecovered);
        countryCritical = findViewById(R.id.CountryCritical);
        countryActive = findViewById(R.id.CountryActive);
        countryTodayCases = findViewById(R.id.CountryTodayCases);
        tvTotalDeaths = findViewById(R.id.CountryDeaths);
        countryTodayDeaths = findViewById(R.id.CountryTodayDeaths);

        countryName.setText(AffectedCountries.countryModelsList.get(positionCountry).getCountry());
        countryCases.setText(AffectedCountries.countryModelsList.get(positionCountry).getCases());
        countryRecovered.setText(AffectedCountries.countryModelsList.get(positionCountry).getRecovered());
        countryCritical.setText(AffectedCountries.countryModelsList.get(positionCountry).getCritical());
        countryActive.setText(AffectedCountries.countryModelsList.get(positionCountry).getActive());
        countryTodayCases.setText(AffectedCountries.countryModelsList.get(positionCountry).getTodayCases());
        tvTotalDeaths.setText(AffectedCountries.countryModelsList.get(positionCountry).getDeaths());
        countryTodayDeaths.setText(AffectedCountries.countryModelsList.get(positionCountry).getTodayDeaths());


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
