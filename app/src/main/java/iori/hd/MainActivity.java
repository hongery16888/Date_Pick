package iori.hd;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ListView的加入购物车动画
 *
 * @author OutTime
 */
public class MainActivity extends Activity {

    private int[] position = new int[2];
    private PopupWindow mPopupWindow;
    private DatePicker  datePicker;

    @InjectView(R.id.time)
    TextView time;

    @OnClick(R.id.btn)
    public void pop(View v){
        getPopupWindowInstance();
        mPopupWindow.setFocusable(true);
        mPopupWindow.showAtLocation(MainActivity.this
                //设置layout在PopupWindow中显示的位置
                .findViewById(R.id.main), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0);
//        mPopupWindow.showAsDropDown(v);
        Toast.makeText(this, "----", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    private void getPopupWindowInstance() {
        if (null != mPopupWindow) {
            mPopupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }

    private void initPopuptWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View popupWindow = layoutInflater.inflate(R.layout.date_pop_main, null);
        mPopupWindow = new PopupWindow(popupWindow, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.getContentView().measure(0, 0);
        mPopupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

        datePicker = (DatePicker)popupWindow.findViewById(R.id.datePicker);

        popupWindow.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        popupWindow.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText(datePicker.getYear() + "-" + datePicker.getMonth() + "-" + datePicker.getDay() );
                mPopupWindow.dismiss();
            }
        });
    }

}
