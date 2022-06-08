package com.example.in_class_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private ArrayList<Note> notes;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewNoteText;
        private final TextView textViewNoteUserID;
        private final TextView textViewNoteID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNoteText = itemView.findViewById(R.id.textViewNoteText);
            textViewNoteUserID = itemView.findViewById(R.id.textViewNoteUserID);
            textViewNoteID = itemView.findViewById(R.id.textViewNoteID);
        }

        public TextView getTextViewNoteText() {
            return textViewNoteText;
        }

        public TextView getTextViewNoteUserID() {
            return textViewNoteUserID;
        }

        public TextView getTextViewNoteID() {
            return textViewNoteID;
        }
    }

    public NotesAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_list_inclass07, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextViewNoteText().setText(notes.get(position).getText());
        holder.getTextViewNoteUserID().setText(notes.get(position).getUserId());
        holder.getTextViewNoteID().setText(notes.get(position).get_id());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


}
