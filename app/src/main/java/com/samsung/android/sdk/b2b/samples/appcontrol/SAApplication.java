/**
 * DISCLAIMER: PLEASE TAKE NOTE THAT THE SAMPLE APPLICATION AND
 * SOURCE CODE DESCRIBED HEREIN IS PROVIDED FOR TESTING PURPOSES ONLY.
 *
 * Samsung expressly disclaims any and all warranties of any kind,
 * whether express or implied, including but not limited to the implied warranties and conditions
 * of merchantability, fitness for a particular purpose and non-infringement.
 * Further, Samsung does not represent or warrant that any portion of the sample application and
 * source code is free of inaccuracies, errors, bugs or interruptions, or is reliable,
 * accurate, complete, or otherwise valid. The sample application and source code is provided
 * "as is" and "as available", without any warranty of any kind from Samsung.
 *
 * Your use of the sample application and source code is at its own discretion and risk,
 * and licensee will be solely responsible for any damage that results from the use of the sample
 * application and source code including, but not limited to, any damage to your computer system or
 * platform. For the purpose of clarity, the sample code is licensed “as is” and
 * licenses bears the risk of using it.
 *
 * Samsung shall not be liable for any direct, indirect or consequential damages or
 * costs of any type arising out of any action taken by you or others related to the sample application
 * and source code.
 */

package com.samsung.android.sdk.b2b.samples.appcontrol;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.samsung.android.sdk.b2b.samples.appcontrol.utils.SAUtils;


/**
 * This class extends Application class and initializes the set up variables
 */

public class SAApplication extends Application {

    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        //Populating all error codes
        SAUtils.populateCodes(mAppContext);
        //Initializing EnterpriseDeviceManager
        SAUtils.initialize(mAppContext);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity,
                                          Bundle savedInstanceState) {

                //Forcing orientation of a newly created Activity to portrait mode
                activity.setRequestedOrientation(
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            }

            @Override
            public void onActivityDestroyed(Activity arg0) {

            }

            @Override
            public void onActivityPaused(Activity arg0) {

            }

            @Override
            public void onActivityResumed(Activity arg0) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity arg0, Bundle arg1) {

            }

            @Override
            public void onActivityStarted(Activity arg0) {

            }

            @Override
            public void onActivityStopped(Activity arg0) {

            }


        });

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
