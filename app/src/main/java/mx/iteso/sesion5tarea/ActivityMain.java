package mx.iteso.sesion5tarea;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {
    AutoCompleteTextView textView;
    EditText NombreAlumno, Telefono;
    Spinner Escolaridad;
    RadioButton Genero;
    AutoCompleteTextView LibroFavorito;
    CheckBox PracticaDeporte;


    public class Alumno  {
        String Informacion;
        EditText NombreAlumno, Telefono;
        Spinner Escolaridad;
        RadioButton Genero;
        AutoCompleteTextView LibroFavorito;
        CheckBox PracticaDeporte;

        public Alumno(EditText nombreAlumno, EditText telefono, Spinner escolaridad, RadioButton genero, AutoCompleteTextView libroFavorito, CheckBox practicaDeporte) {
            this.NombreAlumno=nombreAlumno;
            this.Telefono=telefono;
            this.Escolaridad=escolaridad;
            this.Genero=genero;
            this.LibroFavorito=libroFavorito;
            this.PracticaDeporte=practicaDeporte;

        }
        public Alumno(EditText nombreAlumno, EditText telefono, Spinner escolaridad, RadioButton genero, CheckBox practicaDeporte) {
            this.NombreAlumno=nombreAlumno;
            this.Telefono=telefono;
            this.Escolaridad=escolaridad;
            this.Genero=genero;
            this.PracticaDeporte=practicaDeporte;

        }

        public String data1() {
            Informacion = ("Nombre: " + NombreAlumno.getText() + "\n" +
                    "Telefono: " + Telefono.getText()+ "\n" +
                    "Escolaridad: " + Escolaridad.getSelectedItem().toString() + "\n" +
                    "Género: " + Genero.getText()+ "\n" +
                    "Practica Deporte: " + (PracticaDeporte.isChecked()?"Si":"No"));
            return Informacion;
        }
        public String data2() {
            Informacion = ("Nombre: " + NombreAlumno.getText() + "\n" +
                    "Telefono: " + Telefono.getText()+ "\n" +
                    "Escolaridad: " + Escolaridad.getSelectedItem().toString() + "\n" +
                    "Género: " + Genero.getText()+ "\n" +
                    "Libro Favorito: " + LibroFavorito.getText()+ "\n" +
                    "Practica Deporte: " + (PracticaDeporte.isChecked()?"Si":"No"));
            return Informacion;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.activity_main_autocomplete);

        String[]books= getResources().getStringArray(R.array.libros);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,books) ;
        textView.setAdapter(adapter);




        NombreAlumno = (EditText) findViewById(R.id.activity_main_nombre);
        Telefono = (EditText) findViewById(R.id.activity_main_telefono);
        Escolaridad = (Spinner) findViewById(R.id.activity_main_spinner);
        Genero = (RadioButton) findViewById(R.id.activity_main_fem);
        Genero.setChecked(true);
        LibroFavorito = (AutoCompleteTextView) findViewById(R.id.activity_main_autocomplete);
        PracticaDeporte = (CheckBox)findViewById(R.id.activity_main_checkbox);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(dataComplete()==true){
                if(LibroFavorito.getText().toString().isEmpty()){
                    Alumno alumno = new Alumno(NombreAlumno, Telefono, Escolaridad, Genero, PracticaDeporte);
                    Toast.makeText(getApplicationContext(), alumno.data1(), Toast.LENGTH_LONG).show();
                }else {
                    Alumno alumno = new Alumno(NombreAlumno, Telefono, Escolaridad, Genero, LibroFavorito, PracticaDeporte);
                    Toast.makeText(getApplicationContext(), alumno.data2(), Toast.LENGTH_LONG).show();
                }
                erase(NombreAlumno,Telefono,Escolaridad,Genero,LibroFavorito,PracticaDeporte);
        }
        return true;
    }

    public boolean dataComplete() {
        boolean flag=true;

        if (NombreAlumno.getText().toString().isEmpty()&& Telefono.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.data_D, Toast.LENGTH_SHORT).show();
            return false;
        } else if (NombreAlumno.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.data_A, Toast.LENGTH_SHORT).show();
            flag=false;
        } else if (Telefono.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.data_B, Toast.LENGTH_SHORT).show();
            flag = false;
        }
         if(!flag)
            return false;
        return true;
    }




    public void onButtonClicked(View view){
        if(view.getId()==R.id.activity_main_clean) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Atención");
            builder.setMessage("¿Seguro que desea limpiar la pantalla?");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    erase( NombreAlumno,Telefono,Escolaridad,Genero,LibroFavorito,PracticaDeporte);
                    }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
             builder.create();
             builder.show();
        }
    }

    public void erase (EditText Nom, EditText Tel, Spinner Esc, RadioButton Gen, AutoCompleteTextView Lib, CheckBox Dep){
            this.NombreAlumno.setText("");
            this.Telefono.setText("");
            this.Escolaridad.setSelection(0);
            this.Genero = (RadioButton)findViewById(R.id.activity_main_fem);
            this.Genero.setChecked(true);
            this.LibroFavorito.setText("");
            this.PracticaDeporte.setChecked(false);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.activity_main_fem:
                if (checked)
                    Genero=(RadioButton)findViewById(R.id.activity_main_fem);
                    break;
            case R.id.activity_main_masc:
                if (checked)
                    Genero=(RadioButton)findViewById(R.id.activity_main_masc);
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.activity_main_checkbox:
                if (checked)
                    break;

        }
    }



}

