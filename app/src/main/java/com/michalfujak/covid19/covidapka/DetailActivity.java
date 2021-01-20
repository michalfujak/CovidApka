package com.michalfujak.covid19.covidapka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private int positionCountry;

    private TextView txtNameCountry;
    private TextView txtDetailCases;
    private TextView txtDetailRecovered;
    private TextView txtDetailCritical;
    private TextView txtDetailActive;
    private TextView txtDetailTodayCases;
    private TextView txtDetailTodayDeaths;
    private TextView txtDetailTotalDeaths;

    private ImageView imageDetailViewFlagImage;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Disabled, menu not exists
        // getSupportActionBar().setTitle(R.string.button_actionbar_back_action_home);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // StartActivity
        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position", 0);

        txtNameCountry = findViewById(R.id.text_view_detail_content_name_country);
        txtDetailCases = findViewById(R.id.text_view_detail_content_cases);
        txtDetailRecovered = findViewById(R.id.text_view_detail_content_recovered);
        txtDetailCritical = findViewById(R.id.text_view_detail_content_critical);
        txtDetailActive = findViewById(R.id.text_view_detail_content_active);
        txtDetailTodayCases = findViewById(R.id.text_view_detail_content_today_cases);
        txtDetailTodayDeaths = findViewById(R.id.text_view_detail_content_today_deaths);
        txtDetailTotalDeaths = findViewById(R.id.text_view_detail_content_total_deaths);

        imageDetailViewFlagImage = findViewById(R.id.image_view_detail_contry_flag);

        // fill data
        txtNameCountry.setText(AffectedCountries.listCountryModel.get(positionCountry).getCountry());
        txtDetailCases.setText(AffectedCountries.listCountryModel.get(positionCountry).getCases());
        txtDetailRecovered.setText(AffectedCountries.listCountryModel.get(positionCountry).getRecovered());
        txtDetailCritical.setText(AffectedCountries.listCountryModel.get(positionCountry).getCritical());
        txtDetailActive.setText(AffectedCountries.listCountryModel.get(positionCountry).getActive());
        txtDetailTodayCases.setText(AffectedCountries.listCountryModel.get(positionCountry).getTotalCases());
        txtDetailTodayDeaths.setText(AffectedCountries.listCountryModel.get(positionCountry).getDeaths());
        txtDetailTotalDeaths.setText(AffectedCountries.listCountryModel.get(positionCountry).getTotalDeaths());

        Glide.with(DetailActivity.this).load(AffectedCountries.listCountryModel.get(positionCountry).getFlag()).into(imageDetailViewFlagImage);

    }

    /**
     * Override:     true
     * function:     onOptionsItemSelected(item)
     * param:        item
     * return:       item
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        //
        return super.onOptionsItemSelected(item);
    }
}