package com.interviewproject.deals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Activity for displaying the detail view of the product deal clicked from the list
 */
public class DealsDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private String mPrice, mSalePrice, mDescription, mImage, mTitle;
    private TextView mPriceTextView, mSalePriceTextView, mDescriptionTextView, mTitleTextView, mRegTextView;
    private ImageView mDealImageView;
    private Button mAddToCartButton, mAddToListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals_detail);

        mDealImageView = findViewById(R.id.deals_detail_image);
        mPriceTextView = findViewById(R.id.old_price_detail);
        mSalePriceTextView = findViewById(R.id.new_price_detail);
        mTitleTextView = findViewById(R.id.title_detail);
        mDescriptionTextView = findViewById(R.id.description_detail);
        mAddToCartButton = findViewById(R.id.button_add_to_cart);
        mAddToListButton = findViewById(R.id.button_add_to_list);
        mRegTextView = findViewById(R.id.reg_textView);

        mAddToListButton.setOnClickListener(this);
        mAddToCartButton.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle detailInformation = intent.getExtras();
        if(detailInformation != null) {
            mImage = detailInformation.getString("IMAGE");
            mSalePrice = detailInformation.getString("SALE_PRICE");
            mPrice = detailInformation.getString("PRICE");
            mTitle = detailInformation.getString("TITLE");
            mDescription = detailInformation.getString("DESCRIPTION");

            initializeViews();
        }
    }

    private void initializeViews() {
        Glide.with(this)
                .asBitmap()
                .load(mImage)
                .into(mDealImageView);

        mPriceTextView.setText(mPrice);
        mPriceTextView.setPaintFlags(mPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mSalePriceTextView.setText(mSalePrice);
        mTitleTextView.setText(mTitle);
        mDescriptionTextView.setText(mDescription);

        if(mSalePrice == null) {
            mSalePriceTextView.setText(mPrice);
            mPriceTextView.setText("");
            mRegTextView.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_to_cart:
                Toast.makeText(this, "Added To Cart!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button_add_to_list:
                Toast.makeText(this, "Added To List!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
