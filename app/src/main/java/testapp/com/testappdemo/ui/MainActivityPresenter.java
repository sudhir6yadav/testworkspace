package testapp.com.testappdemo.ui;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import testapp.com.testappdemo.models.MatrimonialModel;
import testapp.com.testappdemo.network.ApiInterfaces;
import testapp.com.testappdemo.network.RetrofitInstance;

public class MainActivityPresenter implements MainActivityPresenterInterface{

    MainActivityViewInterface mainActivityViewInterface;
    private String TAG = "MainActivityPresenter";

    public MainActivityPresenter(MainActivityViewInterface mainActivityViewInterface) {
        this.mainActivityViewInterface = mainActivityViewInterface;
    }

    @Override
    public void getMatrimonialDetails() {
        getObservable().subscribeWith(getObserver());
    }

    public Observable<MatrimonialModel> getObservable(){
        return RetrofitInstance.getRetrofit().create(ApiInterfaces.class)
                .getMatrimonialMatches("10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MatrimonialModel> getObserver(){
        return new DisposableObserver<MatrimonialModel>() {

            @Override
            public void onNext(MatrimonialModel matrimonialModel) {
                Log.d(TAG,"detail OnNext"+matrimonialModel.getResults().get(0).getEmail());
                mainActivityViewInterface.showgetMatrimonialDetails(matrimonialModel);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Error "+e);
                e.printStackTrace();
                mainActivityViewInterface.displayErrMsg("Error fetching Matrimonial Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                mainActivityViewInterface.hideProgressBar();
            }
        };
    }
}
