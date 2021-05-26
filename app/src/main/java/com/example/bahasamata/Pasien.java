package com.example.bahasamata;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.face.FaceDetector;

import java.io.IOException;
import java.util.Locale;


public class Pasien extends AppCompatActivity {

    ImageButton emergencyCall_button, alert_button, alarm_button;
    boolean flag = false;
    CameraSource cameraSource;
    private int seconds = 0;
    private boolean running = false;
    public int pilihan = 0;
    MediaPlayer knockSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_pasien);

        knockSound = MediaPlayer.create(this, R.raw.knocksound);


        emergencyCall_button = (ImageButton) findViewById(R.id.button_telfonDarurat);
        emergencyCall_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pasien.this, "TELFON DARURAT!", Toast.LENGTH_SHORT).show();
            }
        });

        alert_button = (ImageButton) findViewById(R.id.button_alert);
        alert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pasien.this, "ALERT!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Pasien.this, Alert.class));
            }
        });


        runTimer();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 1);
            Toast.makeText(this, "Permission not granted!\n Grant permission and restart app", Toast.LENGTH_SHORT).show();
        } else {
            init();
        }
    }


    private void init() {
        flag = true;
        initCameraSource();
    }

    private void initCameraSource() {
        FaceDetector detector = new FaceDetector.Builder(this)
                .setTrackingEnabled(true)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .setMode(FaceDetector.FAST_MODE)
                .build();
        detector.setProcessor(new MultiProcessor.Builder(new FaceTrackerDaemon(Pasien.this, getLocalClassName())).build());

        cameraSource = new CameraSource.Builder(this, detector)
                .setRequestedPreviewSize(1024, 768)
                .setFacing(CameraSource.CAMERA_FACING_FRONT)
                .setRequestedFps(30.0f)
                .build();

        try {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            cameraSource.start();
        } catch (IOException e) {
            Toast.makeText(Pasien.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (cameraSource != null) {
            try {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                cameraSource.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cameraSource != null) {
            cameraSource.stop();
        }


        //setBackgroundGrey();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraSource != null) {
            cameraSource.release();
        }
    }

    //update view
    public void updateMainView(Condition condition) {
        switch (condition) {
            case USER_EYES_OPEN:
                //setBackgroundGreen();
                 if (seconds == 2) {
                     if (pilihan == 0) {

                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 Toast.makeText(Pasien.this, "TELFON DARURAT!", Toast.LENGTH_SHORT).show();
                             }
                         });
                     } else {
                         runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Pasien.this, "ALERT!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Pasien.this, Alert.class));
                            }
                        });

                    }

                 } else if (seconds >= 1) {

                     pilihan++;
                     if (pilihan > 1) {
                         pilihan = 0;
                     }
                     //Toast.makeText(Pasien.this, pilihan , Toast.LENGTH_SHORT).show();

                 }
                if (seconds > 2) {

                } else if (pilihan == 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            emergencyCall_button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                            alert_button.setBackgroundColor(getResources().getColor(android.R.color.white));
                            // Toast.makeText(Pasien.this, "Pilihan 0 : Emergency Call" , Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (pilihan == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            emergencyCall_button.setBackgroundColor(getResources().getColor(android.R.color.white));
                            alert_button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                            //Toast.makeText(Pasien.this, "Pilihan 1 : Alert" , Toast.LENGTH_SHORT).show();

                        }
                    });

                }


                seconds = 0;
                running = false;
                break;
            case USER_EYES_CLOSED:
                //setBackgroundOrange();
                running = true;
                break;
            case FACE_NOT_FOUND:
                setBackgroundRed();
                running = false;
                seconds = 0;
                break;
            default:
                //setBackgroundGrey();
        }
    }

    //set background Grey
//    private void setBackgroundGrey() {
//        if (background != null)
//            background.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
//    }
//
//    //set background Green
//    private void setBackgroundGreen() {
//        if (background != null) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    background.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
//                }
//            });
//        }
//    }
//
//    //set background Orange
//    private void setBackgroundOrange() {
//        if (background != null) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    background.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
//                }
//            });
//        }
//    }
//
//    //set background Red
    private void setBackgroundRed() {
        if (alert_button.getBackground() != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    alert_button.setBackgroundColor(getResources().getColor(android.R.color.white));
                }
            });
        }
        if (emergencyCall_button.getBackground() != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    emergencyCall_button.setBackgroundColor(getResources().getColor(android.R.color.white));
                }
            });
        }
    }

    private void runTimer() {

        // Get the text view

        // Creates a new Handler
        final Handler handler
                = new Handler();

        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
        handler.post(new Runnable() {
            @Override

            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                final TextView timeView
                        = (TextView) findViewById(
                        R.id.time_view);

                String time
                        = String
                        .format(Locale.getDefault(),
                                "%d:%02d:%02d", hours,
                                minutes, secs);

                // Set the text view text.
                timeView.setText(time);
                // If running is true, increment the
                // seconds variable.
                if (running) {
                    knockSound.start();
                    seconds++;

                }

                // Post the code again
                // with a delay of 1 second.


                handler.postDelayed(this, 1000);


            }
        });
    }

}