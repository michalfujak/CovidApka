package com.michalfujak.covid19.covidapka;

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

public class CovidAdapter extends ArrayAdapter<CountryModel>
{
    // Variables
    private Context context;
    private List<CountryModel> listCountryModels;
    private List<CountryModel> listCountryModelsFilter;

    /**
     * construct:    CovidAdapter(content, resource)
     * param:        context
     * param:        resource
     */
    public CovidAdapter(Context context, List<CountryModel> listCountryModels)
    {
        // Adapter construct content...
        super(context, R.layout.activity_covid_data_list_items, listCountryModels);
        this.context = context;
        this.listCountryModels = listCountryModels;
        this.listCountryModelsFilter = listCountryModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        // View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_covid_data_list_items, null, true);
        // Initializable components
        ImageView objImageViewFlag = view.findViewById(R.id.list_item_image_flag);
        TextView objTextViewFlagName = view.findViewById(R.id.list_item_context_text_country_flag);
        //
        objTextViewFlagName.setText(listCountryModelsFilter.get(position).getCountry());
        Glide.with(context).load(listCountryModelsFilter.get(position).getFlag()).into(objImageViewFlag);


        return view;
    }

    @Override
    public int getCount() {
        return listCountryModelsFilter.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return listCountryModelsFilter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * function:     getFilter()
     * return:       objFilter List<data>
     */
    @NonNull
    @Override
    public Filter getFilter() {
        Filter objFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                // FilterResults
                FilterResults filterResults = new FilterResults();
                //
                if(constraint == null || constraint.length() == 0)
                {
                    filterResults.count = listCountryModels.size();
                    filterResults.values = listCountryModels;
                }
                else
                {
                    List<CountryModel> resultModel = new ArrayList<>();
                    String strSearch = constraint.toString().toLowerCase();

                    //
                    for(CountryModel countryItems: listCountryModels)
                    {
                        if(countryItems.getCountry().toLowerCase().contains(strSearch))
                        {
                            resultModel.add(countryItems);
                        }
                        filterResults.count = resultModel.size();
                        filterResults.values = resultModel;
                    }
                }
                // return filter data...
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listCountryModelsFilter = (List<CountryModel>) results.values;
                AffectedCountries.listCountryModel = (List<CountryModel>) results.values;
            }
        };
        return objFilter;
    }
}

























