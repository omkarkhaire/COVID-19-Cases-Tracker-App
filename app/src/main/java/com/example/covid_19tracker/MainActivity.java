package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    TextView cases,recovered,critical,active,todaycases,todaydeath,totaldeath,ac;
    SimpleArcLoader simpleArcLoader;
    PieChart pieChart;
    ScrollView scrollView;
    String urlApi="https://disease.sh/v3/covid-19/all";
    public void trackcountries(View view)
    {
        Intent intent=new Intent(getApplicationContext(),Countries.class);
        startActivity(intent);

    }

    //for menu we created
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);


        return super.onCreateOptionsMenu(menu);
    }
    //
//  when  we select menu options


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.rf:
                 fetchdata();
                return  true;


            case R.id.about:
//                Toast.makeText(getApplicationContext(),"yet to come",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
                return  true;

            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cases=findViewById(R.id.tvcases);
        recovered=findViewById(R.id.recovered);
        critical=findViewById(R.id.critical);
        active=findViewById(R.id.active);
        todaycases=findViewById(R.id.todaycases);
        todaydeath=findViewById(R.id.todaydeath);
        totaldeath=findViewById(R.id.totaldeaths);
        ac=findViewById(R.id.Affectedcountries);

        simpleArcLoader=findViewById(R.id.loader);
        pieChart=findViewById(R.id.piechart);
        scrollView=findViewById(R.id.scrollview);


        fetchdata();

    }

    private void fetchdata()
    {
        pieChart.clearChart();//while refreshing it is highly required
        //rest api from corona.imao.ninja site
      simpleArcLoader.start();
        StringRequest request=new StringRequest(Request.Method.GET, urlApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    cases.setText(jsonObject.getString("cases"));
                    recovered.setText(jsonObject.getString("recovered"));
                    critical.setText(jsonObject.getString("critical"));
                    active.setText(jsonObject.getString("active"));
                    todaycases.setText(jsonObject.getString("todayCases"));
                    todaydeath.setText(jsonObject.getString("todayDeaths"));
                    totaldeath.setText(jsonObject.getString("deaths"));

                    ac.setText(jsonObject.getString("affectedCountries"));

                    pieChart.addPieSlice(new PieModel("cases",Integer.parseInt(cases.getText().toString()), Color.parseColor("#FFA726")));
                    pieChart.addPieSlice(new PieModel("recovered",Integer.parseInt(recovered.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("deaths",Integer.parseInt(totaldeath.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("active",Integer.parseInt(active.getText().toString()), Color.parseColor("#29B6F6")));
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
                Toast.makeText(getApplicationContext(),""+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


}

//<color name="cases">#FFA726</color>
//<color name="recovered">#66BB6A</color>
//<color name="deaths">#EF5350</color>
//<color name="active">#29B6F6</color>