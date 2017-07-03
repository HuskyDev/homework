package android.cources.homework;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.cources.homework.adapters.AppsAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

public class AppsActivity extends AppCompatActivity {

    private final int COUNT_COLUMNS = 3;

    private Context context;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recycleView;
    private EditText editText;
    private ImageButton imageButton;
    private PackageManager packageManager;
    private List<ApplicationInfo> appsList;
    private AppsAdapter appsAdapter;
    private boolean isGridView = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);

        context = getApplicationContext();
        editText = (EditText) findViewById(R.id.editText);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        recycleView = (RecyclerView) findViewById(R.id.recyclerView);
        createLayout();

//        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
//        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        appsList = context.getPackageManager().queryIntentActivities(mainIntent, 0);

        packageManager = getPackageManager();
        appsList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        appsAdapter = new AppsAdapter(this, packageManager, appsList);
        recycleView.setAdapter(appsAdapter);
        editText.addTextChangedListener(textWatcher);
        imageButton.setOnClickListener(clickListener);
    }

    private void createLayout() {
        if(isGridView) {
            layoutManager = new GridLayoutManager(context, COUNT_COLUMNS);
            recycleView.setLayoutManager(layoutManager);
            imageButton.setImageResource(R.drawable.hw_list_view);
        } else {
            layoutManager = new LinearLayoutManager(context);
            recycleView.setLayoutManager(layoutManager);
            imageButton.setImageResource(R.drawable.hw_grid_view);
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            appsAdapter.getFilter().filter(text);
        }
    };

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isGridView = !isGridView;

            createLayout();
        }
    };
}
