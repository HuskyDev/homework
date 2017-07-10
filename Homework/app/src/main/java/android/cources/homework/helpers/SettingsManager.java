package android.cources.homework.helpers;

import android.app.Application;
import android.content.SharedPreferences;
import android.cources.homework.structure.AppStructure;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Роман on 08.07.2017.
 */

public class SettingsManager {
    private static final String KEY_APPS = "apps";

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private Type listType;

    public SettingsManager(Application application) {
        sp = PreferenceManager.getDefaultSharedPreferences(application);
        editor = sp.edit();
        gson = new Gson();
        listType = new TypeToken<List<AppStructure>>(){}.getType();
    }

    private void saveString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    private String getString(String key) {
        return sp.getString(key, null);
    }

    public void saveApps(List<AppStructure> data) {
        String json = gson.toJson(data);

        saveString(KEY_APPS, json);
    }

    public List<AppStructure> getApps() {
        String json = getString(KEY_APPS);

        return json != null ?
                (List<AppStructure>) gson.fromJson(getString(KEY_APPS), listType) :
                new ArrayList<AppStructure>();
    }
}
