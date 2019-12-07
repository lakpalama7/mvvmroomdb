package com.liveinbits.mvvmactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.liveinbits.mvvmactivity.model.NoteAdapterRecycler;
import com.liveinbits.mvvmactivity.dao.NoteModel;
import com.liveinbits.mvvmactivity.utils.SampleDataProvider;
import com.liveinbits.mvvmactivity.viewmodel.ListActivityViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private List<NoteModel> noteModelList=new ArrayList<>();

    private ListActivityViewModel viewModel;
    Handler handler;
    private Toolbar toolbar;
    NoteAdapterRecycler adapterRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initReyclerview();
        initViewModel();
       toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MVVM TEST App");

        floatingActionButton=findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(MainActivity.this,EditorAcitivity.class);
               startActivity(intent);
            }
        });
       //noteModelList= SampleDataProvider.getListNote();
       // noteModelList=viewModel.listNote;

    }



    private void initViewModel() {

        Observer<List<NoteModel>> observer=new Observer<List<NoteModel>>() {
            @Override
            public void onChanged(List<NoteModel> noteModels) {
                noteModelList.clear();
                noteModelList.addAll(noteModels);
                if(adapterRecycler==null){
                    adapterRecycler =new NoteAdapterRecycler(noteModelList,MainActivity.this);
                    recyclerView.setAdapter(adapterRecycler);
                }
                else{
                    adapterRecycler.notifyDataSetChanged();
                }
            }
        };
        viewModel= ViewModelProviders.of(this).get(ListActivityViewModel.class);
        viewModel.listNote.observe(MainActivity.this,observer);
    }



    private void initReyclerview() {
        recyclerView=findViewById(R.id.content_recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                addData();
                break;
            case R.id.delete:
                deleteAllNote();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllNote() {
        viewModel.deleteAllNote();
    }

    private void addData() {
        viewModel.addSampleData();
    }
}
