package hr.magicpot.task.presentation;

import hr.magicpot.task.storage.db.DBHelper;
import hr.magicpot.task.storage.db.interactor.DatabaseInteractor;
import hr.magicpot.task.storage.db.interactor.DatabaseInteractorImpl;
import hr.magicpot.task.network.InternetInteractor;
import hr.magicpot.task.network.InternetInteractorImpl;
import hr.magicpot.task.storage.db.model.DBModel;
import hr.magicpot.task.userinterface.MainView;
import hr.magicpot.task.storage.preference.SharedPreferenceInteratorImpl;
import hr.magicpot.task.storage.preference.SharedPreferencesInteractor;

/**
 * Created by xxx on 19.11.2016..
 */

public class MainPresenterImpl implements MainPresenter, InternetInteractor.onWebDataListener, DatabaseInteractor.onDatabaseCheckListener, SharedPreferencesInteractor.onSharedPreferencesListener {
    private MainView mainView;
    private InternetInteractor internetInteractor;
    private DatabaseInteractor databaseInteractor;
    private SharedPreferencesInteractor sharedPreferencesInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.internetInteractor = new InternetInteractorImpl();
        this.databaseInteractor = new DatabaseInteractorImpl(this);
        this.sharedPreferencesInteractor = new SharedPreferenceInteratorImpl();
    }

    /*Mainpresenter*/
    @Override
    public void processUrl(String url) {
        showProgressbar();
        databaseInteractor.checkDatabase(url);
    }

    /*Listeners*/
    @Override
    public void onDBResponse(boolean response, String url, long hascode) {
        if(!response){
            showMessage("Url vec postoji u bazi.\n" + url + " " + hascode );
            mainView.hideProgressbar();
            mainView.disableButtonFiveSec();
        }else {
            sharedPreferencesInteractor.check(url, this);
        }
    }

    @Override
    public void onSPResponse(boolean response, String url, long hascode) {
        if(response){
            showMessage("Url vec postoji u shared preferencu.\n" + url + " " + hascode);
            mainView.disableButtonFiveSec();
            mainView.hideProgressbar();
        }else{
            internetInteractor.getHtmlContent(url, this);
        }
    }

    @Override
    public void onWDResponse(String htmlResponse, String url) {
        mainView.hideProgressbar();

        long hashCode = htmlResponse.hashCode();
        DBModel model = new DBModel(url, htmlResponse.hashCode());

        if(hashCode%2 == 0)
            databaseInteractor.store(model);
        else
            sharedPreferencesInteractor.storeHashcode(url, hashCode, this);
    }

    @Override
    public void onDBSucess(String url, long hascode) {
        showMessage("Uspjesno spremanje u bazu.\n" + url + " " + hascode);
    }

    @Override
    public void onSPStoreSuccess(String url, long hashcode) {
        showMessage("Uspjesno spremanje u Shared preferences.\n" + url + " " + hashcode);
    }

    @Override
    public void onWDError(String msg) {
        mainView.hideProgressbar();
        mainView.showMessage(msg);
    }

    @Override
    public void onDBError(String msg){
        mainView.showMessage(msg);
        mainView.hideProgressbar();
    }

    private void showMessage(String msg){
        mainView.showMessage(msg);
    }

    private void showProgressbar() {
        mainView.showProgressbar();
    }

}
