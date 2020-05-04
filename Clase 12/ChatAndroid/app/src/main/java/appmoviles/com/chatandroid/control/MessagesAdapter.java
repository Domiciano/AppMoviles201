package appmoviles.com.chatandroid.control;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import appmoviles.com.chatandroid.R;
import appmoviles.com.chatandroid.model.Message;
import appmoviles.com.chatandroid.util.HTTPSWebUtilDomi;

public class MessagesAdapter extends BaseAdapter {


    private ArrayList<Message> messages;
    private String userID = "";

    public MessagesAdapter() {
        messages = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View root;

        if (userID.equals(messages.get(i).getUserId())) {
            root = inflater.inflate(R.layout.messages_row_mine, null);
        }else{
            root = inflater.inflate(R.layout.messages_row_others, null);
        }

        TextView messageRow = root.findViewById(R.id.message_row);
        messageRow.setText(messages.get(i).getBody());


        return root;
    }

    public void addMessage(Message message) {
        messages.add(message);
        notifyDataSetChanged();
    }

    public void setUserID(String userID) {
        this.userID = userID;
        notifyDataSetChanged();
    }
}
