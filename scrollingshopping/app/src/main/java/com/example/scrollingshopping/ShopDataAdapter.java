package com.example.scrollingshopping;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ShopDataAdapter extends ArrayAdapter<ShopData> implements Filterable {

    private Context context;
    private int resource;
    private List<ShopData> shopDataList;
    private List<ShopData> filteredShopList;
    ShopDataAdapter.ValueFilter valueFilter;

    public ShopDataAdapter(@NonNull Context context, int resource, @NonNull List<ShopData> shopDataList) {
        super(context, resource, shopDataList);
        this.context = context;
        this.resource = resource;
        this.shopDataList = shopDataList;
        this.filteredShopList=shopDataList;
    }

    @Override
    public int getCount() {
        return shopDataList.size();
    }

    @Override
    public ShopData getItem(int position) {
        return shopDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int image = getItem(position).getImageResource();
        String name = getItem(position).getName();
        int cost = getItem(position).getCost();
        String desc = getItem(position).getDescription();

        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource,parent,false);
        ImageButton imageButton = convertView.findViewById(R.id.imageButton);
        imageButton.setImageResource(image);
        TextView nameBtn = convertView.findViewById(R.id.nameButton);
        nameBtn.setText(name);
        TextView costBtn = convertView.findViewById(R.id.costButton);
        costBtn.setText("$ "+ Integer.toString(cost) );
        TextView descBtn = convertView.findViewById(R.id.descButton);
        descBtn.setText(desc);
        return convertView;
    }

    public class ValueFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                List<ShopData> filterList = new ArrayList<>();
                for (int i = 0; i < filteredShopList.size(); i++) {
                    if (filteredShopList.get(i).getName() !=null && (filteredShopList.get(i).getName().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(filteredShopList.get(i));
                    }
                    if (filteredShopList.get(i).getDescription()!=null && (filteredShopList.get(i).getDescription().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(filteredShopList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = filteredShopList.size();
                results.values = filteredShopList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            shopDataList = (List<ShopData>) filterResults.values;
            notifyDataSetChanged();
        }
    }


}