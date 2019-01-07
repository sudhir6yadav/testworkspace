package testapp.com.testappdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import testapp.com.testappdemo.R;
import testapp.com.testappdemo.adapters.MatrimonialMatchesListItemAdapter;
import testapp.com.testappdemo.models.MatrimonialModel;

public class MainActivity extends AppCompatActivity implements MainActivityViewInterface{

    @BindView(R.id.rvList)
    RecyclerView rvList;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    MainActivityPresenter mainActivityPresenter;

    String TAG = "MainActivity";
    RecyclerView.Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initViews();

        mainActivityPresenter = new MainActivityPresenter(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        mainActivityPresenter.getMatrimonialDetails();

    }

    private void initViews() {

        rvList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void showgetMatrimonialDetails(MatrimonialModel matrimonialModel) {

        if(matrimonialModel!=null) {
            Log.d(TAG,matrimonialModel.getResults().get(0).getEmail());
             adapter = new MatrimonialMatchesListItemAdapter(matrimonialModel.getResults(), MainActivity.this);
            rvList.setAdapter(adapter);
        }
    }

    @Override
    public void displayErrMsg(String msg) {

    }
}
