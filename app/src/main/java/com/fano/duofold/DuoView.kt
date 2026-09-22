package com.fano.duofold
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.window.layout.FoldingFeature
import kotlin.math.min
class DuoView(context:Context):View(context){
 private val p=Paint(Paint.ANTI_ALIAS_FLAG); private var progress=0f; private var lastWide:Boolean?=null; private var fold:FoldingFeature?=null
 fun setFoldFeature(value:FoldingFeature?){fold=value;invalidate()}
 override fun onSizeChanged(w:Int,h:Int,oldw:Int,oldh:Int){val wide=w.toFloat()/h>.72f;if(lastWide!=null&&wide!=lastWide)animateTo(if(wide)1f else 0f)else progress=if(wide)1f else 0f;lastWide=wide}
 private fun animateTo(target:Float){ValueAnimator.ofFloat(progress,target).apply{duration=720;interpolator=DecelerateInterpolator();addUpdateListener{progress=it.animatedValue as Float;invalidate()};start()}}
 override fun onDraw(c:Canvas){super.onDraw(c);val w=width.toFloat();val h=height.toFloat();val s=min(w,h)
  p.shader=LinearGradient(0f,0f,w,h,Color.rgb(10,14,28),Color.rgb(70,18,45),Shader.TileMode.CLAMP);c.drawRect(0f,0f,w,h,p);p.shader=null
  val hx=w/2f;p.shader=LinearGradient(hx-w*.08f,0f,hx+w*.08f,0f,intArrayOf(Color.TRANSPARENT,Color.argb((105*(1-progress)).toInt(),0,0,0),Color.TRANSPARENT),null,Shader.TileMode.CLAMP);c.drawRect(hx-w*.09f,0f,hx+w*.09f,h,p);p.shader=null
  val r=s*.047f*(.86f+.14f*progress);for(idx in 0 until 8){val row=idx/4;val col=idx%4;val sp=w/5f;val bx=sp*(col+1);val side=if(bx<hx)-1 else 1;val x=bx+side*w*.035f*(1-progress);val y=h*.27f+row*s*.19f;p.color=Color.rgb(80+(idx*23)%160,90+(idx*31)%150,120+(idx*17)%130);c.drawCircle(x,y,r,p);p.color=Color.WHITE;p.textAlign=Paint.Align.CENTER;p.textSize=s*.025f;c.drawText("App "+(idx+1),x,y+r+s*.04f,p)}
  p.color=Color.argb(100,255,255,255);c.drawRoundRect(RectF(w*.12f,h*.82f,w*.88f,h*.93f),s*.04f,s*.04f,p)
  p.color=Color.WHITE;p.textAlign=Paint.Align.LEFT;p.textSize=s*.025f;val state=fold?.state?.toString()?:if(progress>.5f)"OPEN" else "COVER";c.drawText("DUO • "+state,s*.04f,s*.06f,p)
 }
}