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

import JavaClasses.HeartC;

public class MainAdapterH extends FirebaseRecyclerAdapter<HeartC,MainAdapterH.myViewHolder> {
/**
 * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
 * {@link FirebaseRecyclerOptions} for configuration options.
 *
 * @param options
 */
    public MainAdapterH(@NonNull FirebaseRecyclerOptions<HeartC> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull HeartC model) {
       holder.Time.setText(model.getTime());
        holder.date.setText(model.getDate());
        holder.BPM.setText(model.getBPM());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_heart_view,parent,false);
        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder{

      TextView Time,date,BPM;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            Time=(TextView)itemView.findViewById(R.id.TimeHR);
            date=(TextView)itemView.findViewById(R.id.dateHR);
            BPM=(TextView)itemView.findViewById(R.id.BPMHR);
        }
    }

}

