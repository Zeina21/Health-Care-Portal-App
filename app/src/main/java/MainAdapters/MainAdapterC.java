package MainAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import JavaClasses.CovidC;
import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdapterC extends FirebaseRecyclerAdapter<CovidC, MainAdapterC.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterC(@NonNull FirebaseRecyclerOptions<CovidC> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CovidC model) {
        holder.InDate.setText(model.getInfection());
        holder.Vtype.setText(model.getType());
        holder.DoseNum.setText(model.getDosesNumber());
        holder.VaccDate.setText(model.getVacc());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_covid19, parent, false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView InDate, Vtype, DoseNum, VaccDate;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
           // InDate = (TextView) itemView.findViewById(R.id.DateIn);
            Vtype = (TextView) itemView.findViewById(R.id.VaccT);
            DoseNum = (TextView) itemView.findViewById(R.id.NumD);
            VaccDate = (TextView) itemView.findViewById(R.id.DoseD);
        }
    }
}
