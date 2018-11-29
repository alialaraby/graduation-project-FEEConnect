package com.alisakralaraby.feeconnectdemo2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.alisakralaraby.feeconnectdemo2.R;

import java.util.ArrayList;

/**
 * Created by ali_a on 24/04/2017.
 */
public class AlarmsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> btnNames;

    public AlarmsAdapter(Context context, ArrayList<String> btnNames) {
        this.context = context;
        this.btnNames = btnNames;
    }

    @Override
    public int getCount() {
        return btnNames.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.alarm_list_item, null);
        Button button = (Button)view.findViewById(R.id.list_item_btn);
        button.setText(btnNames.get(position));
        return view;
    }
}
