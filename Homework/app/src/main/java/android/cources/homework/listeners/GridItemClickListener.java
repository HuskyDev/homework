package android.cources.homework.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by Роман on 11.07.2017.
 */

public class GridItemClickListener implements View.OnClickListener {

    private Context context;
    private Intent intent;

    public GridItemClickListener(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void onClick(View v) {
        if(intent != null)
            context.startActivity(intent);
    }
}
