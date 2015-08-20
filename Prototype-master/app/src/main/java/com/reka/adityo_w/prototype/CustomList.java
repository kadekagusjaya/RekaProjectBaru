package com.reka.adityo_w.prototype;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Adityo_W on 18/08/2015.
 */
public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;

    public CustomList(Activity context, String[] web, Integer[] imageId) {
        super(context, R.layout.menu_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.menu_single, null, true);

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        TextView txtTitle = (TextView) rowView.findViewById(R.id.menu_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.menu_icon);

        txtTitle.setText(web[position]);
        txtTitle.setWidth(dm.widthPixels - 100);

        imageView.setImageResource(imageId[position]);

        return rowView;
    }
}
