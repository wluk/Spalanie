package localhost.spalanie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent addRefuel = new Intent();
                addRefuel.setClass(WelcomeActivity.this, TestActivity.class);
                startActivity(addRefuel);
            }
        }, 1000);
    }
}
