package ca.centennial.comp304.lab3.bastian.bastias

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ca.centennial.comp304.lab3.bastian.bastias.databinding.ActivityExercise1Binding


class Exercise1Activity:AppCompatActivity() {
    private lateinit var binding: ActivityExercise1Binding

    private lateinit var reusableImageView: ImageView
    private lateinit var textView: TextView

    //
    private var startx = 10
    private var starty = 10
    private var endx = 300
    private var endy = 300

    //
    private lateinit var canvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private lateinit var bitmap: Bitmap
    //
    // Set up the paint with which to draw.
    private val paint = Paint().apply {
        color = Color.MAGENTA
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        style = Paint.Style.STROKE // default: FILL
        strokeWidth = 10f // default: Hairline-width (really thin)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExercise1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //creating a bitmap as content view for the image
        bitmap = Bitmap.createBitmap(
            windowManager
                .defaultDisplay.width, windowManager
                .defaultDisplay.height, Bitmap.Config.ARGB_8888
        )
        //tell canvas about the content view
        canvas = Canvas(bitmap)
        //set the background for your drawings
        canvas.drawColor(Color.CYAN) //background
        //setup the image view
        reusableImageView = findViewById<View>(R.id.ImageViewForDrawing) as ImageView
        //tell image view for the bitmap format/content
        reusableImageView.setImageBitmap(bitmap)
        reusableImageView.setVisibility(View.VISIBLE)
        textView = findViewById<View>(R.id.textView1) as TextView
        textView!!.text = resources.getString(R.string.help)
        //
        //reusableImageView.setImageResource(R.drawable.green_rect);
        //spinner behaviour
        val thicknessArray = resources.getIntArray(R.array.thickness_values)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, thicknessArray.map { it.toString() })
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedValue = thicknessArray[position]
                // Handle the selected integer value
                Toast.makeText(this@Exercise1Activity, "Selected value: $selectedValue", Toast.LENGTH_SHORT).show()
                paint.strokeWidth= selectedValue.toFloat()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle when nothing is selected
            }
        }

    } // end of onCreate

    fun clearCanvas(v: View?) {
        canvas?.drawColor(Color.CYAN)
        startx = 10
        starty = 10
        endx = 300
        endy = 300
        //textView!!.text = resources.getString(R.string.help)
    }

    fun startDrawing(v: View?) {
        canvas.drawPoint(15f, 15f, paint!!)
    }

    fun drawLine(canvas: Canvas) {
        textView.text = endy.toString()
        //canvas.drawLine(100,100,300,300,paint);
        canvas.drawLine(startx.toFloat(), starty.toFloat(), endx.toFloat(), endy.toFloat(), paint!!)
        startx = endx
        starty = endy
    }

    //Activate the DPAD on emulator:
    //change the settings in config.ini file in .android folder
    //hw.dPad=yes
    //hw.mainKeys=yes
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                //reusableImageView.setVisibility(View.VISIBLE);
                //reusableImageView.setFocusable(true);
                //reusableImageView.requestFocus();
                endy = endy + 5
                drawLine(canvas)
                //moveRect(canvas);
                //reusableImageView.invalidate();
                return true
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                //reusableImageView.setVisibility(View.VISIBLE);
                reusableImageView.setFocusable(true)
                reusableImageView.requestFocus()
                endx = endx + 5
                drawLine(canvas)
                //moveRect(canvas);
                reusableImageView.invalidate()
                return true
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                //reusableImageView.setVisibility(View.VISIBLE);
                reusableImageView.setFocusable(true)
                reusableImageView.requestFocus()
                endx = endx - 5
                drawLine(canvas)
                //moveRect(canvas);
                reusableImageView!!.invalidate()
                return true
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                //reusableImageView.setVisibility(View.VISIBLE);
                reusableImageView.setFocusable(true)
                reusableImageView.requestFocus()
                endy = endy - 5
                drawLine(canvas)
                //moveRect(canvas);
                reusableImageView!!.invalidate()
                return true
            }
        }
        return false
    }
}