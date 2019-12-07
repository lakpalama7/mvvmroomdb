package com.liveinbits.mvvmactivity.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.liveinbits.mvvmactivity.EditorAcitivity;
import com.liveinbits.mvvmactivity.R;
import com.liveinbits.mvvmactivity.dao.NoteModel;

import java.util.List;

import static com.liveinbits.mvvmactivity.utils.Constant.NOTE_KEY_ID;

public class NoteAdapterRecycler extends RecyclerView.Adapter<NoteAdapterRecycler.MyViewHolder> {

    private List<NoteModel> modelList;
    private Context context;

    public NoteAdapterRecycler(List<NoteModel> modelList,Context context) {
        this.modelList=modelList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_note_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.title.setText(modelList.get(position).getText());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,EditorAcitivity.class);
                intent.putExtra(NOTE_KEY_ID,modelList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


     class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        FloatingActionButton button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            button=itemView.findViewById(R.id.editbutton);
        }
    }
}
