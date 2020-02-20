package appmoviles.com.fragmentsejemplo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnDataSubmitted{



    private TextView mainTitle;
    private LinearLayout fragmentContainer;
    private ProgressBar progressBar;

    private Fragment formularioA, formularioB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTitle = findViewById(R.id.mainTitle);
        fragmentContainer = findViewById(R.id.fragmentContainer);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(2);
        progressBar.setProgress(0);

        formularioA = new FormularioA();
        formularioB = new FormularioB();
        ((FormularioA)formularioA).setListener(this);
        ((FormularioB)formularioB).setListener(this);

        loadFragment(formularioA);
    }

    public void loadFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    @Override
    public void onData(Fragment fragment, String... args) {
        if(fragment.equals(formularioA)){
            loadFragment(formularioB);
            progressBar.setProgress(1);
        }else if(fragment.equals(formularioB)){
            progressBar.setProgress(2);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(formularioB);
            transaction.commit();
        }
    }
}
