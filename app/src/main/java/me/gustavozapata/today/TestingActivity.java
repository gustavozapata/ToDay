package me.gustavozapata.today;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TestingActivity extends AppCompatActivity {

    TextView textTheme;
    TextView textCancel;
    TextView newTask;
    ConstraintLayout popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        if (Build.VERSION.SDK_INT >= 21) {
            this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.ga_background));
        }

        textTheme = findViewById(R.id.text_theme);
        textCancel = findViewById(R.id.text_Cancel);
        newTask = findViewById(R.id.text_createNewTask);
        popup = findViewById(R.id.popup);

        textTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(TestingActivity.this, MainActivity.class));
            }
        });

        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                popup.setVisibility(View.VISIBLE);
            }
        });

        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                popup.setVisibility(View.INVISIBLE);
            }
        });
    }
}
