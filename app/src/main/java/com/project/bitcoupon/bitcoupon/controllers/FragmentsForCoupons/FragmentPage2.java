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
import android.widget.Toast;

import com.project.bitcoupon.bitcoupon.R;
import com.project.bitcoupon.bitcoupon.controllers.PayPalActivity;
import com.project.bitcoupon.bitcoupon.singletons.UserData;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPage2 extends Fragment {

    public static final String FRAG_TWO = "com.project.bitcoupon.bitcoupon.frag.two";
    private  int couponId;
    String email;
    public FragmentPage2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        email = UserData.getInstance().getEmail();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_page2, container, false);
        Bundle arguments = getArguments();
        int position = arguments.getInt(FRAG_TWO);

        //For picture

        String name = arguments.getString("name");
        String price = arguments.getString("price");
        couponId= arguments.getInt("couponId");
        String description = arguments.getString("description");

        TextView mName = (TextView) v.findViewById(R.id.textview_singleCouponName);
        mName.setText(name);

        TextView mDescription = (TextView) v.findViewById(R.id.textview_singleCouponDescription);
        mDescription.setText(description);


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
