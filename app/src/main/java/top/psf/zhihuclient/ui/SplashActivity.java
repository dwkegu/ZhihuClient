package top.psf.zhihuclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import top.psf.zhihuclient.MainActivity;
import top.psf.zhihuclient.R;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.tv_splash_counter)
    TextView tvCounter;
    int leftCounter = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ButterKnife.bind(this);
        CountDownTimer timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvCounter.setText(String.valueOf(--leftCounter));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.start();
    }
}
