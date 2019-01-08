package testapp.com.testappdemo.ui;

import java.util.List;

import testapp.com.testappdemo.models.MaterimonialDetailModel;

public interface MainActivityViewInterface {

    void showProgressBar();
    void hideProgressBar();
    void showgetMatrimonialDetails(List<MaterimonialDetailModel> matrimonialModel);
    void showToastMsg(String msg);

}
