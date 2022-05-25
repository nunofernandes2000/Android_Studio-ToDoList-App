package com.estgp.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.estgp.todolist.Classes.ListAdapter;
import com.estgp.todolist.Classes.task;

import java.time.format.DateTimeFormatter;

public class ListDetails extends AppCompatActivity {

    private TextView tvListDetails;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);

        Intent intent = getIntent();
        task selectedStudent = (task) intent.getSerializableExtra(ListAdapter.EXTRA_SELECT_LIST);

        tvListDetails = findViewById(R.id.tv_details_list);



        String taskText = getString(R.string.name_list) + " " +  (selectedStudent.getName() == null ? getString(R.string.not_defined)  : selectedStudent.getName()) + "\n";
        taskText += getString(R.string.list_description) + " " + (selectedStudent.getDescription() == null ? getString(R.string.not_defined)  : selectedStudent.getDescription()) + "\n";
        taskText += getString(R.string.list_date) + " " +  (selectedStudent.getLimitDate() == null ? getString(R.string.not_defined)  : selectedStudent.getLimitDate().format(formatter)) + "\n";


        tvListDetails.setText(taskText);
    }
}