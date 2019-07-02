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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Task> tasks;
    String taskCategory;
    TextView textSettings;
    TextView createNewTask;
    TextView createNewTaskTemp;
    TextView createNewList;
    TextView newTaskInfo;
    ConstraintLayout constraintLayoutTasks;

    int marginY;

    //POPUP
    Dialog popup;
    ConstraintLayout layoutSelectCategories;
    TextView selectCategory;
    TextView newTask;
    EditText inputTask;
    TextView cancelPopup;
    TextView createPopup;

    ArrayList<TextView> categories;
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
        tasks = new ArrayList<>();
        taskCategory = null;
        textSettings = findViewById(R.id.text_settings);
        createNewTask = findViewById(R.id.createNewTask);
        createNewTaskTemp = findViewById(R.id.text_createNewTask);
        createNewList = findViewById(R.id.text_createNewList);
        newTaskInfo = findViewById(R.id.text_msgTasks);
        constraintLayoutTasks = findViewById(R.id.constraintLayoutTasks);

        marginY = 0;


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

        categories = new ArrayList<>();
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
        createNewTaskTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });
        createNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });
        cancelPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
                taskCategory = null;
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
                taskCategory = "Home";
            }
        });
        optionWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory(optionWork);
                taskCategory = "Work";
            }
        });
        optionStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory(optionStudy);
                taskCategory = "Study";
            }
        });
        optionPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory(optionPersonal);
                taskCategory = "Personal";
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

    //NEWLIST POPUP
    public void createList() {
        if(inputList.getText().toString().equals("")){
            Toast.makeText(this, "A List name is required", Toast.LENGTH_SHORT).show();
        } else {
            newlistPopup.dismiss();
        }
    }

    //POPUP
    public void showPopup() {
        popup.show();
        inputTask.setText("");
        cleanSelection();
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
    public void createTask() {
        if(inputTask.getText().toString().equals("")){
            Toast.makeText(this, "A Task description is required", Toast.LENGTH_SHORT).show();
        } else {
            popup.dismiss();
            createNewTask.setVisibility(View.VISIBLE);
            tasks.add(new Task(inputTask.getText().toString(), taskCategory));
            taskCategory = null;
            renderTasks();
        }
    }
    public void renderTasks(){
        constraintLayoutTasks.removeAllViews();
        for(Task task : tasks){
            constraintLayoutTasks.addView(createTaskElements(task.getDescription()));
        }
        marginY = 0;
        System.out.println(tasks);
    }
    public TextView createTaskElements(String taskTitle){
        CheckBox task = new CheckBox(this);
        task.setText(taskTitle);
        task.setTranslationY(marginY);
        marginY += 90;
        task.setTextAppearance(this, R.style.task);
        task.setPadding(5, -6, 0, 0);
        return task;
    }
}
