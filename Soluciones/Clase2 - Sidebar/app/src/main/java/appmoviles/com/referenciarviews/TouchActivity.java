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
                            //Aquí se guarda la variable X en una global
                            Xini = event.getX();
                            //Se evalúa si el punto de contacto está en
                            // el 10% a la izquierda de la pantalla
                            // a lo ancho
                            if(Xini < 0.1f*console.getWidth()){
                                //Si retorno true, sigo con el gesto
                                return true;
                            }else{
                                //Retraemos el menú. La posición mínima es (-Ancho del menú)
                                //La posición máxima es (0)
                                sidebar.animate().x(-sidebar.getWidth());
                                //Si retorno false, interrumpo el gesto
                                return false;
                            }
                            //Recordar que GESTO es la sucesión de las acciones
                            //DOWN -> MOVE -> UP
                        case MotionEvent.ACTION_MOVE:
                            //Se evalúa si el menú se encuentra totalmente desplegado
                            if(sidebar.getX()+(event.getX()-Xini)>0){
                                //Si se encuentra totalmente desplegado se
                                //limita su movimiento para que no exceda su posición
                                //X máxima
                                sidebar.setX(0);
                            }else {
                                //Si no se encuentra totalmente desplegado, se mueve
                                //normalmente
                                sidebar.setX(sidebar.getX() + (event.getX() - Xini));
                            }
                            //Luego de cada movimiento, se actualiza la variable X
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
