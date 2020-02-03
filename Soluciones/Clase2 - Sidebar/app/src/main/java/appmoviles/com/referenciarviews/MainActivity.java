package appmoviles.com.referenciarviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameET, passwordET;
    private Button signinBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.nameET);
        passwordET = findViewById(R.id.passwordET);
        signinBTN = findViewById(R.id.signinBTN);

        signinBTN.setOnClickListener(
                (v) -> {
                    String name = nameET.getText().toString();
                    String password = passwordET.getText().toString();
                    Toast.makeText(this, name+":"+password, Toast.LENGTH_LONG ).show();
                    Intent i = new Intent(this, TouchActivity.class);
                    startActivity(i);
                }
        );

    }
}
