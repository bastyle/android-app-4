package ca.centennial.comp304.lab3.bastian.bastias

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class DateTextView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    AppCompatTextView(context, attrs, defStyleAttr) {

    private var longDate = 0L

    var date: Long
        get() = longDate
        set(date){
            longDate = date
            text = longDate.toString()
        }

    companion object {

    }

    init {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.PriceTextView,  // The <declare-styleable> name
            defStyleAttr,
            0
        ) // An optional R.style to use for default values
        if (a.hasValue(R.styleable.PriceTextView_longDate)) {
            date = a.getString(R.styleable.PriceTextView_longDate)?.toLong() ?: 0L // default value
        }
        a.recycle() //recycles the TypedArray
    }

}