package kang.base.corner

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.View


/**
 * Created by zhangkb on 2017/9/26 0026.
 */
object SelectorUtils {
    private const val TAG = "SelectorUtils"
    fun setBackground(context: Context, attrs: AttributeSet?, view: View) {
        //从xml那传来的一组值
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.button_selector)

        //选中
        val selectedColor = typedArray.getColor(
            R.styleable.button_selector_selectedColor,
            context.resources.getColor(R.color.color_style_btn_selected)
        )
        val pressedColor: Int
        //点击
        pressedColor =
            if (selectedColor == context.resources.getColor(R.color.color_style_btn_selected)) {
                typedArray.getColor(
                    R.styleable.button_selector_pressedColor,
                    context.resources.getColor(R.color.color_style_btn_normal)
                )
            } else {
                typedArray.getColor(R.styleable.button_selector_pressedColor, selectedColor)
            }

        //enable false
        val enabledColor = typedArray.getColor(
            R.styleable.button_selector_enabledColor,
            context.resources.getColor(R.color.color_btn_enable_false)
        )
        val r = typedArray.getDimensionPixelSize(R.styleable.button_selector_radius, 6).toFloat()
        typedArray.recycle()
        //  float radius = Common.dip2px(context, r);
        // Logger.getLogger().logD(TAG, "radius--:" + radius);
        setBackground(view, selectedColor, pressedColor, enabledColor, r)
    }

    fun setBackground(
        view: View,
        selectedColor: Int,
        pressedColor: Int,
        enabledColor: Int,
        radius: Float
    ) {
        val drawable = StateListDrawable()
        val selectedBg = GradientDrawable()
        val pressedBg = GradientDrawable()
        val enabledBg = GradientDrawable()


        // The corner radii are specified in clockwise order (see Path.addRoundRect())


        //selectedBg.cornerRadius = radius

        //上下左右
        val  bg=floatArrayOf(
            radius.toFloat(), radius.toFloat(),
        0.toFloat(), 0.toFloat(),
        0.toFloat(), 0.toFloat(),
        0.toFloat(), 0.toFloat()
        )

        selectedBg.cornerRadii = bg
        pressedBg.cornerRadius = radius
        enabledBg.cornerRadius = radius
        selectedBg.setColor(selectedColor)
        pressedBg.setColor(pressedColor)
        enabledBg.setColor(enabledColor)
        drawable.addState(intArrayOf(android.R.attr.state_selected), selectedBg)
        drawable.addState(intArrayOf(android.R.attr.state_pressed), pressedBg)
        drawable.addState(intArrayOf(android.R.attr.state_enabled), selectedBg)
        drawable.addState(intArrayOf(), enabledBg)
        view.background = drawable
    }
}