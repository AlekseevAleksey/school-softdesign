package com.softdesign.school.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;


public class RoundImage {

    public static Bitmap getCirle(Bitmap bm, int size) {

        int height = bm.getHeight();
        int left = (int) (bm.getWidth() - height) / 2;
        Bitmap bmOverlay = Bitmap.createBitmap(bm, left, 0, height, height);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bmOverlay, size, size, false);
        return getRoundedBitmap(resizedBitmap, resizedBitmap.getHeight());
    }

    private static Bitmap getRoundedBitmap(Bitmap bitmap, float radius) {

     Bitmap inpBitmap = bitmap;
     int width = 0;
     int height = 0;
     width = inpBitmap.getWidth();
     height = inpBitmap.getHeight();

     if (width <= height) {
     height = width;
     } else {
     width = height;
     }

     Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
     Canvas canvas = new Canvas(output);

     final Paint paint = new Paint();
     final Rect rect = new Rect(0, 0, width, height);
     final RectF rectF = new RectF(rect);
     final float roundPx = height;

     paint.setAntiAlias(true);
     canvas.drawARGB(0, 0, 0, 0);
     paint.setColor(Color.WHITE);
     canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

     paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
     canvas.drawBitmap(inpBitmap, rect, rect, paint);

     return output;
     }
}