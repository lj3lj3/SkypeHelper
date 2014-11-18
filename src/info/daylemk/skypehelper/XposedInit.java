
package info.daylemk.skypehelper;

import android.content.res.XModuleResources;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class XposedInit implements IXposedHookLoadPackage, IXposedHookZygoteInit,
        IXposedHookInitPackageResources {
    private static final String TAG = ">>>>>DayL";
    private static final String TAG_CLASS = "[XposedInit]";

    private static String MODULE_PATH = null;

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
    }

    @Override
    public void handleLoadPackage(final LoadPackageParam lpparam)
            throws Throwable {
        if (!lpparam.packageName.equals("com.skype.raider")) {
            return;
        }

        // XposedBridge.log(TAG + TAG_CLASS + "the skype is here");
        //
        // Class<?> class1 =
        // XposedHelpers.findClass("com.skype.android.res.Sounds",
        // lpparam.classLoader);
        // XposedBridge.log(TAG + TAG_CLASS + "the class found : " + class1);
        // Method[] methods = class1.getDeclaredMethods();
        // for (int i = 0; i < methods.length; i++) {
        // XposedBridge.log(TAG + TAG_CLASS + "methods " + i + ": " +
        // methods[i].toString());
        // }
    }

    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        if (!resparam.packageName.equals("com.skype.raider")) {
            return;
        }

        XposedBridge.log(TAG + TAG_CLASS + "init skype res");

        // File file = new File("/system/media/audio/notifications/Titan.ogg");
        // XposedBridge.log(TAG + TAG_CLASS + "the file : " + file);
        // // File file2 = new
        // File("/sdcard/media/notifications/Antonio_Cesaro_2 - msg.mp3");
        // // XposedBridge.log(TAG + TAG_CLASS + "the file2 : " + file2);
        //
        // resparam.res.setReplacement("com.skype.raider", "raw", "logout",
        // file);

        XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);
        resparam.res.setReplacement("com.skype.raider", "raw", "call_ringin", 
                modRes.fwd(R.raw.raw_05_instant_crush));

        XposedBridge.log(TAG + TAG_CLASS + "success");
    }
}