package com.lcj.commonlib.view.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class SupportRefreshRecyclerView extends RecyclerView {
    private NestedScrollingChildHelper childHelper;
    private float ox;
    private float oy;
    private int[] consumed = new int[2];
    private int[] offsetInWindow = new int[2];

    private void init() {
        setWillNotDraw(false);
        childHelper = new NestedScrollingChildHelper(this);
        childHelper.setNestedScrollingEnabled(true);
    }

    public SupportRefreshRecyclerView(Context context) {
        super(context);
      //  init();
    }

    public SupportRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
       // init();
    }

    public SupportRefreshRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
       // init();
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            ox = ev.getX();
            oy = ev.getY();
            //startNestedScroll(3);
            startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
        }

        if (ev.getAction() == 1 || ev.getAction() == 3) {
            stopNestedScroll();
        }

        if (ev.getAction() == 2) {
            float clampedX = ev.getX();
            float clampedY = ev.getY();
            int dx = (int) (ox - clampedX);
            int dy = (int) (oy - clampedY);
            if (dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)) {
                ev.setLocation(clampedX + (float) consumed[0], clampedY + (float) consumed[1]);
            }

            ox = ev.getX();
            oy = ev.getY();
        }

        return super.onTouchEvent(ev);
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        if(childHelper ==null ){
            init();
        }
        childHelper.setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return childHelper.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int axes) {
        return childHelper.startNestedScroll(axes);
    }

    public void stopNestedScroll() {
        childHelper.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        return childHelper.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return childHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return childHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return childHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return dispatchNestedPreFling(velocityX, velocityY);
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean fling(int velocityX, int velocityY) {
        super.fling(velocityX, velocityY);
        return true;
    }

//    public void addHeadView(View view) {
//        //addHeadView(view);
//    }

}
