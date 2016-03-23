package com.example.gregmiller.picselectfragment;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentTwo extends Fragment {
    //properties
    private Animation rotation;
    private ImageView displayedImg;

    //string array for image names
    String[] web = {
            "Image 1",
            "Image 2",
            "Image 3",
            "Image 4",
            "Image 5"
    };

    //int array for picture ids
    Integer[] imageId = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_two, container, false);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_two, container, false);
    }

    //when it's created, do this
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        //get bundle to see which one was clicked
        Bundle b = this.getArguments();
        int specificimg = b.getInt("imageId");

        displayedImg = (ImageView) getView().findViewById(R.id.imageView2);
        displayedImg.setImageResource(imageId[specificimg]);
        //wave the flag
        waveFlag();
        displayedImg.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    //wave flag method
    public void waveFlag()
    {
        //load animation
        rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotation);
        rotation.setRepeatCount(1); // animation repeats 3 times
        displayedImg.startAnimation(rotation);// play the animation
    }


    public FragmentTwo() {
        // Required empty public constructor
    }


}







