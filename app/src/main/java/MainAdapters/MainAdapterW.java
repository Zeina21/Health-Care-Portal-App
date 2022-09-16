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

import JavaClasses.WeightC;

public class MainAdapterW extends FirebaseRecyclerAdapter<WeightC,MainAdapterW.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterW(@NonNull FirebaseRecyclerOptions<WeightC> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull WeightC model) {
       holder.height.setText(model.getHeight());
        holder.weight.setText(model.getWeight());
        holder.wdate.setText(model.getWdate());
        holder.BMI.setText(model.getBMI());
        holder.status.setText(model.getStatus());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_meaus_view2,parent,false);
        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder{

      TextView height,weight,wdate,BMI,status;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

           height=(TextView)itemView.findViewById(R.id.HeightM);
            weight=(TextView)itemView.findViewById(R.id.WeightM);
            wdate=(TextView)itemView.findViewById(R.id.DateM);
            BMI=(TextView)itemView.findViewById(R.id.bmiM);
            status=(TextView)itemView.findViewById(R.id.StatusM);
        }
    }
}
