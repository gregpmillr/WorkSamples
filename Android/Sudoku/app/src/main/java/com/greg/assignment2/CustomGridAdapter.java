package com.greg.assignment2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Boolean.FALSE;

/**
 * Created by greg on 2/17/17.
 */


public class CustomGridAdapter extends BaseAdapter {

    /**
     * Container for the items in a grid
     */
    static class ViewHolder{
        TextView text;
    }

    /**
     * Declare variables
     */
    private Context context;
    private String[] items;
    private Boolean[] masker;
    LayoutInflater inflater;

    /**
     * Constructor for current adapter
     * @param context
     * @param items
     * @param masker
     */
    public CustomGridAdapter(Context context, String[] items, Boolean[] masker ){
        this.context = context;
        this.items = items;
        this.masker = masker;

        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Set the view for each grid element
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.cell, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.txtCell);

            convertView.setTag(holder);

            if(masker[position]){
                holder.text.setText((items[position]));
                holder.text.setEnabled(FALSE);
            }else{ holder.text.setText(""); }

            //set backgrounds
            if( (position % 9 != 0) && (position % 3 == 0) ){ holder.text.setBackgroundResource(R.drawable.columnborder); }
            if( (position >= 18 && position <= 26) || (position >= 45 && position <= 53) ){ holder.text.setBackgroundResource(R.drawable.rowborder); }
            if( (position == 21 || position == 24 || position == 48 || position == 51 ) ){ holder.text.setBackgroundResource(R.drawable.cornerborder); }
        }else{
            holder = (ViewHolder) convertView.getTag();

            if(masker[position]){
                holder.text.setText((items[position]));
                holder.text.setEnabled(FALSE);
            }else{ holder.text.setText(""); }

            if( (position % 9 != 0) && (position % 3 == 0) ){ holder.text.setBackgroundResource(R.drawable.columnborder);}
            if( (position >= 18 && position <= 26) || (position >= 45 && position <= 53) ){ holder.text.setBackgroundResource(R.drawable.rowborder); }
            if( (position == 21 || position == 24 || position == 48 || position == 51 ) ){ holder.text.setBackgroundResource(R.drawable.cornerborder); }
        }

        return convertView;
    }

    @Override
    public int getCount() { return items.length; }

    @Override
    public Object getItem(int position) { return items[position]; }

    @Override
    public long getItemId(int position) { return position; }
}
