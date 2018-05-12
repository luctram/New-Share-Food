package com.ptit.tranhoangminh.newsharefood.views.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.ptit.tranhoangminh.newsharefood.R;

public class Home1Fragment extends Fragment {

    ViewFlipper flipper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home1fragment, container, false);

        int images[] = {R.drawable.bread, R.drawable.vegitarian, R.drawable.egg};
        flipper = view.findViewById(R.id.flipper);

        for(int image : images) {
            flipperImage(image);
        }

        return view;
    }

    private void flipperImage(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);


    }

}
