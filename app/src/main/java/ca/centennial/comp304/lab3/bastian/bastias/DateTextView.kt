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

    private var mDate = "0"

    var date: String
        get()= mDate
        set(date){
            mDate= date
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = date.toLong()
            text = dateFormatter.format(calendar.time)
        }

    companion object {
        private val CURRENCY_FORMAT = NumberFormat.getCurrencyInstance()
        private var dateFormatter: SimpleDateFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault())
    }



    init {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.PriceTextView,  // The <declare-styleable> name
            defStyleAttr,
            0
        ) // An optional R.style to use for default values
        if (a.hasValue(R.styleable.PriceTextView_date)) {
            date = a.getString(R.styleable.PriceTextView_date).toString() // default value
        }
        a.recycle() //recycles the TypedArray
    }

    fun setLongDate(year: Int, month: Int, day:Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val longDate = calendar.timeInMillis
        val longDateFormatted = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault()).format(Date(longDate))
        text = "Selected Date: $longDateFormatted\nSelected Date (Long): $longDate"
    }

    fun setLongDate(date: Long) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        text = dateFormatter.format(calendar.time)
    }
}