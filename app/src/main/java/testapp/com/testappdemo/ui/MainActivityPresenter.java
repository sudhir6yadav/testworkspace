package testapp.com.testappdemo.ui;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import testapp.com.testappdemo.db.DbHelper;
import testapp.com.testappdemo.models.MaterimonialDetailModel;
import testapp.com.testappdemo.models.MatrimonialModel;
import testapp.com.testappdemo.network.ApiInterfaces;
import testapp.com.testappdemo.network.RetrofitInstance;
import testapp.com.testappdemo.util.InternetConnection;

public class MainActivityPresenter implements MainActivityPresenterInterface{

    MainActivityViewInterface mainActivityViewInterface;
    private String TAG = "MainActivityPresenter";
    Context context;

    public MainActivityPresenter(MainActivityViewInterface mainActivityViewInterface, Context context) {
        this.mainActivityViewInterface = mainActivityViewInterface;
        this.context=context;
    }

    @Override
    public void getMatrimonialDetails() {

        ///check internet connectivity
        if(InternetConnection.checkConnection(context)) {
            mainActivityViewInterface.showProgressBar();
            getObservable().subscribeWith(getObserver());
        }
        else
        {
            mainActivityViewInterface.showToastMsg("Please Connect to internet");
        }
    }

    @Override
    public void saveDataToDb(List<MaterimonialDetailModel> matrimonialModels) {

        mainActivityViewInterface.showgetMatrimonialDetails(matrimonialModels);

    }

    //call api default param valuew is 10
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

                List<MaterimonialDetailModel> materimonialDetailModels=new ArrayList<>();
                for (int i=0;i<matrimonialModel.getResults().size();i++)
                {
                    MaterimonialDetailModel materimonialDetailModel=new MaterimonialDetailModel();
                    materimonialDetailModel.setFirstname(matrimonialModel.getResults().get(i).getName().getTitle());
                    materimonialDetailModel.setMiddlename(matrimonialModel.getResults().get(i).getName().getFirst());
                    materimonialDetailModel.setLastname(matrimonialModel.getResults().get(i).getName().getLast());
                    materimonialDetailModel.setAge(matrimonialModel.getResults().get(i).getDob().getAge());
                    materimonialDetailModel.setCellno(matrimonialModel.getResults().get(i).getCell());
                    materimonialDetailModel.setPhoneno(matrimonialModel.getResults().get(i).getPhone());
                    materimonialDetailModel.setCity(matrimonialModel.getResults().get(i).getLocation().getCity());
                    materimonialDetailModel.setState(matrimonialModel.getResults().get(i).getLocation().getState());
                    materimonialDetailModel.setEmail(matrimonialModel.getResults().get(i).getEmail());
                    materimonialDetailModel.setStreet(matrimonialModel.getResults().get(i).getLocation().getStreet());
                    materimonialDetailModel.setGender(matrimonialModel.getResults().get(i).getGender());
                    materimonialDetailModel.setPicture(matrimonialModel.getResults().get(i).getPicture().getMedium());
                    materimonialDetailModels.add(materimonialDetailModel);
                }

                saveDataToDb(materimonialDetailModels);

                ///save data to db
                DbHelper.getDbInstance(context).addDetail(materimonialDetailModels);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Error "+e);
                e.printStackTrace();
                mainActivityViewInterface.showToastMsg("Error to fetching live Data");

                saveDataToDb(DbHelper.getDbInstance(context).showDetails());
                mainActivityViewInterface.hideProgressBar();
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                mainActivityViewInterface.hideProgressBar();
            }
        };
    }
}
