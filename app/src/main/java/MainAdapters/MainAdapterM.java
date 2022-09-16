package MainAdapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import JavaClasses.MedicamentC;
import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdapterM extends FirebaseRecyclerAdapter<MedicamentC,MainAdapterM.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterM(@NonNull FirebaseRecyclerOptions<MedicamentC> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MedicamentC model) {
        holder.medname.setText(model.getMedName());
        holder.dose.setText(model.getDose());
        holder.frequency.setText(model.getFrequency());
        holder.doc.setText(model.getDocName());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_medication_view,parent,false);

        return new myViewHolder(v);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView medname, dose, frequency,doc;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            medname = itemView.findViewById(R.id.medicationN);
            dose = itemView.findViewById(R.id.doseM);
            frequency = itemView.findViewById(R.id.frequencyM);
            doc=(TextView)itemView.findViewById(R.id.docM);

        }
    }
}