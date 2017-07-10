package android.cources.homework;

import android.content.pm.PackageManager;
import android.cources.homework.adapters.AppsAdapter;
import android.cources.homework.core.CoreActivity;
import android.cources.homework.structure.AppExtStructure;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

public class AppsActivity extends CoreActivity {

    private final int COUNT_COLUMNS = 3;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recycleView;
    private EditText editText;
    private ImageButton imageButton;
    private List<AppExtStructure> appsList;
    private AppsAdapter appsAdapter;
    private boolean isGridView = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);

        recycleView = (RecyclerView) findViewById(R.id.recyclerView);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        editText = (EditText) findViewById(R.id.editText);
        appsList = createAppsList(packageManager.getInstalledApplications(PackageManager.GET_META_DATA), false);
        appsAdapter = new AppsAdapter(this, application, appsList);
        recycleView.setAdapter(appsAdapter);
        editText.addTextChangedListener(textWatcher);
        imageButton.setOnClickListener(changeView);

        createLayout();
    }

    /**
     * Create layout by view type
     * */
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

        recycleView.getRecycledViewPool().clear();
    }

    /**
     * Text watcher
     * */
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            appsAdapter.getFilter().filter(text);
        }
    };

    /**
     * Click listener for changing view
     * */
    private View.OnClickListener changeView = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isGridView = !isGridView;

            createLayout();
        }
    };
}
