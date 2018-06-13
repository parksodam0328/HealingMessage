package kr.hs.emirim.parksodam.healingmessage.slider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import kr.hs.emirim.parksodam.healingmessage.R;
/**
 * Created by Mirim on 2018-04-03.
 */

public class FragmentSlider extends Fragment {

    private static final String ARG_PARAM1 = "params";

    private int imageUrls;

    public FragmentSlider() {
    }

    public static FragmentSlider newInstance(int params) {
        FragmentSlider fragment = new FragmentSlider();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, params);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        imageUrls = getArguments().getInt(ARG_PARAM1);
        View view = inflater.inflate(R.layout.fragment_slider_item, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        Picasso.with(getActivity())
                .load(imageUrls)
                .placeholder(R.drawable.image_slider_1)
                .resize(1000,400)
                .placeholder(R.drawable.s1)
                .into(img);
        return view;
    }
}