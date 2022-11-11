package ifsc.testoni.mycustomlauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_app_list);
        setCustomLauncher();
    }

    private void setCustomLauncher() {
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager packageManager = getPackageManager();

        List<ResolveInfo> resolveInfoList =  packageManager.queryIntentActivities(mainIntent, 0);

        ArrayList<Application> applications = new ArrayList<>();

        for (ResolveInfo ri : resolveInfoList) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(ri.activityInfo.packageName, 0);
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ri.activityInfo.packageName, 0);

                applications.add(new Application(packageInfo, applicationInfo));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        ApplicationArrayAdapter appInfoArrayAdapter = new ApplicationArrayAdapter(
                getApplicationContext(),
                R.layout.launcher_app_list_item,
                applications);

        ListView appListView = findViewById(R.id.launcher_app_list);
        appListView.setAdapter(appInfoArrayAdapter);
    }
}