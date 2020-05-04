package appmoviles.com.chatandroid.services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONObject;
import appmoviles.com.chatandroid.model.Message;
import appmoviles.com.chatandroid.util.NotificationUtils;


public class FCMService extends FirebaseMessagingService {


    public static final String API_KEY = "AAAAjo_3vA4:APA91bE-69eVH7odJplYUvZFv4mfqKGUqRXCqk8GSVc2RrqQUZ8l0sGF9t5ljeYhsV5HL3aQdVDNQlzFm3GYSVBVFeVZ4ASsWD_57dzAkT0evd3rCo1OIZqd2tRiqyaOdvoQCMZq6DUm";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(">>>",""+remoteMessage.getData());

        JSONObject object = new JSONObject(remoteMessage.getData());
        Gson gson = new Gson();
        Message message = gson.fromJson(object.toString(), Message.class);

        NotificationUtils.createNotification(this, message.getBody());


    }
}
