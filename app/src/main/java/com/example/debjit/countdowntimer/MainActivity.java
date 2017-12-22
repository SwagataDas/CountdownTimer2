package com.example.debjit.countdowntimer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText etUsername,etPassword;

    int counter = 2;

    private static final String FORMAT = "%02d:%02d:%02d";

    int seconds , minutes;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        etUsername = findViewById(R.id.editText);
        etPassword = findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.equals("Swagata") && password.equals("swagata123")) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Next.class);
                    startActivity(intent);
                } else if (username.equals("") || password.equals("")) {
                    Toast.makeText(MainActivity.this, "Login failed. Username and/or password doesn't match.", Toast.LENGTH_SHORT).show();
                } else if (counter == 0)
                // Disable button after 3 failed attempts
                {
                    button.setEnabled(false);

                    Toast alert = Toast.makeText(MainActivity.this, "Login Disabled for 30 secs", Toast.LENGTH_SHORT);
                    alert.show();

                    final CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) { // adjust the milli seconds here
                        public void onTick(long millisUntilFinished) {

                            String time = "" + String.format(FORMAT,
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                            textView.setText(time);

                        }

                        public void onFinish() {
                            textView.setText("");
                            button.setEnabled(true);
                            counter = 2;
                        }
                    }.start();
                }
                else
                // Wrong password
                {   Toast alert = Toast.makeText(MainActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT);
                    alert.show();
                    counter--;
                };
            }
        });
    }
}
