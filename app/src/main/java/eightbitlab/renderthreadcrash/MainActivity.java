package eightbitlab.renderthreadcrash;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LolDialogFragment().show(getSupportFragmentManager(), "TAG");
    }

    public static class LolDialogFragment extends DialogFragment {
        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity()).setView(R.layout.layout_with_edit_text).create();
        }
    }
}
