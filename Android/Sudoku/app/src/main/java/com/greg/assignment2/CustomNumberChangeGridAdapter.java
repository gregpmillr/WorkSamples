package com.greg.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Boolean.FALSE;

/**
 * Created by greg on 2/20/17.
 */

public class CustomNumberChangeGridAdapter extends BaseAdapter {

    /**
     * Container for the items in a grid
     */
    static class ViewHolder{
        Button btnChangeNum;
    }

    /**
     * Declare variables
     */
    LayoutInflater inflater;
    private Context context;
    private String[] items;

    /**
     * Constructor for current adapter
     * @param context
     * @param items
     */
    public CustomNumberChangeGridAdapter(Context context, String[] items){
        this.context = context;
        this.items = items;

        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Sets the view for each grid element
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent){
        CustomNumberChangeGridAdapter.ViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.numberchange, parent, false);
            holder = new CustomNumberChangeGridAdapter.ViewHolder();
            holder.btnChangeNum = (Button) convertView.findViewById(R.id.btnChangeNum);

            convertView.setTag(holder);
        }else{
            holder = (CustomNumberChangeGridAdapter.ViewHolder) convertView.getTag();
        }

        //set the text of items from 1-9
        holder.btnChangeNum.setText(items[position]);

        return convertView;
    }

    @Override
    public int getCount() { return items.length; }

    @Override
    public Object getItem(int position) { return items[position]; }

    @Override
    public long getItemId(int position) { return position; }
}
