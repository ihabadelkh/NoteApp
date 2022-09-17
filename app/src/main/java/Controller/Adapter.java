package Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.MainActivity;
import com.example.notes.R;
import com.example.notes.UpdateActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import Model.Note;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private ArrayList id, note;


    public Adapter(Context context, ArrayList id, ArrayList note) {
        this.context = context;
        this.id = id;
        this.note = note;
    }



    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_rows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull Adapter.ViewHolder holder, int position) {

        holder.id.setText(String.valueOf(id.get(position)));
        holder.note.setText(String.valueOf(note.get(position)));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("note", String.valueOf(note.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id, note;
        private ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            note = itemView.findViewById(R.id.note);
            constraintLayout = itemView.findViewById(R.id.constraint);
        }
    }
}
