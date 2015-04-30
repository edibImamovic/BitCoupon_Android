package com.project.bitcoupon.bitcoupon.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.bitcoupon.bitcoupon.R;
import com.project.bitcoupon.bitcoupon.controllers.FragmentsForCoupons.FragmentPage1;
import com.project.bitcoupon.bitcoupon.controllers.FragmentsForCoupons.FragmentPage2;
import com.project.bitcoupon.bitcoupon.controllers.FragmentsForCoupons.FragmentPage3;
import com.squareup.picasso.Picasso;

public class SingleCouponActivity extends BaseActivity {

    private static final String TAG = "SingleCouponActivity_Tag";

    private String name;
    private String description;
    private String imgPath;
    private String price;

    private int couponId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_coupon);

        Intent it = getIntent();

        couponId = it.getIntExtra("couponId", 0);
        imgPath = it.getStringExtra("picture");
        name = it.getStringExtra("name");
        description = it.getStringExtra("description");
        price = it.getStringExtra("price");

        ProductViewAdapter adapter = new ProductViewAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

    }

    private class ProductViewAdapter extends FragmentStatePagerAdapter {

        public ProductViewAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment current;
            if(position==0){
                current = new FragmentPage1();
                Bundle arguments = new Bundle();
                arguments.putInt(FragmentPage1.FRAG_ONE, position);
                arguments.putString("name", name);
                arguments.putString("picture", imgPath);
                arguments.putInt("couponId", couponId);
                current.setArguments(arguments);
                return current;
            } else if(position==1){
                current = new FragmentPage2();
                Bundle arguments = new Bundle();
                arguments.putInt(FragmentPage2.FRAG_TWO, position);
                arguments.putString("description", description);
                arguments.putString("name", name);
                arguments.putString("price", price);
                arguments.putInt("couponId", couponId);
                current.setArguments(arguments);
                return current;
            } else{
                current = new FragmentPage3();
                Bundle arguments = new Bundle();
                arguments.putInt(FragmentPage3.FRAG_THREE, position);
                arguments.putString("name", name);
                arguments.putString("picture", imgPath);
                arguments.putInt("couponId", couponId);
                current.setArguments(arguments);

                return current;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}
