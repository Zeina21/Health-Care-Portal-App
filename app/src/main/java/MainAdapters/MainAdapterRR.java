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

import JavaClasses.RespiratoryC;

public class MainAdapterRR  extends FirebaseRecyclerAdapter<RespiratoryC,MainAdapterRR.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterRR(@NonNull FirebaseRecyclerOptions<RespiratoryC> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull RespiratoryC model) {
       holder.breath.setText(model.getBreathRR());
        holder.dateRR.setText(model.getDateRR());
        holder.timeRR.setText(model.getTimeRR());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_respiratory_view,parent,false);
        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder{

      TextView breath,dateRR,timeRR;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            breath=(TextView)itemView.findViewById(R.id.Breath);
            dateRR=(TextView)itemView.findViewById(R.id.DateRR);
            timeRR=(TextView)itemView.findViewById(R.id.TimeRR);
        }
    }

}

