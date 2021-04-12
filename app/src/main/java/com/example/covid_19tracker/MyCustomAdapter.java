package com.example.covid_19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<countrymodel> {
    private Context context;
    private List<countrymodel> countrylist;
    private List<countrymodel> countrylistfiltered;//for searching

    public MyCustomAdapter(Context context, List<countrymodel> countrylist) {
        super(context, R.layout.item, countrylist);
        this.context = context;
        this.countrylist = countrylist;
        this.countrylistfiltered = countrylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, true);
        TextView countryname = view.findViewById(R.id.country);
        ImageView flag = view.findViewById(R.id.flag);
        countryname.setText(countrylistfiltered.get(position).getCountry());
        Glide.with(context).load(countrylistfiltered.get(position).getFlag()).into(flag);

        return view;
    }

    @Override
    public int getCount() {
        return countrylistfiltered.size();
    }

    @Nullable
    @Override
    public countrymodel getItem(int position) {
        return countrylistfiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = countrylist.size();
                    filterResults.values = countrylist;

                } else {
                    List<countrymodel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (countrymodel itemsModel : countrylist) {
                        if (itemsModel.getCountry().toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countrylistfiltered = (List<countrymodel>) filterResults.values;
                Countries.countrymodelList = (List<countrymodel>) filterResults.values;
                notifyDataSetChanged();

            }
        };

        return filter;
    }
}
