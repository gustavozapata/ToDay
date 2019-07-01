package me.gustavozapata.today;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView textSettings;
    TextView createNewTask;
    TextView createNewList;
    TextView newTaskInfo;
    ConstraintLayout constraintLayoutTasks;

    //POPUP
    Dialog popup;
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

    //NEWLIST POPUP
    Dialog newlistPopup;
    EditText inputList;
    TextView cancelNewlistPopup;
    TextView createNewlistPopup;

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
        textSettings = findViewById(R.id.text_settings);
        createNewTask = findViewById(R.id.text_createNewTask);
        createNewList = findViewById(R.id.text_createNewList);
        newTaskInfo = findViewById(R.id.text_msgTasks);
        constraintLayoutTasks = findViewById(R.id.constraintLayoutTasks);


        //POPUP
        popup = new Dialog(this);
        popup.setContentView(R.layout.popup);
        Objects.requireNonNull(popup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        layoutSelectCategories = popup.findViewById(R.id.layout_categories);
        selectCategory = popup.findViewById(R.id.text_selectCategory);
        newTask = popup.findViewById(R.id.text_NewTask);
        inputTask = popup.findViewById(R.id.input_Task);
        createPopup = popup.findViewById(R.id.text_Create);
        cancelPopup = popup.findViewById(R.id.text_Cancel);
        optionHome = popup.findViewById(R.id.option_home);
        optionWork = popup.findViewById(R.id.option_work);
        optionStudy = popup.findViewById(R.id.option_study);
        optionPersonal = popup.findViewById(R.id.option_personal);

        categories.add(optionHome);
        categories.add(optionWork);
        categories.add(optionStudy);
        categories.add(optionPersonal);


        //NEWLIST POPUP
        newlistPopup = new Dialog(this);
        newlistPopup.setContentView(R.layout.newlist_popup);
        Objects.requireNonNull(newlistPopup.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        inputList = newlistPopup.findViewById(R.id.input_List);
        createNewlistPopup = newlistPopup.findViewById(R.id.create_list);
        cancelNewlistPopup = newlistPopup.findViewById(R.id.cancel_list);


        //FOOTER
        textFooter = findViewById(R.id.text_footer);



        //##### EVENTS #####
        //POPUP
        createNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.show();
                inputTask.setText("");
                cleanSelection();
            }
        });
        cancelPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        createPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                createTask();
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

        //NEWLIST POPUP
        createNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newlistPopup.show();
                inputList.setText("");
            }
        });
        createNewlistPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                createList();
            }
        });
        cancelNewlistPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newlistPopup.dismiss();
            }
        });


        //SETTINGS
        textSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }


    //POPUP
    public void createTask() {
        if(inputTask.getText().toString().equals("")){
            Toast.makeText(this, "A Task description is required", Toast.LENGTH_SHORT).show();
        } else {
            popup.dismiss();
            renderTask(inputTask.getText().toString());
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

    //NEWLIST POPUP
    public void createList() {
        if(inputList.getText().toString().equals("")){
            Toast.makeText(this, "A List name is required", Toast.LENGTH_SHORT).show();
        } else {
            newlistPopup.dismiss();
        }
    }

    public void renderTask(String task) {
        newTaskInfo.setVisibility(View.INVISIBLE);
        constraintLayoutTasks.addView(createTaskViews(task));
    }

    public TextView createTaskViews(String task){
        CheckBox checkBox = new CheckBox(this);
        TextView textView = new TextView(new ContextThemeWrapper(this, R.style.task), null, 0);
        textView.setText(task);
        return textView;
    }
}
