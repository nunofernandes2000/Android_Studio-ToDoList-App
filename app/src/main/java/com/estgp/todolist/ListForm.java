package com.estgp.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.estgp.todolist.Classes.task;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ListForm extends AppCompatActivity {

    public static final String EXTRA_LIST = "LIST_TASK_V2";

    private EditText etListName;

    private EditText etListDescription;

    private EditText etLimitDate;

    private Button btnInsert;

    private DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_form);


        etLimitDate = findViewById(R.id.et_limit_Date);
        etListDescription = findViewById(R.id.et_list_description);
        etListName = findViewById(R.id.et_list_name);


        btnInsert = findViewById(R.id.btn_insert);

        initDatePicker();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                task tasklist = new task();

                Log.d("mytag", "Date" + etLimitDate.getText().toString());

                if(etListName.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), R.string.please_insert_name, Toast.LENGTH_LONG).show();
                    return;
                }

                if(etListDescription.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(),R.string.please_insert_name, Toast.LENGTH_LONG).show();
                    return;
                }
                tasklist.setName(etListName.getText().toString().isEmpty() ? null : (etListName.getText().toString()));
                tasklist.setDescription(etListDescription.getText().toString().isEmpty() ? null : etListDescription.getText().toString());
                tasklist.setLimitDate(etLimitDate.getText().toString().isEmpty() ? null : stringToLocalDate(etLimitDate.getText().toString()));


                Log.d("mytag", tasklist.toString());

                Intent replyIntent = new Intent();
                replyIntent.putExtra(EXTRA_LIST, (Serializable) tasklist);

                setResult(RESULT_OK, replyIntent);
                finish();

            }
        });
    }


    //Métodos Data
    private LocalDate stringToLocalDate(String date){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        }
        return null;
    }

    private String localdateToString(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(date != null) {
            return date.format(formatter);
        }
        return "";
    }

    private void initDatePicker(){
        etLimitDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                if(!etLimitDate.getText().toString().isEmpty()){
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        calendar.setTime(sdf.parse(etLimitDate.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(ListForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String checkValidDay = dayOfMonth >= 10 ? String.valueOf(dayOfMonth) : "0" + dayOfMonth;
                        String checkValidMonth = month >= 10 ? String.valueOf(month+1) : "0" + (month+1);

                        etLimitDate.setText(checkValidDay + "/" + checkValidMonth + "/" + year);
                    }
                }, year, month, day);

                datePicker.show();
            }
        });

    }
}