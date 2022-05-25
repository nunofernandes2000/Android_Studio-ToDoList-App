package com.estgp.todolist;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.estgp.todolist.Classes.ListAdapter;
import com.estgp.todolist.Classes.task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_SELECT_LIST = "LIST_TASK";
    public static Activity mainActivity;
    private ArrayList<task> tasklist;
    public static ArrayList<task> tasklists;
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private ActivityResultLauncher<Intent> formActivityResultLauncher;
    private Integer selectedListIndex = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;


        tasklist = new ArrayList<>();



        recyclerView = findViewById(R.id.rv_list);
        adapter = new ListAdapter(this, tasklist,  mainActivity);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        //mensagem de alerta
        if(tasklist.isEmpty()){
            Toast.makeText(getApplicationContext(),R.string.Toast, Toast.LENGTH_LONG).show();
        }

        formActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Log.d("myTag", "Activity Result OK");
                            int taskListSize = tasklist.size();
                            task TaskList = (task) result.getData().getSerializableExtra(ListForm.EXTRA_LIST);

                            if(selectedListIndex == null) {
                                Log.d("myTag", "MainActivity New Student");
                                tasklist.add(TaskList);
                                recyclerView.getAdapter().notifyItemInserted(taskListSize);
                                recyclerView.smoothScrollToPosition(taskListSize);
                            }
                            else{
                                tasklist.set(selectedListIndex, TaskList);
                                Log.d("myTag", "MainActivity Updated Student");
                                recyclerView.getAdapter().notifyItemChanged(selectedListIndex);
                                recyclerView.smoothScrollToPosition(selectedListIndex);
                                selectedListIndex = null;
                            }

                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            selectedListIndex = null;
                            Log.d("myTag", "Activity Result CANCELLED");
                        }
                    }
                });
    }

    //######################################## Menu ################################################
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), ListForm.class);
            formActivityResultLauncher.launch(intent);
            return true;
        } else if (id == R.id.action_delete) {

            //resolve crash da app
            if (tasklist.isEmpty()){

            }else{
                tasklist.remove(0);
                recyclerView.getAdapter().notifyItemRemoved(0);

                //mensagem de alerta
                if(tasklist.isEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.Toast, Toast.LENGTH_LONG).show();
                }
            }

        }

        return super.onOptionsItemSelected(item);
    }

}
