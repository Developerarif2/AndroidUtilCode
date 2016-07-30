# ��Ļ���
### ��ȡ�ֻ��ֱ���
``` java
/**
* ��ȡ��Ļ�Ŀ��px
*/
public static int getDeviceWidth(Context context) {
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics outMetrics = new DisplayMetrics();// ������һ�Ű�ֽ
    windowManager.getDefaultDisplay().getMetrics(outMetrics);// ����ֽ���ÿ��
    return outMetrics.widthPixels;
}

/**
* ��ȡ��Ļ�ĸ߶�px
*/
public static int getDeviceHeight(Context context) {
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics outMetrics = new DisplayMetrics();// ������һ�Ű�ֽ
    windowManager.getDefaultDisplay().getMetrics(outMetrics);// ����ֽ���ÿ��
    return outMetrics.heightPixels;
}
```

### ��ȡ״̬���߶�
```
/**
* ��ȡ״̬���߶�
*/
public int getStatusBarHeight() {
    int result = 0;
    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
        result = getResources().getDimensionPixelSize(resourceId);
    }
    return result;
}
```

### ��ȡ״̬���߶ȣ�������(ActionBar)�߶�
``` java
/**
* ��ȡ״̬���߶ȣ�������(ActionBar)�߶�
*/
public static int getTopBarHeight(Activity activity) {
    return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
}
```

### ��ȡ��Ļ��ͼ
``` java
/**
* ��ȡ��ǰ��Ļ��ͼ������״̬��
*/
public static Bitmap snapShotWithStatusBar(Activity activity) {
    View view = activity.getWindow().getDecorView();
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap bmp = view.getDrawingCache();
    int width = getScreenWidth(activity);
    int height = getScreenHeight(activity);
    Bitmap bp = null;
    bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
    view.destroyDrawingCache();
    return bp;
}

/**
* ��ȡ��ǰ��Ļ��ͼ��������״̬��
*/
public static Bitmap snapShotWithoutStatusBar(Activity activity) {
    View view = activity.getWindow().getDecorView();
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap bmp = view.getDrawingCache();
    Rect frame = new Rect();
    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    int statusBarHeight = frame.top;
    int width = getScreenWidth(activity);
    int height = getScreenHeight(activity);
    Bitmap bp = null;
    bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
            - statusBarHeight);
    view.destroyDrawingCache();
    return bp;
}
```

### ����͸��״̬��������setContentView֮ǰ����
``` java
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    //͸��״̬��
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    //͸��������
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
}

// ���ڶ����ؼ������м����������������ݳ�����״̬��֮��
android:clipToPadding="true" 
android:fitsSystemWindows="true"
```
