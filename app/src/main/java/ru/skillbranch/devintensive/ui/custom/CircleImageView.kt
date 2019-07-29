package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.content.ContextCompat
import ru.skillbranch.devintensive.R


class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr:Int = 0
) : ImageView(context,attrs,defStyleAttr) {
    companion object {
        private const val DEFAULT_BORDER_COLOR = Color.WHITE
        private const val DEFAULT_BORDER_WIDTH = 2
    }
    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH
    init {
        if(attrs!=null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_BORDER_WIDTH)
            borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)
            a.recycle()
        }
    }

    fun getBorderWidth():Int = borderWidth

    fun setBorderWidth(dp:Int){
        if (dp == borderWidth){
            return
        }
        this.invalidate()
    }


    fun getBorderColor():Int = borderColor

    fun setBorderColor(hex:String){
        if(Color.parseColor(hex)== borderColor) {
            return
        }
        this.invalidate()
    }

    fun setBorderColor(colorId: Int){
        if(ContextCompat.getColor(context,colorId) == borderColor) {
            return
        }
        this.invalidate()
    }

}