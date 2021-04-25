import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kickons.R;

public class ItemDisplayAdapter extends RecyclerView.Adapter<ItemDisplayAdapter.ViewHolder> {

    private String[] items,location, price;



    public ItemDisplayAdapter(String[] items, String[] location, String[] price) {
        this.items = items;
        this.location = location;
        this.price = price;

    }

    @NonNull
    @Override
    public ItemDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_item, parent,false);
        //TODO: add onbindveiwholder
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDisplayAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.items.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}
