package testapp.com.testappdemo.ui;

import java.util.List;

import testapp.com.testappdemo.models.MaterimonialDetailModel;
import testapp.com.testappdemo.models.MatrimonialModel;

public interface MainActivityPresenterInterface {

    void getMatrimonialDetails();
    void saveDataToDb(List<MaterimonialDetailModel> matrimonialModels);
}
