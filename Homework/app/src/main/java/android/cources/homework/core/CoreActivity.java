package android.cources.homework.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.cources.homework.helpers.SettingsManager;
import android.cources.homework.structure.AppExtStructure;
import android.cources.homework.structure.AppStructure;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Роман on 10.07.2017.
 */

public class CoreActivity extends AppCompatActivity {

    protected Context context;
    protected CoreApplication application;
    protected PackageManager packageManager;
    protected SettingsManager settingsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();
        application = (CoreApplication) getApplicationContext();
        settingsManager = application.getSettingsManager();
        packageManager = getPackageManager();
    }

    /**
     * Create list of apps
     * */
    protected List<AppExtStructure> createAppsList(List<ApplicationInfo> appsInfoList, boolean onlyChecked) {
        List<AppExtStructure> appsList = new ArrayList<>();
        List<AppStructure> checkedApps = settingsManager.getApps();

        for (ApplicationInfo appInfo: appsInfoList) {
            boolean isChecked = getAppCheckedStatus(checkedApps, appInfo.packageName);

            if(onlyChecked && !isChecked)
                continue;

            appsList.add(new AppExtStructure(
                    appInfo.packageName,
                    appInfo.loadLabel(packageManager).toString(),
                    appInfo.loadIcon(packageManager),
                    packageManager.getLaunchIntentForPackage(appInfo.packageName),
                    isChecked
            ));
        }

        return appsList;
    }

    private boolean getAppCheckedStatus(List<AppStructure> checkedApps, String packageName) {
        for (AppStructure app: checkedApps) {
            if(app.getPackage().equals(packageName))
                return true;
        }

        return false;
    }
}
