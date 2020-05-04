package appmoviles.com.chatandroid.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import appmoviles.com.chatandroid.R;
import appmoviles.com.chatandroid.control.ChatController;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {


    private TextView usernameTV;
    private ListView messagesList;
    private EditText messageET;
    private Button galBtn, sendBtn;
    private ImageView messageIV;
    private ConstraintLayout controlsContainer;
    private ChatController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        usernameTV = findViewById(R.id.usernameTV);
        messagesList = findViewById(R.id.messagesList);
        messageET = findViewById(R.id.messageET);
        galBtn = findViewById(R.id.galBtn);
        sendBtn = findViewById(R.id.sendBtn);
        messageIV = findViewById(R.id.messageIV);
        controlsContainer = findViewById(R.id.controlsContainer);

        controller = new ChatController(this);
    }

    public TextView getUsernameTV() {
        return usernameTV;
    }

    public ListView getMessagesList() {
        return messagesList;
    }

    public EditText getMessageET() {
        return messageET;
    }

    public Button getGalBtn() {
        return galBtn;
    }

    public Button getSendBtn() {
        return sendBtn;
    }

    public ImageView getMessageIV() {
        return messageIV;
    }

    public ConstraintLayout getControlsContainer() {
        return controlsContainer;
    }


    //Mi actividad pierde el primer plano
    @Override
    protected void onPause() {
        controller.beforePause();
        super.onPause();
    }


    //Mi actividad recupera el primer plano
    @Override
    protected void onResume() {
        controller.beforeResume();
        super.onResume();
    }

}
