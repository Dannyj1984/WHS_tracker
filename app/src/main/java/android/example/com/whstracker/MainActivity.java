package android.example.com.whstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();


//Global variables

    TextView newHcp;
    Button enterBtn;
    public static Float handicap; // global variable
    int hcpInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "Handicap is " + handicap);



        //Declare views
        enterBtn = findViewById(R.id.inputNew);
        newHcp = findViewById(R.id.currentHcp);




        display();

    }

    public void display() {
        try {
            hcpInt = Math.round(handicap);
            newHcp.setText(Float.toString(hcpInt));
        } catch (Exception e) {
            Log.d(LOG_TAG, "error" + e);
        }

    }

    public void enterNew(View view) {

        Intent intent = new Intent(this, Calculator.class);
        startActivity(intent);


    }
}
