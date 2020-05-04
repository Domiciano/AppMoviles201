package appmoviles.com.chatandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import appmoviles.com.chatandroid.R;
import appmoviles.com.chatandroid.control.MainController;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText chatroomET, usernameET;
    private Button signinBtn;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatroomET = findViewById(R.id.chatroomET);
        usernameET = findViewById(R.id.usernameET);
        signinBtn = findViewById(R.id.signinBtn);

        controller = new MainController(this);
    }

    public EditText getChatroomET() {
        return chatroomET;
    }

    public EditText getUsernameET() {
        return usernameET;
    }

    public Button getSigninBtn() {
        return signinBtn;
    }
}
