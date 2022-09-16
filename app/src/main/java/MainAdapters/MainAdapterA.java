package MainAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import JavaClasses.AllergiesC;

public class MainAdapterA extends FirebaseRecyclerAdapter<AllergiesC,MainAdapterA.myViewHolder> {


/**
 * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
 * {@link FirebaseRecyclerOptions} for configuration options.
 *
 * @param options
 */
    public MainAdapterA(@NonNull FirebaseRecyclerOptions<AllergiesC> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull AllergiesC model) {
        holder.Type.setText(model.getAtype());
        holder.AName.setText(model.getAName());
        holder.Date.setText(model.getAdate());
        holder.DocName.setText(model.getDocName());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_allergie_view,parent,false);

        return new myViewHolder(v);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView Type,AName,Date,DocName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            Type = itemView.findViewById(R.id.TypeAle);
            AName = itemView.findViewById(R.id.NameA);
            Date = itemView.findViewById(R.id.DateAle);
            DocName= itemView.findViewById(R.id.docA);

        }
    }





}
