package se.chalmers.tda367.vt13.dimensions;

import se.chalmers.tda367.vt13.dimensions.controller.GameController;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
         
       AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
       cfg.useGL20 = false;
       cfg.useAccelerometer = false;
       cfg.useCompass = false;
         
       initialize(new GameController(), cfg);
    }
}