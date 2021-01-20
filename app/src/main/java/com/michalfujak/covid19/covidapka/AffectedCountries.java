package com.michalfujak.covid19.covidapka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountries extends AppCompatActivity {

    // Components
    MaterialEditText materialEditTextSearch;
    ListView listViewCountriesFlag;
    SimpleArcLoader loaderSimpleArcCountriesFlag;

    public static List<CountryModel> listCountryModel = new ArrayList<>();
    CountryModel objCountryModel;
    CovidAdapter objCovidAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);
        //
        materialEditTextSearch = findViewById(R.id.countries_components_search);
        listViewCountriesFlag = findViewById(R.id.list_view_countries_flag);
        loaderSimpleArcCountriesFlag = findViewById(R.id.arc_loader_countries_flag);

        // Call fetchListData
        fetchListData();

        // ListView and ClickFocusFlag
        listViewCountriesFlag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Clicked
                startActivity(new Intent(getApplicationContext(), DetailActivity.class).putExtra("position", position));
            }
        });

        // For ActionBar, button back home.
        // Disabled, menu not exists
        // getSupportActionBar().setTitle(R.string.button_actionbar_back_action_home);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Search
        materialEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // MyFilter
                objCovidAdapter.getFilter().filter(s);
                objCovidAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * function:      fetchListData(void)
     * return:        null
     */
    private void fetchListData()
    {
        String url = "https://corona.lmao.ninja/v2/countries";
        loaderSimpleArcCountriesFlag.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Response
                        try
                        {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i = 0; i<jsonArray.length(); i++ )
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                // fill
                                String updated = jsonObject.getString("updated");
                                String country = jsonObject.getString("country");
                                String cases = jsonObject.getString("cases");
                                String totalCases = jsonObject.getString("todayCases");
                                String deaths = jsonObject.getString("todayDeaths");
                                String totalDeaths = jsonObject.getString("deaths");
                                String recovered = jsonObject.getString("recovered");
                                String todayRecovered = jsonObject.getString("todayRecovered");
                                String active = jsonObject.getString("active");
                                String critical = jsonObject.getString("critical");

                                //
                                JSONObject jsonObjectFlag = jsonObject.getJSONObject("countryInfo");
                                String flagUrl = jsonObjectFlag.getString("flag");

                                // Call CountryModel
                                objCountryModel = new CountryModel(
                                        updated,
                                        flagUrl,
                                        country,
                                        cases,
                                        totalCases,
                                        deaths,
                                        totalDeaths,
                                        recovered,
                                        todayRecovered,
                                        active,
                                        critical );
                                listCountryModel.add(objCountryModel);
                            }

                            // Call Adapter
                            objCovidAdapter = new CovidAdapter(AffectedCountries.this, listCountryModel);
                            // Default ListView, fill adapter
                            listViewCountriesFlag.setAdapter(objCovidAdapter);
                            loaderSimpleArcCountriesFlag.stop();
                            loaderSimpleArcCountriesFlag.setVisibility(View.GONE);
                        }
                        catch(JSONException jsonException)
                        {
                            jsonException.printStackTrace();
                            loaderSimpleArcCountriesFlag.stop();
                            loaderSimpleArcCountriesFlag.setVisibility(View.GONE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Error stage
                loaderSimpleArcCountriesFlag.stop();
                loaderSimpleArcCountriesFlag.setVisibility(View.GONE);
                Toast.makeText(AffectedCountries.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}































