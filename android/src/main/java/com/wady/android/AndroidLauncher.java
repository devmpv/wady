package com.wady.android;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.mpv.GameStarter;

public class AndroidLauncher extends AndroidApplication {
	//private AdView adView;

	// This is the callback that posts a message for the handler
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Create the layout
		RelativeLayout layout = new RelativeLayout(this);

		// Create the libgdx View
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		/*
		// Create and setup the AdMob view
		// adView = new AdView(this);
		//adView.setAdUnitId("ca-app-pub-9161258038291870/6015923347");
		//adView.setAdSize(AdSize.SMART_BANNER);

        AdRequest adRequest = new AdRequest.Builder()
        					.addTestDevice(AdRequest.DEVICE_ID_EMULATOR) //Emulator
        					.addTestDevice("79E92E554ACEC25E7C0744DB62D560B7") // Lenovo test
        					.build();
        adView.loadAd(adRequest);
        */
		// Add the libgdx view
		View gameView = initializeForView(new GameStarter());
		layout.addView(gameView);
		// Add the AdMob view
		RelativeLayout.LayoutParams adParams =
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		/* UNCOMMENT TO ENABLE ADS! */
		// layout.addView(adView, adParams);

		// Hook it all up
		setContentView(layout);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//System.runFinalizersOnExit(true);
		System.exit(0);
	}
}
