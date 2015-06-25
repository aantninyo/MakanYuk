package com.example.debbyrahardjo.prototype;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RestaurantProfile extends Fragment {
    public RestaurantProfile(){

    }

    @Override
    public View  onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_profile,container,false);

        TextView name = (TextView) view.findViewById(R.id.name_resto);
        TextView address = (TextView) view.findViewById(R.id.address);

        name.setText(this.getArguments().getString("name"));
        address.setText(this.getArguments().getString("alamat"));
        return view;

    }

}
