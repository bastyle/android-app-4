package ca.centennial.comp304.lab3.bastian.bastias

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class Exercise3Activity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise3)
        performAnimation(R.anim.translate_position)
    }

    private fun performAnimation(animationResourceID: Int) {
        // We will animate the imageview
        val reusableImageView: ImageView =
            findViewById<View>(R.id.earth) as ImageView
        reusableImageView.setImageResource(R.drawable.earth)
        reusableImageView.setVisibility(View.VISIBLE)

        // Load the appropriate animation
        val an: Animation = AnimationUtils.loadAnimation(this, animationResourceID)
        // Register a listener, so we can disable and re-enable buttons
        an.setAnimationListener(MyAnimationListener())
        // Start the animation
        an.repeatMode=Animation.RESTART
        an.repeatCount=-1
        reusableImageView.startAnimation(an)
    }

    internal inner class MyAnimationListener : Animation.AnimationListener {
        override fun onAnimationEnd(animation: Animation) {
           performAnimation(R.anim.translate_position)
        }

        override fun onAnimationRepeat(animation: Animation) {
            // what to do when animation loops
        }

        override fun onAnimationStart(animation: Animation) {
            // Disable all buttons while animation is running
            //toggleButtons(false)
        }
    }
}