package com.example.haa_roh.api;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

public class HAAItem extends ViewGroup {
    public HAAItem(Context context) {
        super(context);
        init(context);
    }

    public HAAItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HAAItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HAAItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    private void init(Context context) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int childCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        if (childCount == 0) {  //如果没有子元素
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {//宽/高是否采取了wrap_content
            Log.d("HAA_ZZZ", "onMeasure: "+ height);
            height = getChildAt(0).getMeasuredHeight();
            width = childCount * getChildAt(0).getMeasuredWidth();
            setMeasuredDimension(width, height);
        } else if (widthMode == MeasureSpec.AT_MOST) { //宽是否采取了wrap_content，是的话 宽是所有子元素之和
            Log.d("HAA_ZZZ", "onMeasure1: "+ height);
            width = childCount * getChildAt(0).getMeasuredWidth();
            setMeasuredDimension(width, height);
        } else if(heightMode == MeasureSpec.AT_MOST){  //高是否采取wrap_content， 是的话 高是第一个元素的高
            Log.d("HAA_ZZZ", "onMeasure:2 "+ height);
            height = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(width, height);
        }else{
            Log.d("HAA_ZZZ", "onMeasure:3 "+ height);
            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int leftOffset = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if(child.getVisibility() != View.GONE){
                Log.d("HZZ_ZZZ", "onLayout: ");
                child.layout(l + leftOffset, 0, r + leftOffset, child.getMeasuredHeight());
                leftOffset += child.getMeasuredWidth();
            }
        }
    }
}
