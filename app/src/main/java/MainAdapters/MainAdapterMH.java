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

import JavaClasses.HistoryC;

public class MainAdapterMH extends FirebaseRecyclerAdapter<HistoryC,MainAdapterMH.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterMH(@NonNull FirebaseRecyclerOptions<HistoryC> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull HistoryC model) {
       holder.operation.setText(model.getOperation());
        holder.opDate.setText(model.getOpDate());
        holder.body.setText(model.getBody());
    holder.anesthesia.setText(model.getAnesthesia());
    holder.opFacility.setText(model.getOpFacility());
        holder.docName.setText(model.getDocName());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_history_view,parent,false);
        return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder{

      TextView operation,body,opDate,anesthesia,opFacility,docName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            operation=(TextView)itemView.findViewById(R.id.OPName);
            opDate=(TextView)itemView.findViewById(R.id.DateHis);
            opFacility=(TextView)itemView.findViewById(R.id.FacilityOP);
            anesthesia=(TextView)itemView.findViewById(R.id.Anes);
            body=(TextView)itemView.findViewById(R.id.body);
            docName=(TextView)itemView.findViewById(R.id.dochis);
        }
    }





}
