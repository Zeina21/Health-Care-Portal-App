package MainAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import JavaClasses.PathC;
import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdapterP extends FirebaseRecyclerAdapter<PathC, MainAdapterP.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterP(@NonNull FirebaseRecyclerOptions<PathC> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MainAdapterP.myViewHolder holder, int position, @NonNull PathC model) {
        holder.Dname.setText(model.getDName());
        holder.Sdate.setText(model.getSDate());
        holder.treatment.setText(model.getTreatment());
        holder.doc.setText(model.getDocName());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_path_view, parent, false);
        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView Dname, Sdate, treatment,doc;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            Dname = (TextView) itemView.findViewById(R.id.Dname);
            Sdate = (TextView) itemView.findViewById(R.id.StartDate);
            treatment = (TextView) itemView.findViewById(R.id.treatment);
            doc=(TextView)itemView.findViewById(R.id.docP);
        }
    }
}

