package com.project.bitcoupon.bitcoupon.controllers;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.project.bitcoupon.bitcoupon.R;
import com.project.bitcoupon.bitcoupon.controllers.FragmentsForCoupons.FragmentPage1;
import com.project.bitcoupon.bitcoupon.controllers.FragmentsForCoupons.FragmentPage2;
import com.project.bitcoupon.bitcoupon.controllers.FragmentsForCoupons.FragmentPage3;
import com.project.bitcoupon.bitcoupon.controllers.FragmentsForCoupons.FragmentPage4;

public class SingleCouponActivity extends BaseActivity {

    private static final String TAG = "SingleCouponActivity_Tag";

    private int couponId;
    private String name;
    private String price;
    private String expiration;
    private String imgPath;
    private String categoryName;
    private String description;
    private String remark;
    private String seller;
    private String minOrder;
    private String maxOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_coupon);

        Intent it = getIntent();

        couponId = it.getIntExtra("couponId", 0);
        name = it.getStringExtra("name");
        price = it.getStringExtra("price");
        expiration = it.getStringExtra("expiration");
        imgPath = it.getStringExtra("picture");
        categoryName = it.getStringExtra("categoryName");
        description = it.getStringExtra("description");
        remark = it.getStringExtra("remark");
        seller = it.getStringExtra("seller");
        minOrder = it.getStringExtra("minOrder");
        maxOrder = it.getStringExtra("maxOrder");


        CouponViewAdapter adapter = new CouponViewAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

    }

    private class CouponViewAdapter extends FragmentStatePagerAdapter {

        public CouponViewAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Method that send information to each fragment
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            Fragment current;
            if (position == 0) {
                current = new FragmentPage1();
                Bundle arguments = new Bundle();
                arguments.putInt(FragmentPage1.FRAG_ONE, position);
                arguments.putString("name", name);
                arguments.putString("categoryName", categoryName);
                arguments.putString("picture", imgPath);
                arguments.putString("price", price);
                arguments.putInt("couponId", couponId);
                current.setArguments(arguments);
                return current;
            } else if (position == 1) {
                current = new FragmentPage2();
                Bundle arguments = new Bundle();
                arguments.putInt(FragmentPage2.FRAG_TWO, position);
                arguments.putString("description", description);
                arguments.putString("name", name);
                arguments.putString("price", price);
                arguments.putInt("couponId", couponId);
                current.setArguments(arguments);
                return current;
            } else if (position == 2) {
                current = new FragmentPage3();
                Bundle arguments = new Bundle();
                arguments.putInt(FragmentPage3.FRAG_THREE, position);
                arguments.putString("name", name);
                arguments.putString("remark", remark);
                arguments.putString("price", price);
                arguments.putString("picture", imgPath);
                arguments.putInt("couponId", couponId);
                current.setArguments(arguments);
                return current;
            } else {
                current = new FragmentPage4();
                Bundle arguments = new Bundle();
                arguments.putInt(FragmentPage4.FRAG_FOUR, position);
                arguments.putString("name", name);
                arguments.putInt("couponId", couponId);
                arguments.putString("seller", seller);
                arguments.putString("price", price);
                arguments.putString("categoryName", categoryName);
                arguments.putString("expiration", expiration);
                arguments.putString("minOrder", minOrder);
                arguments.putString("maxOrder", maxOrder);
                current.setArguments(arguments);
                return current;
            }
        }

        /**
         * Method for fragment count
         * @return
         */
        @Override
        public int getCount() {
            return 4;
        }

        /**
         * For each fragment set title
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return getString(R.string.coupon_offer);
            } else if (position == 1) {
                return getString(R.string.description);
            } else if (position == 2) {
                return getString(R.string.remark);
            } else {
                return getString(R.string.detailed_description);
            }
        }
    }
}
