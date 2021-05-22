package com.example.bahasamata;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class EyesTracker extends Tracker<Face> {
    private final float THRESHOLD = 0.75f;
    private Context context;
    private String className;

    public EyesTracker(Context context, String className) {

        this.context = context;
        this.className = className;

    }

    @Override
    public void onUpdate(Detector.Detections<Face> detections, Face face) {
        if (face.getIsLeftEyeOpenProbability() > THRESHOLD || face.getIsRightEyeOpenProbability() > THRESHOLD) {
            Log.i(TAG, "onUpdate: Open Eyes Detected");
            if (className.equals("Pasien")) {
                ((Pasien) context).updateMainView(Condition.USER_EYES_OPEN);
            } else if (className.equals("Alert")) {
                ((Pasien) context).updateMainView(Condition.USER_EYES_OPEN);
            }

        } else {
            Log.i(TAG, "onUpdate: Close Eyes Detected");
            if (className.equals("Pasien")) {
                ((Pasien) context).updateMainView(Condition.USER_EYES_CLOSED);
            } else if (className.equals("Alert")) {
                ((Pasien) context).updateMainView(Condition.USER_EYES_CLOSED);
            }
        }
    }

    @Override
    public void onMissing(Detector.Detections<Face> detections) {
        super.onMissing(detections);
        Log.i(TAG, "onUpdate: Face Not Detected!");
        if (className.equals("Pasien")) {
            ((Pasien) context).updateMainView(Condition.FACE_NOT_FOUND);
        } else if (className.equals("Alert")) {
            ((Pasien) context).updateMainView(Condition.FACE_NOT_FOUND);
        }
    }

    @Override
    public void onDone() {
        super.onDone();
    }
}
