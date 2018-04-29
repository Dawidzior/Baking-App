package dawidzior.bakingapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dawidzior.bakingapp.model.Ingredient;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private final List<Ingredient> ingredients;

    public IngredientsAdapter(List<Ingredient> items) {
        ingredients = items;
    }

    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new IngredientsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final IngredientsAdapter.ViewHolder holder, int position) {
        Ingredient currentIngredient = ingredients.get(position);
        holder.name.setText(currentIngredient.getName());
        holder.measureQuantity
                .setText(String.valueOf(currentIngredient.getQuantity() + " " + currentIngredient.getMeasure()));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final TextView measureQuantity;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(android.R.id.text1);
            measureQuantity = view.findViewById(android.R.id.text2);
        }
    }
}
