package android.example.com.whstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class Calculator extends AppCompatActivity {

    //Tag for logcat testing
    private static final String LOG_TAG = Calculator.class.getSimpleName();
    //constant var for master ArrayList
    private static final String mList = "masterList";

    Button calculateBtn;
    EditText newScoreField;
    float currentHcp;
    int currentScore;
    ArrayList<Integer> masterList = new ArrayList<>();
    ArrayList<Integer> sortedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_score);

        loadData();
        Log.d(LOG_TAG, "Array size is " + masterList.size() );


        calculateBtn = findViewById(R.id.calculate);
        newScoreField = findViewById(R.id.newScore);


    }

    public void calculate(View view) {

        assignScores();
        saveData();

        Log.d(LOG_TAG, "current handicap is " + currentHcp);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("message", currentHcp);

        startActivity(intent);
    }


    public void assignScores() {

        currentScore = Integer.parseInt(newScoreField.getText().toString());

        masterList.add(0, currentScore);

        if(20 <= masterList.size()) {

            sortedList = new ArrayList<>();

            for(int i = 19; i >-1; i--){

                sortedList.add(masterList.get(i));
            }
            Collections.sort(sortedList);
            int total = sortedList.get(0) + sortedList.get(1) + sortedList.get(2) + sortedList.get(3) + sortedList.get(4) + sortedList.get(5) + sortedList.get(6) + sortedList.get(7);
            Log.d(LOG_TAG, "total is " + total);
            MainActivity.handicap = total / 8f;
        } else {
            Toast.makeText(this, "Need 20 scores to calculate handicap", Toast.LENGTH_SHORT).show();
        }



    }
        //Save ArrayList using gson to be able to load again when activity restarts
        private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(masterList);
            editor.putString(mList, json);
            editor.apply();
        }

        //Load ArrayList
        private void loadData() {
            SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(mList, null);
            Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
            masterList = gson.fromJson(json, type);

            if(masterList == null) {
                masterList = new ArrayList<>();
            }
        }


    public void remove(View view) {

        masterList.remove(0);
    }
}








