package android.cources.homework.holders;

import android.content.Context;
import android.content.Intent;
import android.cources.homework.R;
import android.cources.homework.core.CoreApplication;
import android.cources.homework.helpers.SettingsManager;
import android.cources.homework.structure.AppExtStructure;
import android.cources.homework.structure.AppStructure;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Роман on 08.07.2017.
 */

public class AppViewHolder extends RecyclerView.ViewHolder {

    public static final int MAX_CHECKED_COUNT = 12;

    private Context context;
    private SettingsManager settingsManager;
    private CheckBox checkBox;
    private ImageButton imageButton;
    private TextView textView;

    public AppViewHolder(View itemView, Context context, CoreApplication application) {
        super(itemView);

        this.context = context;
        settingsManager = application.getSettingsManager();
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        textView = (TextView) itemView.findViewById(R.id.textView);
        imageButton = (ImageButton) itemView.findViewById(R.id.imageButton);
        checkBox.setOnClickListener(this.cbClick);
        imageButton.setOnClickListener(this.btnClick);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    private View.OnClickListener cbClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            CheckBox cb = (CheckBox) v;
            List<AppStructure> checkedApps = settingsManager.getApps();
            AppExtStructure currentApp = (AppExtStructure) v.getTag();

            if((checkedApps.size() >= MAX_CHECKED_COUNT) && cb.isChecked()) {
                Toast.makeText(
                    context,
                    "Максимально можно выбрать только " + MAX_CHECKED_COUNT + " приложений",
                    Toast.LENGTH_SHORT
                ).show();
                cb.setChecked(false);
            } else if(!cb.isChecked())
                checkedApps = removeApp(checkedApps, currentApp.getPackage());
            else
                checkedApps.add(new AppStructure(currentApp.getPackage(), currentApp.getLabel()));

            settingsManager.saveApps(checkedApps);
        }
    };

    private List<AppStructure> removeApp(List<AppStructure> apps, String packageName) {
        int counter = 0;

        for (AppStructure app: apps) {
            if(app.getPackage().equals(packageName)) {
                apps.remove(counter);
                break;
            }

            counter++;
        }

        return apps;
    }

    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent appIntent = (Intent) v.getTag();

            if(appIntent != null)
                context.startActivity(appIntent);
        }
    };
}
