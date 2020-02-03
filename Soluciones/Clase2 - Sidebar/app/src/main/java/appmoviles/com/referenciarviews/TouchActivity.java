package appmoviles.com.referenciarviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TouchActivity extends AppCompatActivity {

    private TextView box, console;
    private ConstraintLayout rootPanel;
    private LinearLayout sidebar;

    float Xini = 0 ;
    float Yini = 0 ;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        sidebar = findViewById(R.id.sidebar);
        rootPanel = findViewById(R.id.rootPanel);
        box = findViewById(R.id.box);
        console = findViewById(R.id.console);

        box.setOnTouchListener(
                (v, event)->{
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            console.setText("DOWN");
                            Xini = event.getX();
                            Yini = event.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            console.setText("MOVE");
                            box.setX(  box.getX() + event.getX() - Xini );
                            box.setY(  box.getY() + event.getY() - Yini );
                            break;
                        case MotionEvent.ACTION_UP:
                            console.setText("UP");
                            break;
                    }
                    console.append("\nX: "+event.getX());
                    console.append("\nY: "+event.getY());
                     return true;
                }
        );

        rootPanel.setOnTouchListener(
                (v,event)->{
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:

                            Xini = event.getX();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if(sidebar.getX()+(event.getX()-Xini)>0){
                                sidebar.setX(0);
                            }else {
                                sidebar.setX(sidebar.getX() + (event.getX() - Xini));
                            }
                            Xini = event.getX();
                            break;
                        case MotionEvent.ACTION_UP:
                            if(sidebar.getX()<-sidebar.getWidth()/2){
                                sidebar.animate().x(-sidebar.getWidth());
                            }else{
                                sidebar.animate().x(0);
                            }
                            break;
                    }
                    return true;
                }
        );

    }
}
