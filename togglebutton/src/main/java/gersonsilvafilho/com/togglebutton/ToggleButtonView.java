package gersonsilvafilho.com.togglebutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ToggleButton;

/**
 * Created by Gerson Silva Filho on 01.04.19.
 */
public class ToggleButtonView extends ToggleButton
{
    public ToggleButtonView(final Context context)
    {
        super(context);
        init(context, null);
    }


    public ToggleButtonView(final Context context, @Nullable final AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }


    public ToggleButtonView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ToggleButtonView(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    public void init(final Context context, final AttributeSet attrs)
    {
        setBackground();
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToggleButtonView);
        setDrawablesColors(typedArray);
        setTextColors(typedArray);
    }


    private void setBackground()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            setBackground(getResources().getDrawable(R.drawable.background, null));
        }
        else
        {
            setBackground(getResources().getDrawable(R.drawable.background));
        }
    }


    private void setTextColors(final TypedArray typedArray)
    {
        final int checkedTextColor = typedArray.getColor(R.styleable.ToggleButtonView_checkedTextColor, getResources().getColor(R.color.defaultCheckedColor));
        final int uncheckedTextColor = typedArray.getColor(R.styleable.ToggleButtonView_uncheckedTextColor, getResources().getColor(R.color.defaultUncheckedColor));

        final ColorStateList colorStateList = new ColorStateList(
            new int[][] {
                new int[] {android.R.attr.state_checked},
                new int[] {-android.R.attr.state_checked}
            },
            new int[] {
                checkedTextColor,
                uncheckedTextColor
            }
        );

        setTextColor(colorStateList);
    }


    private void setDrawablesColors(final TypedArray typedArray)
    {
        final int toggleOnColor = typedArray.getColor(R.styleable.ToggleButtonView_checkedColor, getResources().getColor(R.color.defaultCheckedColor));
        final int toggleOnBorderColor = typedArray.getColor(R.styleable.ToggleButtonView_checkedBorderColor, toggleOnColor);

        final int toggleOffColor = typedArray.getColor(R.styleable.ToggleButtonView_uncheckedColor, getResources().getColor(R.color.defaultUncheckedColor));
        final int toggleOffBorderColor = typedArray.getColor(R.styleable.ToggleButtonView_uncheckedBorderColor, toggleOffColor);

        final float borderWidth = typedArray.getDimension(R.styleable.ToggleButtonView_borderWidth, 4.0f);
        final float radius = typedArray.getDimension(R.styleable.ToggleButtonView_radius, 15.0f);

        final StateListDrawable stateListDrawable = (StateListDrawable) getBackground();
        final DrawableContainer.DrawableContainerState dcs = (DrawableContainer.DrawableContainerState) stateListDrawable.getConstantState();
        final Drawable[] drawableItems = dcs.getChildren();
        final GradientDrawable unChecked = (GradientDrawable) drawableItems[0];
        final GradientDrawable checked = (GradientDrawable) drawableItems[1];

        setDrawableColors(checked, borderWidth, toggleOnBorderColor, radius);
        setDrawableColors(unChecked, borderWidth, toggleOffBorderColor, radius);
    }


    private void setDrawableColors(final GradientDrawable checkedDrawable, final float borderWidth, final int onOrderColor, final float radius)
    {
        checkedDrawable.setStroke(Math.round(borderWidth), onOrderColor);
        checkedDrawable.setColor(onOrderColor);
        checkedDrawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, getResources().getDisplayMetrics()));
    }
}

