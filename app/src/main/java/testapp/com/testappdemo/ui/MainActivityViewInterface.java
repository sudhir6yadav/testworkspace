package testapp.com.testappdemo.ui;

import testapp.com.testappdemo.models.MatrimonialModel;

public interface MainActivityViewInterface {

    void showToast(String s);
    void showProgressBar();
    void hideProgressBar();
    void showgetMatrimonialDetails(MatrimonialModel matrimonialModel);
    void displayErrMsg(String msg);

}
