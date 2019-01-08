package testapp.com.testappdemo.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import testapp.com.testappdemo.R;
import testapp.com.testappdemo.adapters.MatrimonialMatchesListItemAdapter;
import testapp.com.testappdemo.db.DbHelper;
import testapp.com.testappdemo.models.MaterimonialDetailModel;
import testapp.com.testappdemo.models.MatrimonialModel;

public class MainActivity extends AppCompatActivity implements MainActivityViewInterface{

    @BindView(R.id.rvList)
    RecyclerView rvList;

    @BindView(R.id.rlProgressBar)
    RelativeLayout rlProgressBar;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @BindView(R.id.tvInternetConnection)
    TextView tvInternetConnection;

    MainActivityPresenter mainActivityPresenter;

    String TAG = "MainActivity";
    RecyclerView.Adapter adapter;


    private LinearLayoutManager linearLayoutManager;
    boolean isLoading=false;
    List<MaterimonialDetailModel> materimonialDetailModels=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initViews();

        mainActivityPresenter = new MainActivityPresenter(this,this);

        mainActivityPresenter.saveDataToDb(DbHelper.getDbInstance(this).showDetails());

    }

    private void initViews() {


        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                Log.d(TAG, "onScrolled: "+isLoading);
                if (!isLoading)
                {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0)
                    {
                        isLoading = true;

                        mainActivityPresenter.getMatrimonialDetails();
                        Log.d(TAG, "onScrolled: inside "+isLoading);
                    }

                }
            }
        });
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        rlProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        rlProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void showgetMatrimonialDetails(List<MaterimonialDetailModel> matrimonialModel) {

        if(matrimonialModel!=null) {
            materimonialDetailModels.addAll(matrimonialModel);
            Log.d(TAG,matrimonialModel.get(0).getEmail());

            if(adapter != null)
            {
                isLoading=false;
                adapter.notifyDataSetChanged();
            }
            else {
                adapter = new MatrimonialMatchesListItemAdapter(materimonialDetailModels, MainActivity.this);
                rvList.setAdapter(adapter);
                linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                rvList.setLayoutManager(linearLayoutManager);

            }
        }
        else
        {
            mainActivityPresenter.getMatrimonialDetails();
        }
    }

    @Override
    public void showToastMsg(String msg) {
        progressBar.setVisibility(View.GONE);
        tvInternetConnection.setText(msg);
    }
}
