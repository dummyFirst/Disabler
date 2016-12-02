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

package com.samsung.android.sdk.b2b.samples.appcontrol.apis;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.enterprise.EnterpriseDeviceManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.samsung.android.sdk.b2b.samples.appcontrol.R;
import com.samsung.android.sdk.b2b.samples.appcontrol.constants.SAConstants;
import com.samsung.android.sdk.b2b.samples.appcontrol.utils.SAUtils;

/**
 * This sample app is created to help you get started with the KNOX Standard SDK
 *
 * Min SDK required: KNOX Standard SDK ver 2.0
 *
 * Targeted SDK: KNOX Standard SDK ver 5.4
 *
 * This app enables or disables certain apps
 *
 *  used KNOX standard SDK's API
 *  - enableAndroidBrowser()
 *  - disableAndroidBrowser()
 *  - enableVoiceDialer()
 *  - disableVoiceDialer()
 *  - enableYouTube()
 *  - disableYouTube()
 *  - enableAndroidMarket()
 *  - disableAndroidMarket()
 *  - setEnableApplication()
 *  - setDisableApplication()
 *  - getApplicationStateList()
 *  - setApplicationStateList()
 *  - isApplicationInstalled()
 *
 * @author seap.ts@samsung.com
 *
 */

public class MainActivity extends Activity {

    private SharedPreferences adminLicensePrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        adminLicensePrefs = getSharedPreferences(SAConstants.MY_PREFS_NAME, MODE_PRIVATE);
    }

    public void buttonClicked(View v){
        switch(v.getId()) {
            case R.id.btBrowserEnable:
            case R.id.btBrowserDisable:
            case R.id.btDialerEnable:
            case R.id.btDialerDisable:
            case R.id.btYouTubeEnable:
            case R.id.btYouTubeDisable:
            case R.id.btMarketEnable:
            case R.id.btMarketDisable:
            {
                try {
                    EnterpriseDeviceManager edm = (EnterpriseDeviceManager) getSystemService(EnterpriseDeviceManager.ENTERPRISE_POLICY_SERVICE);
                    switch(v.getId()) {
                        case R.id.btBrowserEnable:
                            // enableAndroidBrowser() enables the native Android browser application
                            edm.getApplicationPolicy().enableAndroidBrowser();
                            SAUtils.displayToast(this, getResources().getString(R.string.android_browser) + getResources().getString(R.string.enabled));
                            break;
                        case R.id.btBrowserDisable:
                            // disableAndroidBrowser() disables the native Android browser application
                            edm.getApplicationPolicy().disableAndroidBrowser();
                            SAUtils.displayToast(this, getResources().getString(R.string.android_browser) + getResources().getString(R.string.disabled));
                            break;
                        case R.id.btDialerEnable:
                            // enableVoiceDialer() enables the voice dialer application.
                            edm.getApplicationPolicy().enableVoiceDialer();
                            SAUtils.displayToast(this, getResources().getString(R.string.voice_dialer) + getResources().getString(R.string.enabled));
                            break;
                        case R.id.btDialerDisable:
                            // disableVoiceDialer() disables the voice dialer application.
                            edm.getApplicationPolicy().disableVoiceDialer();
                            SAUtils.displayToast(this, getResources().getString(R.string.voice_dialer) + getResources().getString(R.string.disabled));
                            break;
                        case R.id.btYouTubeEnable:
                            // enableYouTube() enables the YouTube application.
                            edm.getApplicationPolicy().enableYouTube();
                            SAUtils.displayToast(this, getResources().getString(R.string.youtube) + getResources().getString(R.string.enabled));
                            break;
                        case R.id.btYouTubeDisable:
                            // disableYouTube() disables the YouTube application.
                            edm.getApplicationPolicy().disableYouTube();
                            SAUtils.displayToast(this, getResources().getString(R.string.youtube) + getResources().getString(R.string.disabled));
                            break;
                        case R.id.btMarketEnable:
                            // enableAndroidMarket() enables the Google Play application.
                            edm.getApplicationPolicy().enableAndroidMarket();
                            SAUtils.displayToast(this, getResources().getString(R.string.android_market) + getResources().getString(R.string.enabled));
                            break;
                        case R.id.btMarketDisable:
                            // disableAndroidMarket() disables the Google Play application.
                            edm.getApplicationPolicy().disableAndroidMarket();
                            SAUtils.displayToast(this, getResources().getString(R.string.android_market) + getResources().getString(R.string.disabled));
                            break;
                    }
                } catch (SecurityException e) {
                    // This exception may occur when the caller does not have permission.
                    SAUtils.displayToast(this, getResources().getString(R.string.fail));
                }
            }
            break;

            case R.id.btAppEnable:
            case R.id.btAppDisable:
            {
                try {
                    EnterpriseDeviceManager edm = (EnterpriseDeviceManager) getSystemService(EnterpriseDeviceManager.ENTERPRISE_POLICY_SERVICE);
                    String strPakageName =((TextView)findViewById(R.id.editText)).getText().toString();
                    if(TextUtils.isEmpty(strPakageName)) {
                        strPakageName = getResources().getString(R.string.default_pkg_name);
                    }
                    // isApplicationInstalled() checks if an application package is installed.
                    else if(!edm.getApplicationPolicy().isApplicationInstalled(strPakageName)){
                        SAUtils.displayToast(this, "\'"+strPakageName +"\' " + getResources().getString(R.string.err_pkg_not_installed));
                        break;
                    }
                    boolean result = false;
                    String success_msg;
                    if( v.getId() == R.id.btAppEnable)
                    {
                        //setEnableApplication() enables a application package that was previously disabled.
                        result = edm.getApplicationPolicy().setEnableApplication(strPakageName);
                        success_msg = strPakageName + getResources().getString(R.string.enabled);
                    }
                    else {
                        //setDisableApplication() disables a application package without uninstalling.
                        result = edm.getApplicationPolicy().setDisableApplication(strPakageName);
                        success_msg = strPakageName + getResources().getString(R.string.disabled);
                    }

                    if( true == result){
                        SAUtils.displayToast(this, success_msg);
                    } else {
                        SAUtils.displayToast(this, getResources().getString(R.string.enabling_failed));
                    }
                } catch(SecurityException e) {
                    // This exception may occur when the caller does not have permission.
                    SAUtils.displayToast(this, getResources().getString(R.string.enabling_failed));
                }
            }
            break;

            case R.id.btShowEnabledApp:
            case R.id.btShowDisabledApp:
            {
                try {
                    EnterpriseDeviceManager edm = (EnterpriseDeviceManager) getSystemService(EnterpriseDeviceManager.ENTERPRISE_POLICY_SERVICE);
                    boolean state = false;
                    if( v.getId() == R.id.btShowEnabledApp)
                        state = true;

                    // getApplicationStateList() gets the list of enabled or disabled applications based on state.
                    String[] packageList = edm.getApplicationPolicy().getApplicationStateList(state);
                    if( packageList == null)
                    {
                        if(state)
                            SAUtils.displayToast(this,getResources().getString(R.string.err_no_enabled_app));
                        else
                            SAUtils.displayToast(this,getResources().getString(R.string.err_no_disabled_app));
                        break;
                    }
                    StringBuffer packageNames = new StringBuffer();
                    for(int i = 0 ; i< packageList.length ; i++)
                        packageNames.append(packageList[i]+"\n");
                    new AlertDialog.Builder(this)
                            .setMessage(packageNames)
                            .setPositiveButton(getResources().getString(R.string.ok), null)
                            .show();
                } catch(SecurityException e) {
                    // This exception may occur when the caller does not have permission.
                    Log.w(this.getLocalClassName(),getResources().getString(R.string.security_exception) + e);
                }
            }
            break;

            case R.id.btGetEnableAllApp:
            {
                try {
                    EnterpriseDeviceManager edm = (EnterpriseDeviceManager) getSystemService(EnterpriseDeviceManager.ENTERPRISE_POLICY_SERVICE);
                    // getApplicationStateList() gets the list of enabled or disabled applications based on state.
                    String[] currentlyDisabledAppList = edm.getApplicationPolicy().getApplicationStateList(false);
                    if (null == currentlyDisabledAppList) {
                        SAUtils.displayToast(this,getResources().getString(R.string.err_no_disabled_app));
                        break;
                    }
                    // setApplicationStateList() set the list of application package names to the enabled or disabled state.
                    String[] successfullyEnabledAppList = edm.getApplicationPolicy().setApplicationStateList(currentlyDisabledAppList, true);
                    if (null == successfullyEnabledAppList)
                        throw new NullPointerException(getResources().getString(R.string.err_enabledapplist_is_null));

                    StringBuffer successfullyEnabledApp = new StringBuffer();
                    for (int i = 0; i < successfullyEnabledAppList.length; i++)
                        successfullyEnabledApp.append(successfullyEnabledAppList[i] + "\n");

                    new AlertDialog.Builder(this)
                            .setMessage(successfullyEnabledAppList.length +getResources().getString(R.string.apps_enables)+"\n"+successfullyEnabledApp)
                            .setPositiveButton(getResources().getString(R.string.ok), null)
                            .show();
                } catch(SecurityException e) {
                    // This exception may occur when the caller does not have permission.
                    Log.w(this.getLocalClassName(),getResources().getString(R.string.security_exception)+e);
                }
            }
            break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!adminLicensePrefs.getBoolean(SAConstants.ADMIN, false)) {
            Intent intent = new Intent(this, AdminLicenseActivation.class);
            startActivity(intent);
            finish();
        }
		
        if(!SAUtils.isAPISupported(SAConstants.MDMVersion.VER_2_0))
        {
            ((Button)findViewById(R.id.btBrowserEnable)).setEnabled(false);
            ((Button)findViewById(R.id.btBrowserDisable)).setEnabled(false);
            ((Button)findViewById(R.id.btDialerEnable)).setEnabled(false);
            ((Button)findViewById(R.id.btDialerDisable)).setEnabled(false);
            ((Button)findViewById(R.id.btYouTubeEnable)).setEnabled(false);
            ((Button)findViewById(R.id.btYouTubeDisable)).setEnabled(false);
            ((Button)findViewById(R.id.btMarketEnable)).setEnabled(false);
            ((Button)findViewById(R.id.btMarketDisable)).setEnabled(false);
            ((Button)findViewById(R.id.btAppEnable)).setEnabled(false);
            ((Button)findViewById(R.id.btAppDisable)).setEnabled(false);
            ((Button)findViewById(R.id.btShowEnabledApp)).setEnabled(false);
            ((Button)findViewById(R.id.btShowDisabledApp)).setEnabled(false);
            ((Button)findViewById(R.id.btGetEnableAllApp)).setEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //If device admin has to be disabled
            case R.id.action_disable_admin:
                SharedPreferences sharedpreferences = getSharedPreferences(
                        SAConstants.MY_PREFS_NAME, Context.MODE_PRIVATE);
                Editor editor = sharedpreferences.edit();

                if (sharedpreferences.getBoolean(SAConstants.ADMIN, false)) {
                    editor.putBoolean(SAConstants.ADMIN, false);
                }
                editor.putBoolean(SAConstants.ELM, false);
                editor.commit();
                Intent intent = new Intent(this, AdminLicenseActivation.class);
                intent.putExtra(SAConstants.DEACTIVATION_REQUIRED, true);
                startActivity(intent);
                finish();
                break;

            //Launching AboutActivity
            case R.id.action_about_app:
                Intent aboutAppIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutAppIntent);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

}
