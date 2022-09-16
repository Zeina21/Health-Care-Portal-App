package MainAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import JavaClasses.ImmunizationC;
import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends FirebaseRecyclerAdapter<ImmunizationC,MainAdapter.myViewHolder>{
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<ImmunizationC> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ImmunizationC model) {
       holder.name.setText(model.getName());
        holder.date.setText(model.getDate());
        holder.facility.setText(model.getFacility());
        holder.doc.setText(model.getDocName());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_immunization_view,parent,false);
        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder{

      TextView name,date,facility,doc;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.ImmuN);
            date=(TextView)itemView.findViewById(R.id.DateI);
            facility=(TextView)itemView.findViewById(R.id.facilityI);
            doc=(TextView)itemView.findViewById(R.id.docI);
        }
    }

}
