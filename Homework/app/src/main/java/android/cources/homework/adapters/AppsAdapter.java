package android.cources.homework.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.cources.homework.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Роман on 30.06.2017.
 */

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> implements Filterable {
    
    private Context context;
    private PackageManager packageManager;
    private List<ApplicationInfo> appsList;
    private List<ApplicationInfo> appsFilteredList;

    public AppsAdapter(Context context, PackageManager packageManager, List<ApplicationInfo> appsList) {
        this.context = context;
        this.packageManager = packageManager;
        this.appsList = new ArrayList<>(appsList);
        this.appsFilteredList = appsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_app, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intent appIntent = this.packageManager.getLaunchIntentForPackage(appsFilteredList.get(position).packageName);

        holder.textView.setText(appsFilteredList.get(position).loadLabel(this.packageManager));
        holder.imageButton.setImageDrawable(appsFilteredList.get(position).loadIcon(this.packageManager));
        holder.imageButton.setTag(appIntent);
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
     * View holder
     * */
    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imageButton;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageButton = (ImageButton) itemView.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(this.clickListener);
        }

        private View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = (Intent) v.getTag();

                if(appIntent != null)
                    context.startActivity(appIntent);
            }
        };
    }

    /**
     * Filter of apps
     * */
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();

            if(charSequence.length() > 1) {
                appsFilteredList.clear();

                for(ApplicationInfo item : appsList) {
                    if(item.loadLabel(packageManager).toString().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        appsFilteredList.add(item);
                    }
                }
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
}
