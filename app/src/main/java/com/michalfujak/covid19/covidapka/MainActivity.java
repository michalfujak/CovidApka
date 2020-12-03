package com.michalfujak.covid19.covidapka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Initializable component

    // TextView menu
    TextView objCases;
    TextView objRecovered;
    TextView objCritical;
    TextView objActive;
    TextView objTodayCases;
    TextView objTodayDeaths;
    TextView objTotalDeaths;
    TextView objAffectedCountries;

    // ArcLoader
    SimpleArcLoader objSimpleArcLoader;

    // ScrollView
    ScrollView objScrollView;

    // PieChart - statistic
    PieChart objPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        objCases = (TextView)findViewById(R.id.text_view_content_cases);
        objRecovered = (TextView)findViewById(R.id.text_view_content_recovered);
        objCritical = (TextView)findViewById(R.id.text_view_content_critical);
        objActive = (TextView)findViewById(R.id.text_view_content_active);
        objTodayCases = (TextView)findViewById(R.id.text_view_content_today_cases);
        objTodayDeaths = (TextView)findViewById(R.id.text_view_content_today_deaths);
        objTotalDeaths = (TextView)findViewById(R.id.text_view_content_total_deaths);
        objAffectedCountries = (TextView)findViewById(R.id.text_view_content_affected_countries);
        //
        objSimpleArcLoader = (SimpleArcLoader)findViewById(R.id.global_stats_loader_content);
        objScrollView = (ScrollView)findViewById(R.id.scrollview_content_global_stats);
        objPieChart = (PieChart)findViewById(R.id.piechart_track_countries_statistic);

        //
        fetchData();
    }

    public void goTrackCountries(View view) {
        // action click affected countries
        startActivity(new Intent(getApplicationContext(), AffectedCountries.class));
    }

    /**
     * function: fetchData()
     * param:    void
     */
    private void fetchData()
    {
        String url = "https://corona.lmao.ninja/v2/all/";
        objSimpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //JsonObject
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            // setComponent
                            objCases.setText(jsonObject.getString("cases"));
                            objRecovered.setText(jsonObject.getString("recovered"));
                            objCritical.setText(jsonObject.getString("critical"));
                            objActive.setText(jsonObject.getString("active"));
                            objTodayCases.setText(jsonObject.getString("todayCases"));
                            objTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                            objTotalDeaths.setText(jsonObject.getString("deaths"));
                            objAffectedCountries.setText(jsonObject.getString("affectedCountries"));

                            //PieChart statistic
                            objPieChart.addPieSlice(new PieModel( getString(R.string.piechart_text_total_cases),  Integer.parseInt(objCases.getText().toString()), Color.parseColor("#FF9800")));
                            objPieChart.addPieSlice(new PieModel( getString(R.string.piechart_text_recovered),  Integer.parseInt(objRecovered.getText().toString()), Color.parseColor("#00E676")));
                            objPieChart.addPieSlice(new PieModel( getString(R.string.piechart_text_active),  Integer.parseInt(objActive.getText().toString()), Color.parseColor("#03A9F4")));
                            objPieChart.addPieSlice(new PieModel( getString(R.string.piechart_text_deaths),  Integer.parseInt(objTotalDeaths.getText().toString()), Color.parseColor("#424242")));

                            //start.animate
                            objPieChart.startAnimation();

                            //
                            objSimpleArcLoader.stop();
                            objSimpleArcLoader.setVisibility(View.GONE);
                            objScrollView.setVisibility(View.VISIBLE);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            objSimpleArcLoader.stop();
                            objSimpleArcLoader.setVisibility(View.GONE);
                            objScrollView.setVisibility(View.VISIBLE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Error state
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                objSimpleArcLoader.stop();
                objSimpleArcLoader.setVisibility(View.GONE);
                objScrollView.setVisibility(View.VISIBLE);
            }
        });

        // RequestQuery
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}