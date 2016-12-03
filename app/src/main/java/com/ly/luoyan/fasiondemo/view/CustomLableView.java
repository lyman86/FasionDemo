package com.ly.luoyan.fasiondemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.ly.luoyan.fasiondemo.utils.DensityUtils;
import com.ly.luoyan.fasiondemo.utils.MyWindow;
import com.ly.luoyan.fasiondemo.utils.WindowUtil;

/**
 * Created by luoyan on 16/9/7.
 */

public class CustomLableView extends View {
    private Paint paint;
    private Paint textPaint;
    private Paint circlePaint;
    private Rect bounds;
    private String defaultLableBg = "#5f000000";
    private String cicleBg = "#7f000000";

    private String text = "";

    private int textWidth;
    private MyWindow myWindow;

    private int paddingLeftRight;
    private int paddingTopBottom;
    private int circleRadius;
    private int veticalLine;
    private int line;

    private int pointX = 0;
    private int pointY = 0;

    private Derection derection = Derection.TO_LEFT;
    public CustomLableView(Context context) {
        this(context,null);


    }



    public enum Derection{
        TO_LEFT,TO_RIGHT;
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.parseColor(defaultLableBg));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);//实心

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(DensityUtils.dp2px(getContext(),10));
        bounds = new Rect();

        circlePaint = new Paint();
        circlePaint.setColor(Color.parseColor("#5fffffff"));
        circlePaint.setAntiAlias(true);
    }

    public CustomLableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        myWindow = WindowUtil.getWindow(context);
        paddingLeftRight = DensityUtils.dp2px(context,10);
        paddingTopBottom = DensityUtils.dp2px(context,12);
        circleRadius = DensityUtils.dp2px(context,3);
        veticalLine = DensityUtils.dp2px(context,2);
        line = DensityUtils.dp2px(context,2);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        setMeasuredDimension((paddingLeftRight + bounds.width() + paddingLeftRight) + paddingTopBottom,paddingTopBottom*2);

    }

    public int getTextWidth(){
        return textWidth;
    }

    public void setPoint(int pointX,int pointY){
        this.pointX = pointX;
        this.pointY = pointY;
    }
    public int getPointX(){
        return pointX;
    }

    public int getPointY(){
        return pointY;
    }

    public void setText(String text){
        this.text = text;
        postInvalidate();
    }

    public void setDerection(){
        this.derection = Derection.TO_LEFT.equals(derection)? Derection.TO_RIGHT: Derection.TO_LEFT;
        postInvalidate();
    }

    public Derection getDerection(){
        return derection;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        textWidth = bounds.width();

        if (myWindow==null)myWindow = WindowUtil.getWindow(getContext());

        switch (derection){
            case TO_LEFT: {
                Path path = new Path();
                pointY = 0;
                pointX = line;
                path.moveTo(pointX, pointY);
                path.moveTo(pointX - line, pointY+line);

                path.lineTo(pointX - line, pointY + paddingTopBottom*2-line);
                path.lineTo(pointX, pointY  + paddingTopBottom*2);

                path.lineTo(pointX + (2*paddingLeftRight + textWidth)-line, pointY + paddingTopBottom*2);
                path.lineTo(pointX + (2*paddingLeftRight + textWidth)+line, pointY -line + paddingTopBottom*2);

                path.lineTo(pointX + (2*paddingLeftRight + textWidth) + 6*paddingTopBottom/7+line, pointY+paddingTopBottom+veticalLine/5);
                path.lineTo(pointX + (2*paddingLeftRight + textWidth) + 6*paddingTopBottom/7+line, pointY+paddingTopBottom-veticalLine/5);

                path.lineTo(pointX + (2*paddingLeftRight + textWidth)+line, pointY+line);
                path.lineTo(pointX + (2*paddingLeftRight + textWidth)-3*line, pointY-line);
                path.lineTo(pointX, pointY);

                path.close();
                canvas.drawPath(path, paint);
                path = null;
                Rect rect = new Rect(pointX, pointY, pointX + (3*paddingLeftRight/2 + textWidth), pointY + paddingTopBottom*2);
                Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
                int baseline = (rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2;
                textPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(text, rect.centerX(), baseline, textPaint);
                canvas.drawCircle((2*paddingLeftRight + textWidth),paddingTopBottom,circleRadius,circlePaint);



            }
            case TO_RIGHT: {
                Path path = new Path();
                pointY = 0;
                pointX = line;
                path.moveTo(pointX, pointY);
                path.moveTo(pointX - line, pointY+line);

                path.lineTo(pointX - line, pointY + paddingTopBottom*2-line);
                path.lineTo(pointX, pointY  + paddingTopBottom*2);

                path.lineTo(pointX + (2*paddingLeftRight + textWidth)-line, pointY + paddingTopBottom*2);
                path.lineTo(pointX + (2*paddingLeftRight + textWidth)+line, pointY -line + paddingTopBottom*2);

                path.lineTo(pointX + (2*paddingLeftRight + textWidth) + 6*paddingTopBottom/7+line, pointY+paddingTopBottom+veticalLine/5);
                path.lineTo(pointX + (2*paddingLeftRight + textWidth) + 6*paddingTopBottom/7+line, pointY+paddingTopBottom-veticalLine/5);

                path.lineTo(pointX + (2*paddingLeftRight + textWidth)+line, pointY+line);
                path.lineTo(pointX + (2*paddingLeftRight + textWidth)-3*line, pointY-line);
                path.lineTo(pointX, pointY);

                path.close();
                canvas.drawPath(path, paint);
                path = null;
                Rect rect = new Rect(pointX, pointY, pointX + (3*paddingLeftRight/2 + textWidth), pointY + paddingTopBottom*2);
                Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
                int baseline = (rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2;
                textPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(text, rect.centerX(), baseline, textPaint);
                canvas.drawCircle((2*paddingLeftRight + textWidth),paddingTopBottom,circleRadius,circlePaint);
            }
        }


//        Path ciclePath = new Path();
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        ciclePath.addCircle((2*paddingLeftRight + textWidth+circleRadius/2),(paddingTopBottom+circleRadius/2),circleRadius,Path.Direction.CW);
//        canvas.clipPath(ciclePath,android.graphics.Region.Op.INTERSECT);
//        Bitmap b = Bitmap.createBitmap((2*paddingLeftRight + textWidth) + paddingTopBottom, paddingTopBottom*2, Bitmap.Config.ARGB_8888);
//        canvas.drawBitmap(b,0,0,circlePaint);
    }
}
