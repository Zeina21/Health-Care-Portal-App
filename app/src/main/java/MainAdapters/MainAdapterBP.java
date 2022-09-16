package MainAdapters;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import JavaClasses.BloodC;
import com.example.seniorproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public class MainAdapterBP extends FirebaseRecyclerAdapter<BloodC,MainAdapterBP.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterBP(@NonNull FirebaseRecyclerOptions<BloodC> options) {

        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull BloodC model) {
       holder.systolic.setText(model.getSystolic());
        holder.bpdate.setText(model.getBPDate());
        holder.diastolic.setText(model.getDiastolic());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_blood_view,parent,false);
        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder{

      TextView bpdate,systolic,diastolic;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            bpdate=(TextView)itemView.findViewById(R.id.PressureDate);
            systolic=(TextView)itemView.findViewById(R.id.Systolic);
            diastolic=(TextView)itemView.findViewById(R.id.Diastolic);
        }
    }

}



