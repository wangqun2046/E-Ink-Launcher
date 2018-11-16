package info.wangqun.launcher;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;

import java.util.*;

/**
 * Created by mod on 16-5-6.
 */
public class Utils {

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    static int dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    static String getAMPMCNString(int hours, int ampm) {
        if (ampm == Calendar.AM) {
            if (hours < 5) {
                return sAmPmCN[0];
            } else if (hours < 7) {
                return sAmPmCN[1];
            } else if (hours < 9) {
                return sAmPmCN[2];
            } else if (hours < 12) {
                return sAmPmCN[3];
            } else {
                return sAmPmCN[0];
            }
        } else {
            if (hours == 0) {
                return sAmPmCN[4];
            } else if (hours < 6) {
                return sAmPmCN[5];
            } else if (hours <= 9) {
                return sAmPmCN[6];
            } else if (hours < 12) {
                return sAmPmCN[7];
            } else if (hours == 12) {
                return sAmPmCN[4];
            } else {
                return sAmPmCN[4];
            }
        }
    }

    private static final String[] sAmPmCN = new String[]{
        "凌晨", "黎明", "早晨", "上午", "中午", "下午", "晚上", "深夜"
    };

}
