package me.gustavozapata.today;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textTheme;

    TextView createNewTask;
    TextView createNewList;

    //POPUP
    ConstraintLayout popup;
    ConstraintLayout layoutSelectCategories;
    TextView selectCategory;
    TextView newTask;
    EditText inputTask;
    TextView cancelPopup;
    TextView createPopup;

    ArrayList<TextView> categories = new ArrayList<>();
    TextView optionHome;
    TextView optionWork;
    TextView optionStudy;
    TextView optionPersonal;

    //FOOTER
    TextView textFooter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

        //##### LINK XML TO JAVA #####
        textTheme = findViewById(R.id.text_theme);

        createNewTask = findViewById(R.id.text_createNewTask);
        createNewList = findViewById(R.id.text_createNewList);

        popup = findViewById(R.id.popup);
        layoutSelectCategories = findViewById(R.id.layout_categories);
        selectCategory = findViewById(R.id.text_selectCategory);
        newTask = findViewById(R.id.text_NewTask);
        inputTask = findViewById(R.id.input_Task);
        createPopup = findViewById(R.id.text_Create);
        cancelPopup = findViewById(R.id.text_Cancel);
        optionHome = findViewById(R.id.option_home);
        optionWork = findViewById(R.id.option_work);
        optionStudy = findViewById(R.id.option_study);
        optionPersonal = findViewById(R.id.option_personal);

        textFooter = findViewById(R.id.text_footer);

        categories.add(optionHome);
        categories.add(optionWork);
        categories.add(optionStudy);
        categories.add(optionPersonal);


        //##### EVENTS #####
        textTheme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestingActivity.class));
            }
        });

        createNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(getString(R.string.NewTask));
                inputTask.setHint(R.string.taskDecription);
                layoutSelectCategories.setVisibility(View.VISIBLE);
                selectCategory.setVisibility(View.VISIBLE);
                cleanSelection();
            }
        });
        createNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(getString(R.string.NewList));
                inputTask.setHint(R.string.listDecription);
                layoutSelectCategories.setVisibility(View.INVISIBLE);
                selectCategory.setVisibility(View.INVISIBLE);
            }
        });

        cancelPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.setVisibility(View.INVISIBLE);
            }
        });
        createPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                createItem();
            }
        });

        optionHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory(optionHome);
            }
        });
        optionWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory(optionWork);
            }
        });
        optionStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory(optionStudy);
            }
        });
        optionPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory(optionPersonal);
            }
        });

        textFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, AirbnbActivity.class));
            }
        });
    }

    public void createItem() {
        if(inputTask.getText().toString().equals("")){
            Toast.makeText(this, "A description is required", Toast.LENGTH_SHORT).show();
        } else {
            popup.setVisibility(View.INVISIBLE);
        }
    }

    public void selectCategory(TextView option) {
        cleanSelection();
        option.setBackgroundResource(R.drawable.selected);
        option.setTextColor(Color.BLACK);
    }

    public void cleanSelection() {
        for (TextView tv : categories) {
            tv.setBackground(null);
            tv.setTextColor(getResources().getColor(R.color.lila));
        }
    }

    public void showPopup(String title) {
        newTask.setText(title);
        popup.setVisibility(View.VISIBLE);
        inputTask.setText("");
//        inputTask.requestFocus();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
