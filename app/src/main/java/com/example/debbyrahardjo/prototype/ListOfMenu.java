package com.example.debbyrahardjo.prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class ListOfMenu extends Fragment {
    public ListOfMenu(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_of_menu,container,false);

        menuAdapter.setInflater(inflater);

        menuAdapter.setNama(this.getArguments().getStringArrayList("nama_makanan"));
        menuAdapter.setDekripsi(this.getArguments().getStringArrayList("dekripsi_makanan"));
        menuAdapter.setHarga(this.getArguments().getStringArrayList("harga_makanan"));
        menuAdapter Adapter = new menuAdapter();

        ListView myAdpt = (ListView) view.findViewById(R.id.listOfMenu);
        myAdpt.setAdapter(Adapter);

        return view;

    }


}
