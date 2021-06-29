package com.example.semicircletofullview

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

val colors : Array<Int> = arrayOf(
    "#f44336",
    "#004D40",
    "#FFD600",
    "#00C853",
    "#6200EA"
).map {
    Color.parseColor(it)
}.toTypedArray()
val strokeFactor : Float = 90f
val sizeFactor : Float = 4.9f
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")
val rot : Float = 180f
val parts : Int = 3
val scGap : Float = 0.02f / parts

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n

fun Canvas.drawSemiCircleToFull(scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = Math.min(w, h) / sizeFactor
    val sc1 : Float = scale.divideScale(0, parts)
    val sc2 : Float = scale.divideScale(1, parts)
    val sc3 : Float = scale.divideScale(2, parts)
    save()
    translate(w / 2 + (w / 2 + size) * sc3,  h + size / 2 - (h / 2) * sc1)
    for (j in 0..1) {
        save()
        rotate(rot * j * sc2)
        drawArc(RectF(-size / 2, -size / 2, size / 2, size / 2), 0f, rot, true, paint)
        restore()
    }
    restore()
}

fun Canvas.drawSCTFUNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawSemiCircleToFull(scale, w, h, paint)
}

class SemiCircleToFullUpView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}