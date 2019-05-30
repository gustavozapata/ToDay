package me.gustavozapata.today;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean isFirstTask = true;
    boolean isFirstComplete = true;
    List listDo;
    List listDone;
    TextView textView_complete;
    CheckBox checkBox;
    EditText input_task;
    LinearLayout layoutTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= 21){
            this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

        //LINK TO GUI COMPONENTS
//        TextView btn_add = (TextView) findViewById(R.id.btn_add);
        textView_complete = findViewById(R.id.text_Tasks);
//        layoutTask = (LinearLayout) findViewById(R.id.layoutTask);
//        input_task = (EditText) findViewById(R.id.input_task);
//        checkBox = (CheckBox) findViewById(R.id.checkBox);

//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isFirstTask){
//                    isFirstTask = false;
//                    listDo = new List();
//                    text_notasks.setVisibility(View.INVISIBLE);
//                    layoutTask.setVisibility(View.VISIBLE);
//                    input_task.setFocusable(true);
//                    checkBox.setChecked(false);
//                }
//
//                listDo.setTasks(new Task());
//            }
//        });

//        checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                if(isFirstComplete){
//                    isFirstComplete = false;
//                    listDone = new List();
//                    textView_complete.setVisibility(View.VISIBLE);
//                    layoutTask.setTranslationY(100);
//                }
//            }
//        });
    }
}
