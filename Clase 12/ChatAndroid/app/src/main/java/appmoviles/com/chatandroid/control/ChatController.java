package appmoviles.com.chatandroid.control;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.io.File;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import appmoviles.com.chatandroid.R;
import appmoviles.com.chatandroid.model.FCMMessage;
import appmoviles.com.chatandroid.model.Message;
import appmoviles.com.chatandroid.model.User;
import appmoviles.com.chatandroid.services.FCMService;
import appmoviles.com.chatandroid.util.HTTPSWebUtilDomi;
import appmoviles.com.chatandroid.util.NotificationUtils;
import appmoviles.com.chatandroid.view.ChatActivity;

import static android.app.Activity.RESULT_OK;

public class ChatController implements View.OnClickListener{

    private static final int GALLERY_CALLBACK = 1;

    private ChatActivity activity;

    private String username;
    private String chatroom;
    private User user;
    private MessagesAdapter adapter;

    private Uri tempUri;

    public ChatController(final ChatActivity activity) {
        this.activity = activity;


        adapter = new MessagesAdapter();
        activity.getMessagesList().setAdapter(adapter);


        username = activity.getIntent().getExtras().getString("username");
        chatroom = activity.getIntent().getExtras().getString("chatroom");

        activity.getSendBtn().setOnClickListener(this);
        activity.getGalBtn().setOnClickListener(this);



        Query queryBusqueda = FirebaseDatabase.getInstance().getReference()
                .child("user").orderByChild("username").equalTo(username);


        queryBusqueda.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Ninguna coincidencia
                if(dataSnapshot.getChildrenCount() == 0){
                    String pushid = FirebaseDatabase.getInstance().getReference().child("user").push().getKey();
                    user = new User(pushid, username);
                    FirebaseDatabase.getInstance().getReference().child("user").child(pushid).setValue(user);
                }else {
                    for (DataSnapshot coincidencia : dataSnapshot.getChildren()) {
                        user = coincidencia.getValue(User.class);

                        break;
                    }
                    Log.e(">>>BUSQUEDA", user.getId() + ":" + user.getUsername());
                }

                adapter.setUserID(user.getId());
                activity.getUsernameTV().setText(user.getUsername());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //Si es una lista
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("chats").child(chatroom).limitToLast(10);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                adapter.addMessage(message);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendBtn:
                String body = activity.getMessageET().getText().toString();

                String pushId = FirebaseDatabase.getInstance().getReference()
                        .child("chats").child(chatroom).push().getKey();

                Message message = new Message(
                        pushId,
                        body,
                        user.getId(),
                        Calendar.getInstance().getTime().getTime());


                FCMMessage fcm = new FCMMessage();
                fcm.setTo("/topics/"+chatroom);
                fcm.setData(message);
                Gson gson = new Gson();
                String json = gson.toJson(fcm);

                new Thread(
                        ()->{
                            HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                            utilDomi.POSTtoFCM(FCMService.API_KEY, json);
                        }
                ).start();
                break;

            case R.id.galBtn:
                NotificationUtils.createNotification(activity,"Hola mundo de notificaciones");
                break;

        }
    }

    public void beforePause() {
        //Suscribirme a un topic
        FirebaseMessaging.getInstance().subscribeToTopic(chatroom).addOnCompleteListener(
                task->{
                    if(task.isSuccessful()){
                        Log.e(">>>","Suscrito!");
                    }
                }
        );
    }

    public void beforeResume() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(chatroom);
    }

    
}
