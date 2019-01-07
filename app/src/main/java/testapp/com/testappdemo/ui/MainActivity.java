package testapp.com.testappdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import testapp.com.testappdemo.R;
import testapp.com.testappdemo.models.MatrimonialModel;

public class MainActivity extends AppCompatActivity implements MainActivityViewInterface{

    @BindView(R.id.rvList)
    RecyclerView rvList;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    MainActivityPresenter mainActivityPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initViews();

        mainActivityPresenter = new MainActivityPresenter(this);
        mainActivityPresenter.getMatrimonialDetails();

    }

    private void initViews() {
        //setSupportActionBar(toolbar);
    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showgetMatrimonialDetails(MatrimonialModel matrimonialModel) {

    }

    @Override
    public void displayErrMsg(String msg) {

    }
}
