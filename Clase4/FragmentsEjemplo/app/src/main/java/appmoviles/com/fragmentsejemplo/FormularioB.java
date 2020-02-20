package appmoviles.com.fragmentsejemplo;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioB extends Fragment implements View.OnClickListener{

    private OnDataSubmitted listener;
    private TextView nameField, cityField, careerField;
    private EditText codeField, phoneField, emailField;

    public FormularioB() {
        // Required empty public constructor
    }

    public void setListener(OnDataSubmitted listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_formulario_b, container, false);
        nameField = view.findViewById(R.id.nameField);
        cityField = view.findViewById(R.id.cityField);
        careerField = view.findViewById(R.id.careerField);
        codeField = view.findViewById(R.id.codeField);
        phoneField = view.findViewById(R.id.phoneField);
        emailField = view.findViewById(R.id.emailField);

        view.findViewById(R.id.nextButton).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nextButton:
                if(listener != null) {
                    listener.onData(this);
                }
                break;
        }
    }
}
