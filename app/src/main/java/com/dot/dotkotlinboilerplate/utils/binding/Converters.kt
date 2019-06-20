package com.dot.dotkotlinboilerplate.utils.binding

import android.animation.Animator
import androidx.databinding.BindingAdapter
import androidx.appcompat.widget.AppCompatImageView
import android.view.View
import com.bumptech.glide.GenericTransitionOptions
import com.dot.dotkotlinboilerplate.data.AppConstants

import com.dot.dotkotlinboilerplate.utils.glide.GlideApp

class Converters {

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: AppCompatImageView, imageUrl: String) {
            GlideApp.with(imageView.context.applicationContext)
                    .load(imageUrl)
                    .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                    .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("showView")
        fun showView(view: View, hide: Boolean){
            if(hide) {
                view.alpha = 0f
                view.animate().alpha(1f).duration = AppConstants.GLOBAL_ANIM_DURATION
            }
        }

        @JvmStatic
        @BindingAdapter("hideView")
        fun hideView(view: View, hide: Boolean){
            if (hide) {
                view.visibility = View.VISIBLE
                view.alpha = 1f
            } else {
                view.animate().alpha(0f).setDuration(AppConstants.GLOBAL_ANIM_DURATION).setListener(object : Animator.AnimatorListener {

                    override fun onAnimationRepeat(p0: Animator?) {

                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        view.visibility = View.INVISIBLE
                    }

                    override fun onAnimationStart(p0: Animator?) {

                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                })
            }
        }

    }

}
