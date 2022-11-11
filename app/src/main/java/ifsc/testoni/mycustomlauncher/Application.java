package ifsc.testoni.mycustomlauncher;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

public class Application {
    PackageInfo packageInfo;
    ApplicationInfo applicationInfo;

    public Application(PackageInfo packageInfo, ApplicationInfo applicationInfo) {
        this.packageInfo = packageInfo;
        this.applicationInfo = applicationInfo;
    }
}
