# �ߴ����
### dp��pxת��
``` java
/**
* dpתpx
*/
public static int dp2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
}

/**
* pxתdp
*/
public static int px2dp(Context context, float pxValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
}
```
 
### sp��pxת��
``` java
/**
* spתpx
*/
public static int sp2px(Context context, float spValue) {
    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (spValue * fontScale + 0.5f);
}

/**
* pxתsp
*/
public static int px2sp(Context context, float pxValue) {
    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (pxValue / fontScale + 0.5f);
}
```

### ���ֵ�λת��
``` java
// �÷���������TypedValue
/**
* ���ֵ�λת��
*/
public static float applyDimension(int unit, float value, DisplayMetrics metrics) {
    switch (unit) {
        case TypedValue.COMPLEX_UNIT_PX:
            return value;
        case TypedValue.COMPLEX_UNIT_DIP:
            return value * metrics.density;
        case TypedValue.COMPLEX_UNIT_SP:
            return value * metrics.scaledDensity;
        case TypedValue.COMPLEX_UNIT_PT:
            return value * metrics.xdpi * (1.0f / 72);
        case TypedValue.COMPLEX_UNIT_IN:
            return value * metrics.xdpi;
        case TypedValue.COMPLEX_UNIT_MM:
            return value * metrics.xdpi * (1.0f / 25.4f);
    }
    return 0;
}
```

### ��onCreate()���ɻ�ȡView�Ŀ��
``` java
/**
* ��onCreate()���ɻ�ȡView�Ŀ��
*/
public static int[] getViewMeasure(View view) {
    int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    view.measure(widthMeasureSpec, heightMeasureSpec);
    return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
}
```

### ListView����ǰ����View�ߴ�
``` java
// ֪ͨ�����֣�ռ�õĿ��ߣ�
/**
* ListView����ǰ����View�ߴ磬��headerView
*/
private void measureView(View view) {
    ViewGroup.LayoutParams p = view.getLayoutParams();
    if (p == null) {
        p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
    int height;
    int tempHeight = p.height;
    if (tempHeight > 0) {
        height = MeasureSpec.makeMeasureSpec(tempHeight,
                MeasureSpec.EXACTLY);
    } else {
        height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
    }
    view.measure(width, height);
}
```
