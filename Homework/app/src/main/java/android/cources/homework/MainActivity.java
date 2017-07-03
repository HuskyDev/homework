package android.cources.homework;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String DEFAULT_PHONE = "tel:";
    private final String DEFAULT_MESSAGE = "";
    private final String DEFAULT_MESSAGE_TYPE = "text/plain";
    private final String CASE_CALL_TAG = "Call";
    private final String CASE_APP_TAG = "APP";
    private final String CASE_SEND_TAG = "Send";

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        View btn_call = findViewById(R.id.call_btn);
        View btn_app = findViewById(R.id.app_btn);
        View btn_send = findViewById(R.id.send_btn);

        btn_call.setOnClickListener(clickListener);
        btn_app.setOnClickListener(clickListener);
        btn_send.setOnClickListener(clickListener);
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