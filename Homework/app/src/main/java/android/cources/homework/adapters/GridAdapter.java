package android.cources.homework.adapters;

import android.content.Context;
import android.cources.homework.R;
import android.cources.homework.listeners.GridItemClickListener;
import android.cources.homework.structure.AppExtStructure;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Роман on 10.07.2017.
 */

public class GridAdapter extends BaseAdapter {

    private Context context;
    private List<AppExtStructure> apps;

    public GridAdapter(Context context, List<AppExtStructure> apps) {
        this.context = context;
        this.apps = apps;
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Object getItem(int position) {
        return apps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AppExtStructure app = apps.get(position);

        if ((convertView == null) && app.getIsChecked()) {
            gridView = inflater.inflate(R.layout.grid_view, null);
            ImageButton imageButton = (ImageButton) gridView.findViewById(R.id.gridImageButton);
            TextView textView = (TextView) gridView.findViewById(R.id.gridTextView);
            imageButton.setImageDrawable(app.getIcon());
            imageButton.setOnClickListener(new GridItemClickListener(context, app.getIntent()));
            textView.setText(app.getLabel());
        } else
            gridView = convertView;

        return gridView;
    }
}
