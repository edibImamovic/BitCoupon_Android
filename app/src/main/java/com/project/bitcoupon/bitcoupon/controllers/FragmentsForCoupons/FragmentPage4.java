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




        String expiration = arguments.getString("expiration");
        String name = arguments.getString("name");
        couponId = arguments.getInt("couponId");

        TextView mExpiration = (TextView) v.findViewById(R.id.textview_expiration);
        mExpiration.setText(expiration);

        TextView mName = (TextView) v.findViewById(R.id.textview_singleCouponName);
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
