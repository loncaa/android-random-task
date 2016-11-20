package hr.magicpot.task.userinterface;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.magicpot.task.R;
import hr.magicpot.task.presentation.MainPresenter;
import hr.magicpot.task.presentation.MainPresenterImpl;
import hr.magicpot.task.storage.db.DBHelper;

public class MainActivity extends OrmLiteBaseActivity<DBHelper> implements MainView {

    @BindView(R.id.url) EditText url;
    @BindView(R.id.button) Button button;
    @BindView(R.id.progress) ProgressBar progressBar;

    MainPresenter presenter;
    private static Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenterImpl(this, getHelper());
        c = getApplicationContext();
    }

    @OnClick(R.id.button)
    public void onclick(View v){
        presenter.processUrl(url.getText().toString());
    }

    @Override
    public void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static SharedPreferences getSPreferences(String name, int m){
        return c != null ? c.getSharedPreferences(name, m) : null;
    }



}
