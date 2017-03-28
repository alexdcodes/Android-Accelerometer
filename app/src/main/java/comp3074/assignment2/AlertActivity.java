// Alex Diker
// 100746284
// George Brown College


package comp3074.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AlertActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

// shows the shake_alert.xml in the layout

        setContentView(R.layout.shake_alert);

// shows the button and listens

        View btn = findViewById(R.id.btn_OK);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        super.onBackPressed();
    }
}
