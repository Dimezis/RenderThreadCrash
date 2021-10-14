package eightbitlab.renderthreadcrash;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.qiyi.xhook.XHook;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("renderthreadcrash");
    }

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XHook.getInstance().init(this.getApplicationContext());
        if (!com.qiyi.xhook.XHook.getInstance().isInited()) {
            return;
        }

        fixSamsungHwuiCrash();
        XHook.getInstance().refresh(false);

        dialog = new AlertDialog.Builder(this).setView(R.layout.layout_with_edit_text).create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        dialog.dismiss();
        super.onDestroy();
    }

    public static native void fixSamsungHwuiCrash();
}
