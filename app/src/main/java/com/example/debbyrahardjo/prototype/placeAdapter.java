package com.example.debbyrahardjo.prototype;

import com.example.debbyrahardjo.prototype.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by debbyrahardjo on 21/04/2015.
 */

//buat nampilin layout bentuk list
public class placeAdapter extends ArrayAdapter {
    private LayoutInflater inflater;

    public placeAdapter(Activity activity, String[] item){
        super(activity,R.layout.place_list,item);
        inflater = activity.getWindow().getLayoutInflater();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return inflater.inflate(R.layout.place_list,parent,false);
    }
}
