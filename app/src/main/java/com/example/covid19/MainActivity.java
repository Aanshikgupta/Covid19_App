package com.example.covid19;

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

    TextView worldCases,worldRecovered,worldCritical,worldActive,worldTodayCases,worldTotalDeaths,worldTodayDeaths,worldAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.scrollStats);
        pieChart = findViewById(R.id.piechart);
        worldCases = findViewById(R.id.WorldCases);
        worldRecovered = findViewById(R.id.WorldRecovered);
        worldCritical = findViewById(R.id.WorldCritical);
        worldActive = findViewById(R.id.WorldActive);
        worldTodayCases = findViewById(R.id.WorldTodayCases);
        worldTotalDeaths = findViewById(R.id.WorldTotalDeaths);
        worldTodayDeaths = findViewById(R.id.WorldTodayDeaths);
        worldAffectedCountries = findViewById(R.id.WorldAffectedCountries);


        getInfo();

    }

    private void getInfo() {

        String url  = "https://disease.sh/v3/covid-19/all";

        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            worldCases.setText(jsonObject.getString("cases"));
                            worldRecovered.setText(jsonObject.getString("recovered"));
                            worldCritical.setText(jsonObject.getString("critical"));
                            worldActive.setText(jsonObject.getString("active"));
                            worldTodayCases.setText(jsonObject.getString("todayCases"));
                            worldTotalDeaths.setText(jsonObject.getString("deaths"));
                            worldTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                            worldAffectedCountries.setText(jsonObject.getString("affectedCountries"));
                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(worldCases.getText().toString()), Color.parseColor("#FFA726")));
                            pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(worldRecovered.getText().toString()), Color.parseColor("#66BB6A")));
                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(worldTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(worldActive.getText().toString()), Color.parseColor("#29B6F6")));
                            pieChart.startAnimation();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void goTrackCountries(View view) {

        startActivity(new Intent(getApplicationContext(),AffectedCountries.class));

    }
}
