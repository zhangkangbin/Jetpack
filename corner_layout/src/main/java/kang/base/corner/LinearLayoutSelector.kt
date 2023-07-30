package kang.base.corner

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kang.base.corner.SelectorUtils.setBackground


/**
 * Created by zhangkb on 2017/9/25 0025.
 * 设置LinearLayout选择和默认的颜色
 * LinearLayout的圆度radius
 */

class LinearLayoutSelector : LinearLayout {
    constructor(context: Context?) : super(context) {}

    private val TAG = this.javaClass.simpleName

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        setBackground(context, attrs, this)
    }
}