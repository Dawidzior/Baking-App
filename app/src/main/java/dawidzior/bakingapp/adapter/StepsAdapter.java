package dawidzior.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import dawidzior.bakingapp.R;
import dawidzior.bakingapp.activity.StepActivity;
import dawidzior.bakingapp.fragment.StepFragment;
import dawidzior.bakingapp.model.Step;
import lombok.Getter;
import lombok.Setter;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private static final String DOT_SPACE = ". ";

    private Context context;

    @Getter
    private final List<Step> steps;

    @Getter
    @Setter
    private int selectedStep = 0;

    @Getter
    private OnItemSelectedListener listener;

    public StepsAdapter(Context context, List<Step> items) {
        this.context = context;
        steps = items;
        listener = (OnItemSelectedListener) context;
    }

    @NonNull
    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_list_item, parent, false);
        return new StepsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StepsAdapter.ViewHolder holder, int position) {
        if (position != 0) {
            holder.stepDescription
                    .setText(String.valueOf(position) + DOT_SPACE + steps.get(position).getShortDescription());
        } else {
            holder.stepDescription.setText(steps.get(position).getShortDescription());
        }

        if (context.getResources().getBoolean(R.bool.isTablet)) {
            if (position == selectedStep) {
                holder.stepDescription.setBackgroundResource(R.color.colorAccent);
                holder.stepDescription.setTextColor(context.getResources().getColor(android.R.color.primary_text_dark));
            } else {
                holder.stepDescription.setBackgroundResource(android.R.color.background_light);
                holder.stepDescription
                        .setTextColor(context.getResources().getColor(android.R.color.primary_text_light));
            }
        }
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView stepDescription;

        ViewHolder(View view) {
            super(view);
            stepDescription = view.findViewById(R.id.step_description);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (context.getResources().getBoolean(R.bool.isTablet)) {
                selectedStep = getAdapterPosition();
                if (listener != null) listener.onStepListClick(steps.get(getAdapterPosition()));
                notifyDataSetChanged();
            } else {
                Intent intent = new Intent(context, StepActivity.class);
                intent.putExtra(StepFragment.STEPS_LIST, Parcels.wrap(steps));
                intent.putExtra(StepFragment.STEP_NUMBER, getAdapterPosition());
                context.startActivity(intent);
            }
        }
    }

    public interface OnItemSelectedListener {
        void onStepListClick(Step step);
    }
}
