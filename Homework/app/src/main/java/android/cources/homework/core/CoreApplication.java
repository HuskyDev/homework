package android.cources.homework.core;

import android.app.Application;
import android.cources.homework.helpers.SettingsManager;

/**
 * Created by Роман on 08.07.2017.
 */

public class CoreApplication extends Application {
    private SettingsManager settingsManager;

    @Override
    public void onCreate() {
        super.onCreate();

        settingsManager = new SettingsManager(this);
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }
}
