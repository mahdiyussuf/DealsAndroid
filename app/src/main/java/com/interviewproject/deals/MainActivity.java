package com.interviewproject.deals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.interviewproject.deals.Model.Deals;
import com.interviewproject.deals.Model.RootObject;
import com.interviewproject.deals.ViewModel.DealsViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements DealListAdapter.OnDealClickListener {

    DealsViewModel viewModel;
    RecyclerView mDealsRecycler;
    LinearLayout mLoadingAnimation;
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    List<Deals> mDeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new DealsViewModel();

        mLoadingAnimation = findViewById(R.id.loading_animation_container);
        mDealsRecycler = findViewById(R.id.recycler_view_container);
        mDealsRecycler.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    private void getData() {
        mCompositeDisposable.add(viewModel.dealsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootObject>() {
                    @Override
                    public void accept(RootObject rootObject) throws Exception {
                        displayData(rootObject);
                    }
                }));
    }

    private void displayData(RootObject rootObject) {
        mDeals = rootObject.getData();
        mDeals = viewModel.reformatImageURLs(mDeals);
        DealListAdapter dealListAdapter = new DealListAdapter(this, mDeals, this);
        mDealsRecycler.setAdapter(dealListAdapter);
        mLoadingAnimation.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        mCompositeDisposable.clear();
        super.onStop();
    }

    /**
     * When a deal on the list is clicked, the deals detail activity will be launched with the
     * deal's relevant data
     * @param position : position of deal in the list
     */
    @Override
    public void onDealClick(int position) {
        Bundle detailInformation = new Bundle();
        Intent intent = new Intent(this, DealsDetailActivity.class);
        if (mDeals.get(position) != null) {
            detailInformation.putString("IMAGE", mDeals.get(position).getImage());
            detailInformation.putString("SALE_PRICE", mDeals.get(position).getSalePrice());
            detailInformation.putString("PRICE", mDeals.get(position).getPrice());
            detailInformation.putString("TITLE", mDeals.get(position).getTitle());
            detailInformation.putString("DESCRIPTION", mDeals.get(position).getDescription());
        }
        intent.putExtras(detailInformation);
        startActivity(intent);
    }
}
