package com.example.covid_19tracker;
//1:31
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Countries extends AppCompatActivity {
    //https://disease.sh/v3/covid-19/countries
    EditText search;
    ListView list;
    SimpleArcLoader arcLoader;
    String urlApi="https://disease.sh/v3/covid-19/countries";

    public static List<countrymodel> countrymodelList=new ArrayList<>();
    countrymodel model;
    MyCustomAdapter myCustomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        search=findViewById(R.id.search);
        list=findViewById(R.id.list);
        arcLoader=findViewById(R.id.loader);

        getSupportActionBar().setTitle("Affected Countries");//change titel
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//home btn
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        fetchdata();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(),detailofcountry.class).putExtra("position",i));
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 myCustomAdapter.getFilter().filter(charSequence);
                 myCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
//for home btn
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchdata()
    {

        arcLoader.start();
        StringRequest request=new StringRequest(Request.Method.GET, urlApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONArray jsonArray=new JSONArray(response);//array of object
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String countryname=jsonObject.getString("country");
                        String cases=jsonObject.getString("cases");
                        String todaycases=jsonObject.getString("todayCases");
                        String deaths=jsonObject.getString("deaths");
                        String todaydeaths=jsonObject.getString("todayDeaths");
                        String recovered=jsonObject.getString("recovered");
                        String active=jsonObject.getString("active");
                        String critical=jsonObject.getString("critical");
                        String testspermillion=jsonObject.getString("testsPerOneMillion");

                        JSONObject object=jsonObject.getJSONObject("countryInfo"); //another object of countryInfo
                        String flag=object.getString("flag");

                        model=new countrymodel(flag,countryname,cases,todaycases,deaths,todaydeaths,recovered,active,critical,testspermillion);
                        countrymodelList.add(model);
                    }
                    myCustomAdapter=new MyCustomAdapter(getApplicationContext(),countrymodelList);
                    list.setAdapter(myCustomAdapter);
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);



                } catch (JSONException e) {
                    e.printStackTrace();
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),""+error.getMessage(),Toast.LENGTH_LONG).show();
                arcLoader.stop();
                arcLoader.setVisibility(View.GONE);
            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}