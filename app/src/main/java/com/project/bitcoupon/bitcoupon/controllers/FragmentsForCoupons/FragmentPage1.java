package com.project.bitcoupon.bitcoupon.controllers.FragmentsForCoupons;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.bitcoupon.bitcoupon.R;
import com.project.bitcoupon.bitcoupon.controllers.PayPalActivity;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPage1 extends Fragment {

    public static final String FRAG_ONE = "com.project.bitcoupon.bitcoupon.frag.one";
   private  int couponId;
    public FragmentPage1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment_page1, container, false);
        Bundle arguments = getArguments();
        int position = arguments.getInt(FRAG_ONE);


        //For picture

        String imgPath = arguments.getString("picture");
        String categoryName = arguments.getString("categoryName");
        String name = arguments.getString("name");
        couponId= arguments.getInt("couponId");

        ImageView mPicture = (ImageView) v.findViewById(R.id.imageview_singleCouponImage);
        String img = getString(R.string.image_path) + imgPath;
        img = img.replaceAll("\\\\","/");
        Picasso.with(getActivity()).load(img).into(mPicture);



        TextView  mCategoryName = (TextView) v.findViewById(R.id.textview_name_of_category);
        mCategoryName.setText(categoryName);

        TextView  mName = (TextView) v.findViewById(R.id.textview_singleCouponName);
        mName.setText(name);

        Button buyButton = (Button)v.findViewById(R.id.buy_button);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PayPalActivity.class);

                i.putExtra("couponId", couponId);
                startActivity(i);
            }
        });
        return v;

    }


}
