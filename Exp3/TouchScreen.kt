package com.example.drawexp3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TouchScreen : View {
    var paint = Paint()
    var path = Path()
    var flag = 0
    var x1: Float = 0.0f
    var y1: Float = 0.0f
    var x2: Float = 0.0f
    var y2: Float = 0.0f

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        paint.color=Color.RED
        paint.isAntiAlias=true
        paint.strokeJoin=Paint.Join.ROUND
        paint.style=Paint.Style.STROKE
        paint.strokeWidth=5f
    }
    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        canvas.drawPath(path,paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(flag==2){
            val X:Float=event.getX()
            val Y:Float=event.getY()
            when(event.action){
                MotionEvent.ACTION_DOWN->{
                    path.moveTo(X,Y)
                    return true
                }
                MotionEvent.ACTION_MOVE->{
                    path.lineTo(X,Y)
                }
                MotionEvent.ACTION_UP->{

                }
                else->{
                    return false
                }
            }
        }
        if(flag==0||flag==1){
            when(event.action){
                MotionEvent.ACTION_DOWN->{
                    x1=event.getX()
                    y1=event.getY()
                    return true
                }
                MotionEvent.ACTION_MOVE->{
                    return false
                }
                MotionEvent.ACTION_UP->{
                    x2=event.getX()
                    y2=event.getY()
                    val rectF=RectF(x1,y1,x2,y2)
                    if(flag==0)
                        path.addOval(rectF,Path.Direction.CCW)
                    if(flag==1)
                        path.addRect(rectF,Path.Direction.CCW)
                }
                else-> return false
            }
        }
        if (flag == 4) {
            path.reset()
        }
        invalidate()
        return true
    }
    fun setDrawint(F:Int){
        flag=F

        if (flag == 4) {
            path.reset()
            invalidate()
        }
    }
    fun startDrawing(){
        path.rewind()
        invalidate()
    }
}