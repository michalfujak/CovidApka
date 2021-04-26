package com.michalfujak.covid19.covidapka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

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

    // flag top data CountryName (DayActive / DayDeath)
    private TextView txtdetailTopMenuCountryActiveDeath;

    private ImageView imageDetailViewFlagImage;
    private Context context;

    private PieChart objPieChartStatisticCountry;
    private BarChart objBarChartStatisticCountryData;

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

        objPieChartStatisticCountry = findViewById(R.id.piechart_detail_activity_piechart_statistic_data_one);
        objBarChartStatisticCountryData = findViewById(R.id.barchart_detail_activity_statistic_data);

        txtNameCountry = findViewById(R.id.text_view_detail_content_name_country);
        txtDetailCases = findViewById(R.id.text_view_detail_content_cases);
        txtDetailRecovered = findViewById(R.id.text_view_detail_content_recovered);
        txtDetailCritical = findViewById(R.id.text_view_detail_content_critical);
        txtDetailActive = findViewById(R.id.text_view_detail_content_active);
        txtDetailTodayCases = findViewById(R.id.text_view_detail_content_today_cases);
        txtDetailTodayDeaths = findViewById(R.id.text_view_detail_content_today_deaths);
        txtDetailTotalDeaths = findViewById(R.id.text_view_detail_content_total_deaths);
        //
        txtdetailTopMenuCountryActiveDeath = findViewById(R.id.text_view_detail_top_country_name_plus_active_data);

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
        //
        // Add ( TopMenu ) 15.4.2021
        String topTextData = " " + AffectedCountries.listCountryModel.get(positionCountry).getCountry() + " ( " +
                                   AffectedCountries.listCountryModel.get(positionCountry).getTotalCases() + " / " +
                                   AffectedCountries.listCountryModel.get(positionCountry).getDeaths() + " )";
        txtdetailTopMenuCountryActiveDeath.setText(topTextData);
        // fill data

        objPieChartStatisticCountry.addPieSlice(new PieModel(getString(R.string.detail_activity_piechart_text_total_cases), Integer.parseInt(txtDetailCases.getText().toString()), Color.parseColor("#FFB300")));
        objPieChartStatisticCountry.addPieSlice(new PieModel(getString(R.string.detail_activity_piechart_text_recovered), Integer.parseInt(txtDetailRecovered.getText().toString()), Color.parseColor("#00C853")));
        objPieChartStatisticCountry.addPieSlice(new PieModel(getString(R.string.detail_activity_piechart_text_critical), Integer.parseInt(txtDetailCritical.getText().toString()), Color.parseColor("#D50000")));
        objPieChartStatisticCountry.addPieSlice(new PieModel(getString(R.string.detail_activity_piechart_text_active), Integer.parseInt(txtDetailActive.getText().toString()), Color.parseColor("#40C4FF")));
        objPieChartStatisticCountry.addPieSlice(new PieModel(getString(R.string.detail_activity_piechart_text_total_death), Integer.parseInt(txtDetailTotalDeaths.getText().toString()), Color.parseColor("#37474F")));

        // active animate
        objPieChartStatisticCountry.startAnimation();

        // BarChart starting
        objBarChartStatisticCountryData.addBar(new BarModel(Integer.parseInt(txtDetailCases.getText().toString()), Color.parseColor("#FFB300")));
        objBarChartStatisticCountryData.addBar(new BarModel(Integer.parseInt(txtDetailRecovered.getText().toString()), Color.parseColor("#00C853")));
        objBarChartStatisticCountryData.addBar(new BarModel(Integer.parseInt(txtDetailCritical.getText().toString()), Color.parseColor("#D50000")));
        objBarChartStatisticCountryData.addBar(new BarModel(Integer.parseInt(txtDetailActive.getText().toString()), Color.parseColor("#40C4FF")));
        objBarChartStatisticCountryData.addBar(new BarModel(Integer.parseInt(txtDetailTotalDeaths.getText().toString()), Color.parseColor("#37474F")));

        objBarChartStatisticCountryData.startAnimation();

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