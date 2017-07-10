package android.cources.homework.structure;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by Роман on 08.07.2017.
 */

public class AppStructure extends Object {

    private String pack;
    private String label;

    public AppStructure(String pack, String label) {
        this.pack = pack;
        this.label = label;
    }

    public String getPackage() {
        return pack;
    }

    public void setPackage(String pack) {
        this.pack = pack;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
