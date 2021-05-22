package com.example.bahasamata;


import android.content.Context;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

public class FaceTrackerDaemon implements MultiProcessor.Factory<Face> {

    private Context context;
    private String className;

    public FaceTrackerDaemon(Context context, String className) {
        this.context = context;
        this.className = className;
    }

    @Override
    public Tracker<Face> create(Face face) {
        return new EyesTracker(context, className);
    }
}
