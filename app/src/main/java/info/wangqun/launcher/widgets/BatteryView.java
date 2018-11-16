package info.wangqun.launcher.widgets;

import android.content.Context;
import android.graphics.*;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class BatteryView extends View {
    Paint circlePaint;
    TextPaint textPaint;
    int maxProgress = 100;
    int progress = 0;

    public BatteryView(Context context) {
        super(context);
        init();
    }

    public BatteryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BatteryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAntiAlias(true);
        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = getWidth() > getHeight() ? getHeight() : getWidth();
        circlePaint.setStrokeWidth(size / 10);
        circlePaint.setColor(Color.LTGRAY);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, (size - circlePaint.getStrokeWidth()) / 2, circlePaint);
        RectF rectF = new RectF(
            (getWidth() - size + circlePaint.getStrokeWidth()) / 2,
            (getHeight() - size + circlePaint.getStrokeWidth()) / 2,
            (getWidth() - size - circlePaint.getStrokeWidth()) / 2 + size,
            (getHeight() - size - circlePaint.getStrokeWidth()) / 2 + size
        );
        circlePaint.setColor(Color.BLACK);
        canvas.drawArc(rectF, -90, progress * 1f / maxProgress * 360, false, circlePaint);
        textPaint.setTextSize(size / 2.8f);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        String showText = String.format("%02d", (int) Math.round(progress * 1f / maxProgress * 100));
        Rect rect = new Rect();
        textPaint.getTextBounds(showText, 0, showText.length(), rect);
        textPaint.setFakeBoldText(true);
        canvas.translate(getWidth() / 2f, getHeight() / 2f);
        canvas.drawText(showText,
            -(rect.right - rect.left) / 1.9f,
            (rect.bottom - rect.top) / 2f, textPaint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {

        } else {

        }
    }

}
