package info.wangqun.launcher;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment implements View.OnClickListener {
    Spinner col_num_spinner;
    Spinner row_num_spinner;
    SeekBar font_control;
    View rootView;
    TextView hideDivider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_setting, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rootView = getView();
        rootView.findViewById(R.id.toBack).setOnClickListener(this);
        rootView.findViewById(R.id.rootView).setOnClickListener(this);
        rootView.findViewById(R.id.deleteApp).setOnClickListener(this);
        hideDivider = rootView.findViewById(R.id.hideDivider);
        hideDivider.setOnClickListener(this);
        hideDivider.setText(Config.hideDivider ? R.string.setting_show_divider : R.string.setting_hide_divider);
        font_control = rootView.findViewById(R.id.font_control);
        col_num_spinner = rootView.findViewById(R.id.col_num_spinner);
        row_num_spinner = rootView.findViewById(R.id.row_num_spinner);
        row_num_spinner.setSelection(Config.rowNum - 2, false);
        font_control.setProgress((int) ((Config.fontSize - 10) * 10));

        row_num_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(Launcher.ROW_NUM_KEY, position + 2);
                intent.setAction(Launcher.LAUNCHER_ACTION);
                getActivity().sendBroadcast(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        col_num_spinner.setSelection(Config.colNum - 2, false);
        col_num_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(Launcher.COL_NUM_KEY, position + 2);
                intent.setAction(Launcher.LAUNCHER_ACTION);
                getActivity().sendBroadcast(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rootView.findViewById(R.id.btnHideFontControl).setOnClickListener(this);
        rootView.findViewById(R.id.changeFontSize).setOnClickListener(this);

        font_control.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    Intent intent = new Intent();
                    Config.fontSize = 10 + progress / 10f;
                    intent.putExtra(Launcher.LAUNCHER_FONT_SIZE, 10 + progress / 10f);
                    intent.setAction(Launcher.LAUNCHER_ACTION);
                    getActivity().sendBroadcast(intent);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toBack:
            case R.id.rootView:
                getActivity().onBackPressed();
                break;
            case R.id.deleteApp:
                Intent intent = new Intent();
                intent.putExtra(Launcher.DELETE_APP, true);
                intent.setAction(Launcher.LAUNCHER_ACTION);
                getActivity().sendBroadcast(intent);
                getActivity().onBackPressed();
                break;
            case R.id.btnHideFontControl:
                rootView.findViewById(R.id.menuList).setVisibility(View.VISIBLE);
                rootView.findViewById(R.id.font_control_p).setVisibility(View.GONE);
                break;
            case R.id.changeFontSize:
                rootView.findViewById(R.id.menuList).setVisibility(View.GONE);
                rootView.findViewById(R.id.font_control_p).setVisibility(View.VISIBLE);
                break;
            case R.id.hideDivider:
                Config.hideDivider = !Config.hideDivider;
                hideDivider.setText(Config.hideDivider ? "显示分隔线" : "隐藏分隔线");

                intent = new Intent();
                intent.putExtra(Launcher.LAUNCHER_HIDE_DIVIDER, Config.hideDivider);
                intent.setAction(Launcher.LAUNCHER_ACTION);
                getActivity().sendBroadcast(intent);
                getActivity().onBackPressed();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter wifiFilter = new IntentFilter();
        wifiFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
