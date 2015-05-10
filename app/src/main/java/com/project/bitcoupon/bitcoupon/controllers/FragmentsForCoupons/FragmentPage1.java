package com.project.bitcoupon.bitcoupon.controllers.FragmentsForCoupons;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.bitcoupon.bitcoupon.R;
import com.project.bitcoupon.bitcoupon.controllers.PayPalActivity;
import com.project.bitcoupon.bitcoupon.singletons.UserData;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPage1 extends Fragment {

    private SharedPreferences mSharedPreferences;
    String email;
    public static final String FRAG_ONE = "com.project.bitcoupon.bitcoupon.frag.one";
    private  int couponId;
    public FragmentPage1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        email = UserData.getInstance().getEmail();
        View v = inflater.inflate(R.layout.fragment_fragment_page1, container, false);
        Bundle arguments = getArguments();
        int position = arguments.getInt(FRAG_ONE);

        //For picture
        String imgPath = arguments.getString("picture");
        String categoryName = arguments.getString("categoryName");
        String name = arguments.getString("name");
        String price = arguments.getString("price");
        couponId= arguments.getInt("couponId");

        ImageView mPicture = (ImageView) v.findViewById(R.id.imageview_singleCouponImage);

        String img = imgPath;

        img = img.replaceAll("\\\\","/");

        Picasso.with(getActivity()).load(img).into(mPicture);

        TextView  mCategoryName = (TextView) v.findViewById(R.id.textview_name_of_category);
        mCategoryName.setText(getString(R.string.category_name) + categoryName);

        TextView  mName = (TextView) v.findViewById(R.id.textview_singleCouponName);
        mName.setText(name);

        TextView mPrice = (TextView) v.findViewById(R.id.textview_singleCouponPrice);
        mPrice.setText(getString(R.string.price) + price + " " +getString(R.string.currency));

        Button buyButton = (Button)v.findViewById(R.id.buy_button);

        if(email != null ) {
            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent i = new Intent(getActivity(), PayPalActivity.class);
                        i.putExtra("couponId", couponId);
                        startActivity(i);
                    }

             });
        }else{
            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "You need to be logged in.", Toast.LENGTH_SHORT).show();
                }
            });
        }
       return v;

    }


}
