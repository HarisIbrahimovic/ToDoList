package com.example.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.model.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final ClickListener clickListener;
    private final Context context;
    private List<Note> listOfNotes;

    public NoteAdapter(ClickListener clickListener ,Context context){
        this.clickListener = clickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = listOfNotes.get(position);
        holder.itemName.setText(note.getName());
        holder.checkBox.setChecked(note.isCompleted());
        holder.checkBox.setOnClickListener(v ->   clickListener.checkBoxClicked(note));
        holder.deleteButton.setOnClickListener(v->clickListener.deleteNote(note));
    }

    @Override
    public int getItemCount() {
        if(listOfNotes==null)return 0;
        return listOfNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView itemName = itemView.findViewById(R.id.itemNoteName);
        CheckBox checkBox = itemView.findViewById(R.id.checkBox);
        ImageButton deleteButton = itemView.findViewById(R.id.deleteButtonNote);
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.noteClicked(listOfNotes.get(getAdapterPosition()));
        }
    }

    public void setListOfNotes(List<Note> listOfNotes) {
        this.listOfNotes = listOfNotes;
        notifyDataSetChanged();
    }

    public interface ClickListener{
        void checkBoxClicked(Note note);
        void noteClicked(Note note);
        void deleteNote(Note note);
    }
}
