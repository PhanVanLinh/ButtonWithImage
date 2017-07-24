# ButtonWithImage

![alt tag](/image.png)
```
<com.toong.buttoncustom.ButtonCustom
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:bc_corner="10dp"
    app:bc_cornerTopLeft="20dp"
    app:bc_title="Button With Corner"
    />

<com.toong.buttoncustom.ButtonCustom
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="5dp"
    app:bc_borderColor="#00f"
    app:bc_borderWidth="2dp"
    app:bc_corner="10dp"
    app:bc_title="Button With Border"
    />

<!-- Button with drawable -->
<com.toong.buttoncustom.ButtonCustom
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="10dp"
    app:bc_leftDrawable="@drawable/ic_cancel_red"
    />

<com.toong.buttoncustom.ButtonCustom
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="10dp"
    app:bc_leftDrawable="@drawable/ic_cancel_red"
    app:bc_title="Button With Left Drawable"
    />

<com.toong.buttoncustom.ButtonCustom
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="10dp"
    app:bc_leftDrawable="@drawable/ic_cancel_red"
    app:bc_title="Margin Left Drawable, Title style"
    app:bc_titleColor="#f00"
    app:bc_titleMarginStart="30dp"
    app:bc_titleSize="20sp"
    />

<com.toong.buttoncustom.ButtonCustom
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="10dp"
    android:alpha="0.9"
    app:bc_disabled_alpha="0.2"
    app:bc_pressed_alpha="0.5"
    app:bc_selected_alpha="0.5"
    app:bc_title="Alpha when pressed, selected, disable"
    />

<com.toong.buttoncustom.ButtonCustom
    android:id="@+id/customview"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:layout_marginTop="50dp"
    android:alpha="0.9"
    android:padding="15dp"
    app:bc_borderColor="#FF000000"
    app:bc_borderWidth="2dp"
    app:bc_corner="10dp"
    app:bc_cornerBottomLeft="40dp"
    app:bc_cornerTopLeft="40dp"
    app:bc_cornerTopRight="40dp"
    app:bc_disableBackgroundColor="#00f"
    app:bc_disableBorderColor="#000"
    app:bc_disabled_alpha="0.8"
    app:bc_drawableHeight="40dp"
    app:bc_drawableWidth="40dp"
    app:bc_leftDrawable="@drawable/ic_cancel_red"
    app:bc_pressed_alpha="0.1"
    app:bc_selected_alpha="0.5"
    app:bc_title="All Property"
    app:bc_titleColor="#f00"
    app:bc_titleMarginStart="5dp"
    app:bc_titleSize="30sp"
    />
```