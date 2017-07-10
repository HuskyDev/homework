package android.cources.homework;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.cources.homework.adapters.GridAdapter;
import android.cources.homework.core.CoreActivity;
import android.cources.homework.structure.AppExtStructure;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.List;

public class MainActivity extends CoreActivity {

    private final String DEFAULT_PHONE = "tel:";
    private final String DEFAULT_MESSAGE = "";
    private final String DEFAULT_MESSAGE_TYPE = "text/plain";
    private final String CASE_CALL_TAG = "Call";
    private final String CASE_APP_TAG = "APP";
    private final String CASE_SEND_TAG = "Send";

    private FragmentManager fragmentManager;
    private GridAdapter gridAdapter;
    private GridView gridView;
    private List<AppExtStructure> apps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        View btn_call = findViewById(R.id.call_btn);
        View btn_app = findViewById(R.id.app_btn);
        View btn_send = findViewById(R.id.send_btn);

        btn_call.setOnClickListener(clickListener);
        btn_app.setOnClickListener(clickListener);
        btn_send.setOnClickListener(clickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        apps = createAppsList(packageManager.getInstalledApplications(PackageManager.GET_META_DATA),
                true);
        gridAdapter = new GridAdapter(this, apps);
        gridView.setAdapter(gridAdapter);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getTag().toString()) {
                case CASE_CALL_TAG:
                    intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(DEFAULT_PHONE));
                    startActivity(intent);
                    break;
                case CASE_APP_TAG:
                    startActivity(new Intent(context, AppsActivity.class));
                    break;
                case CASE_SEND_TAG:
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, DEFAULT_MESSAGE);
                    intent.setType(DEFAULT_MESSAGE_TYPE);
                    startActivity(intent);
                    break;
            }
        }
    };
}