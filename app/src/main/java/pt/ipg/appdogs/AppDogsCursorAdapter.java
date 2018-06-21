package pt.ipg.appdogs;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AppDogsCursorAdapter extends RecyclerView.Adapter<AppDogsCursorAdapter.AcidenteViewHolder> {

    private Context context;
    private Cursor cursor = null;
    private View.OnClickListener viewHolderClickListener = null;
    private int lastAcidenteClicked = -1;

    public AppDogsCursorAdapter (Context context){
        this.context = context;
    }

    public void refreahData(Cursor cursor){
        if(this.cursor !=cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    public void setViewHolderClickListener(View.OnClickListener viewHolderClickListener){
        this.viewHolderClickListener = viewHolderClickListener;

    }

    public int getLastAcidenteClicked(){
        return lastAcidenteClicked;
    }

    @NonNull
    @Override
    public AcidenteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(context).inflate(R.layout.item_app_dogs, parent,false);
        return new AcidenteViewHolder(item) ;
    }

    @Override
    public void onBindViewHolder(@NonNull AcidenteViewHolder holder, int position) {
        cursor.moveToPosition(position);
        Acidente acidente =BdTabelaAcidente.getCurrentAcidenteFromCursor(cursor);
        holder.setAcidente(acidente);

    }

    public int getItemCount(){
        if (cursor == null)return 0;

        return cursor.getCount();
    }

    public class AcidenteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textViewIDcao;
        private TextView textViewAcidente;
        private int acidenteId;



        public AcidenteViewHolder(View itemView){

            super(itemView);
            textViewIDcao =  (TextView) itemView.findViewById(R.id.textViewIDcao);
            textViewAcidente = (TextView) itemView.findViewById(R.id.textViewAcidente);

            itemView.setOnClickListener(this);
        }

        public void setAcidente(Acidente acidente){
            textViewIDcao.setText(acidente.getIdCao());
            textViewAcidente.setText(acidente.getTipoDeAcidente());
            acidenteId = acidente.getID();
        }

        public void onClick(View view){
            int position = getAdapterPosition();

            if(position == RecyclerView.NO_POSITION){
                return;
            }
            if(viewHolderClickListener != null){
                lastAcidenteClicked = acidenteId;
                viewHolderClickListener.onClick(view);
            }
        }


    }


}
