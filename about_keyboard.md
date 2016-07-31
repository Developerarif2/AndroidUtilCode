# �������
### �������뷨����ڵ�
``` java
// ��manifest.xml��activity������
android:windowSoftInputMode="stateVisible|adjustResize"
```

### ��̬���������
``` java
/**
* ��̬���������
*/
public static void hideSoftInput(Activity activity) {
    View view = activity.getWindow().peekDecorView();
    if (view != null) {
        InputMethodManager inputmanger = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

/**
* ��̬���������
*/
public static void hideSoftInput(Context context, EditText edit) {
    edit.clearFocus();
    InputMethodManager inputmanger = (InputMethodManager) context
            .getSystemService(Context.INPUT_METHOD_SERVICE);
    inputmanger.hideSoftInputFromWindow(edit.getWindowToken(), 0);
}
```

###  �����Ļ�հ��������������
``` java
// ����1����onTouch�д���δ�񽹵�������
/**
* ��onTouch�д���δ�񽹵�������
*/
@Override
public boolean onTouchEvent(MotionEvent event) {
    if (null != this.getCurrentFocus()) {
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }
    return super.onTouchEvent(event);
}

// ����2������EditText����������û������������Աȣ����ж��Ƿ����ؼ��̣�����дdispatchTouchEvent
@Override
public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
        View v = getCurrentFocus();
        if (isShouldHideKeyboard(v, ev)) {
            hideKeyboard(v.getWindowToken());
        }
    }
    return super.dispatchTouchEvent(ev);
}

/**
* ����EditText����������û������������Աȣ����ж��Ƿ����ؼ���
*/
private boolean isShouldHideKeyboard(View v, MotionEvent event) {
    if (v != null && (v instanceof EditText)) {
        int[] l = {0, 0};
        v.getLocationInWindow(l);
        int left = l[0],
                top = l[1],
                bottom = top + v.getHeight(),
                right = left + v.getWidth();
        return !(event.getX() > left && event.getX() < right
                && event.getY() > top && event.getY() < bottom);
    }
    return false;
}

/**
* ��ȡInputMethodManager�����������
*/
private void hideKeyboard(IBinder token) {
    if (token != null) {
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
```

### ��̬��ʾ�����
``` java
/**
* ��̬��ʾ�����
*/
public static void showSoftInput(Context context, EditText edit) {
    edit.setFocusable(true);
    edit.setFocusableInTouchMode(true);
    edit.requestFocus();
    InputMethodManager inputManager = (InputMethodManager) context
            .getSystemService(Context.INPUT_METHOD_SERVICE);
    inputManager.showSoftInput(edit, 0);
}
```

### �л�������ʾ���״̬
``` java
/**
* �л�������ʾ���״̬
*/
public static void toggleSoftInput(Context context, EditText edit) {
    edit.setFocusable(true);
    edit.setFocusableInTouchMode(true);
    edit.requestFocus();
    InputMethodManager inputManager = (InputMethodManager) context
            .getSystemService(Context.INPUT_METHOD_SERVICE);
    inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
}
```
