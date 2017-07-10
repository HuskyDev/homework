package android.cources.homework.structure;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by Роман on 08.07.2017.
 */

public class AppExtStructure extends AppStructure {

    protected Drawable icon;
    protected Intent intent;
    protected boolean isChecked;

    public AppExtStructure(String pack, String label, Drawable icon, Intent intent, boolean isChecked) {
        super(pack, label);
        this.icon = icon;
        this.intent = intent;
        this.isChecked = isChecked;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }
}
