package ifsc.testoni.mycustomlauncher;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ApplicationArrayAdapter extends ArrayAdapter<Application> {
    private Context context;
    private int resource;

    public ApplicationArrayAdapter(@NonNull Context context, int resource, @NonNull List<Application> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view =inflater.inflate(resource, parent, false);

        Application application = getItem(position);

        ImageView appIcon = view.findViewById(R.id.app_icon);
        appIcon.setImageDrawable(context.getPackageManager().getApplicationIcon(application.applicationInfo));

        TextView appName = view.findViewById(R.id.app_name);
        appName.setText(context.getPackageManager().getApplicationLabel(application.applicationInfo).toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(application.packageInfo.packageName);

                if (intent != null) {
                    try {
                        context.startActivity(intent);
                    } catch (Exception exception) {
                        Log.e("launch-error", exception.toString());
                    }
                }

                context.startActivity(intent);
            }
        });

        return view;
    }
}
