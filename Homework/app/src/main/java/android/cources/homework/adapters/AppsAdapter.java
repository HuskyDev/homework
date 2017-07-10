package android.cources.homework.adapters;

import android.content.Context;
import android.cources.homework.R;
import android.cources.homework.core.CoreApplication;
import android.cources.homework.holders.AppViewHolder;
import android.cources.homework.structure.AppExtStructure;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Роман on 30.06.2017.
 */

public class AppsAdapter extends RecyclerView.Adapter<AppViewHolder> implements Filterable {
    
    private Context context;
    private CoreApplication application;
    private List<AppExtStructure> appsList;
    private List<AppExtStructure> appsFilteredList;

    public AppsAdapter(Context context, CoreApplication application, List<AppExtStructure> appsList) {
        this.context = context;
        this.application = application;
        this.appsList = new ArrayList<>(appsList);
        this.appsFilteredList = appsList;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);

        return new AppViewHolder(view, context, application);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        boolean isChecked = appsFilteredList.get(position).getIsChecked();

        if(isChecked)
            holder.getCheckBox().setChecked(isChecked);

        holder.getCheckBox().setTag(appsFilteredList.get(position));
        holder.getTextView().setText(appsFilteredList.get(position).getLabel());
        holder.getImageButton().setImageDrawable(appsFilteredList.get(position).getIcon());
        holder.getImageButton().setTag(appsFilteredList.get(position).getIntent());
    }

    @Override
    public int getItemCount() {
        return appsFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    /**
     * Apps filter
     * */
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();

            if(charSequence.length() > 1) {
                appsFilteredList.clear();

                filterTheList(charSequence);
            } else
                appsFilteredList = new ArrayList<>(appsList);

            filterResults.values = appsFilteredList;
            filterResults.count = appsFilteredList.size();

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();
        }
    };

    private void filterTheList(CharSequence charSequence) {
        for(AppExtStructure item : appsList) {
            if(item.getLabel().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                appsFilteredList.add(item);
            }
        }
    }
}
