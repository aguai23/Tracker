package com.tracker.tracker.services.local;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class locationService extends Service {
    public locationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
