package com.example.lab2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.squareup.picasso.Picasso;

 public class Pagefragment extends Fragment {
    String graphic;
    String helptext;

    public Pagefragment() {}

    @SuppressLint("ValidFragment")
    public Pagefragment(String graphic, String helptext) {
        Bundle arguments = new Bundle();
      this.graphic = graphic;
      this.helptext = helptext;
        arguments.putString("helptext", helptext);
        arguments.putString("graphic", graphic);
      setArguments(arguments);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pagefragment, container, false);

        ImageView imageGraphic = view.findViewById(R.id.graphic);

        if(getArguments().getString("graphic") != null)
            graphic = getArguments().getString("graphic");
        if(getArguments().getString("helptext") != null)
            helptext = getArguments().getString("helptext");

        if(graphic != null)
            Picasso.get().load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + graphic).
                    into(imageGraphic);

        TextView tvPage = (TextView) view.findViewById(R.id.tvPage);
        if(helptext != null)
            tvPage.setText(helptext);
        else
            tvPage.setText("No description found");

        return view;
    }
}