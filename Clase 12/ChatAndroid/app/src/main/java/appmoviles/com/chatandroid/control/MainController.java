package appmoviles.com.chatandroid.control;

import android.Manifest;
import android.content.Intent;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import androidx.core.app.ActivityCompat;
import appmoviles.com.chatandroid.R;
import appmoviles.com.chatandroid.model.User;
import appmoviles.com.chatandroid.view.ChatActivity;
import appmoviles.com.chatandroid.view.MainActivity;

public class MainController implements View.OnClickListener{

    private MainActivity activity;

    public MainController(MainActivity activity) {
        this.activity = activity;

        activity.getSigninBtn().setOnClickListener(this);

        ActivityCompat.requestPermissions(activity, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        }, 0);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signinBtn:
                String chatroom = activity.getChatroomET().getText().toString();
                String username = activity.getUsernameET().getText().toString();

                Intent intent = new Intent(activity, ChatActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("chatroom", chatroom);
                activity.startActivity(intent);
                activity.finish();

                break;
        }
    }
}
