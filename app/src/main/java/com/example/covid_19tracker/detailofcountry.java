package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import static com.example.covid_19tracker.Countries.countrymodelList;

public class detailofcountry extends AppCompatActivity {

    int position;
    TextView cases,recovered,critical,active,todaycases,todaydeath,totaldeath,testspermillion,country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailofcountry);


        Intent intent=getIntent();
        position=intent.getIntExtra("position",0);

        country=findViewById(R.id.country);
        cases=findViewById(R.id.tvcases);
        recovered=findViewById(R.id.recovered);
        critical=findViewById(R.id.critical);
        active=findViewById(R.id.active);
        todaycases=findViewById(R.id.todaycases);
        todaydeath=findViewById(R.id.todaydeath);
        totaldeath=findViewById(R.id.totaldeaths);
       testspermillion=findViewById(R.id.testpermillion);

        getSupportActionBar().setTitle("Country Status");//change titel
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//home btn
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        country.setText(countrymodelList.get(position).getCountry());
        cases.setText(countrymodelList.get(position).getCases());
        recovered.setText(countrymodelList.get(position).getRecovered());
        critical.setText(countrymodelList.get(position).getCritical());
        active.setText(countrymodelList.get(position).getActive());
        todaycases.setText(countrymodelList.get(position).getTodaycases());
        todaydeath.setText(countrymodelList.get(position).getTodaydeaths());
        totaldeath.setText(countrymodelList.get(position).getTotaldeaths());
        testspermillion.setText(countrymodelList.get(position).getTestspermillion());



    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}