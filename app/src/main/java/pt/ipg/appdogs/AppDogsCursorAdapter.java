package pt.ipg.appdogs;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class AppDogsCursorAdapter extends RecyclerView.Adapter<AppDogsCursorAdapter.AppDogsViewHolder> {

    private Context context;
    private Cursor cursor = null;
    private View.OnClickListener viewHolderClickListener = null;
    private int lastAppDogsClicked = -1;

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

    public int getLastAppDogsClicked(){
        return lastAppDogsClicked;
    }

    @NonNull
    @Override
    public AppDogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AppDogsViewHolder holder, int position) {

    }

    public int getItemCount(){
        return 0;

    }

    public class AppDogsViewHolder extends RecyclerView.ViewHolder{
        public AppDogsViewHolder(View itemView){
            super(itemView);
        }


    }
}
