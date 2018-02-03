package mx.iteso.sesion5tarea;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by aceve on 02/02/2018.
 */

public class login extends AppCompatActivity {
    Button gotoNextActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gotoNextActivity=findViewById(R.id.login_button);
        gotoNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(login.this,ActivityMain.class);
                startActivity(myIntent);
            }
        });

    }
}
