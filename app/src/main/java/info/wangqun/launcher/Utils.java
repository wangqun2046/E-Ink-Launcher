package info.wangqun.launcher;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

import androidx.core.graphics.drawable.DrawableCompat;

public class Utils {

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }
}
