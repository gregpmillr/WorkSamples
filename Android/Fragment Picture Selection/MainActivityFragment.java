package com.example.gregmiller.picselectfragment;

import android.app.Fragment;
import android.app.FragmentManager;//fragment transations
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    //declarations
    private SharedPreferences prefs;
    private ListView mListView;
    private customFirstList mAdapter;

    List<String> Lines;


    //strings array for image names
    String[] web = {
            "Image 1",
            "Image 2",
            "Image 3",
            "Image 4",
            "Image 5"
    };
    //int array for image ids
    Integer[] imageId = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5
    };

    //constructor for fragment
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //show the view on the page by inflating fragment_main
        Lines = Arrays.asList(getResources().getStringArray(R.array.imagearray));
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mListView = (ListView) getView().findViewById(R.id.listView);//get view from xml
        mAdapter= new customFirstList(getActivity(),web, imageId);//make new adapter
        mListView.setAdapter(mAdapter);//set the adapter to the list view
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();//when list view is clicked, you have access to it's properties
                prefs=getActivity().getPreferences(Context.MODE_PRIVATE);//when item is clicked, then add it to shared pref
                SharedPreferences.Editor editor = prefs.edit();
                view.setBackgroundColor(Color.BLACK);//for if the commit isn't set, then disable the views
                view.setClickable(true);
                view.setEnabled(false);
                editor.putString(web[+position],web[+position]);//but the string where the user clicked into shared pref
                editor.commit();
                Toast.makeText(getActivity().getBaseContext(),"Committed",Toast.LENGTH_LONG).show();

                int itemPosition = position;

                    //create bundle to store into fragment
                    Bundle bundle=new Bundle();
                    bundle.putInt("imageId", itemPosition);

                   FragmentTwo fr = new FragmentTwo();
                    fr.setArguments(bundle);
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, fr);//replace this fragment with the next one
                    fragmentTransaction.addToBackStack(null);//add to back stack so we can go back to this fragment!
                fragmentTransaction.commit();

            }
        });
    }



}
