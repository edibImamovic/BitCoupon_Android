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
public class FragmentPage4 extends Fragment {

    public static final String FRAG_FOUR = "com.project.bitcoupon.bitcoupon.frag.four";
    private  int couponId;
    public FragmentPage4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_page4, container, false);
        Bundle arguments = getArguments();
        int position = arguments.getInt(FRAG_FOUR);



        String name = arguments.getString("name");
        couponId = arguments.getInt("couponId");
        String expiration = arguments.getString("expiration");
        String minOrder = arguments.getString("minOrder");
        String maxOrder = arguments.getString("maxOrder");
        String price = arguments.getString("price");
        String categoryName = arguments.getString("categoryName");
        String seller = arguments.getString("seller");

        TextView mExpiration = (TextView) v.findViewById(R.id.textview_singleCouponExpiration);
        mExpiration.setText("Expiration date: " + expiration);

        TextView mName = (TextView) v.findViewById(R.id.textview_singleCouponName);
        mName.setText(name);

        TextView  mCategoryName = (TextView) v.findViewById(R.id.textview_name_of_category);
        mCategoryName.setText("Category Name: " + categoryName);

        TextView mMinOrder = (TextView) v.findViewById(R.id.textview_singleCouponMinOrder);
        mMinOrder.setText("Coupons left to reach the offer goal: " + minOrder);

        TextView mMaxOrder = (TextView) v.findViewById(R.id.textview_singleCouponMaxOrder);
        mMaxOrder.setText("Maximal order of Coupons: " + maxOrder);

        TextView mPrice = (TextView) v.findViewById(R.id.textview_singleCouponPrice);
        mPrice.setText("Price: " + price + " " +getString(R.string.currency));

        TextView mSeller = (TextView) v.findViewById(R.id.textview_singleCouponSeller);
        mSeller.setText("Seller of Coupon: " + seller);

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
