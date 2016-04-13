package jp.tetra2000.floatbehindandroid

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.view.ViewGroup

import org.xwalk.core.XWalkPreferences
import org.xwalk.core.XWalkView

class MainActivity : AppCompatActivity() {
    private var xWalkWebView: XWalkView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, true)

        val rootLayout = findViewById(R.id.container) as ViewGroup
        xWalkWebView = XWalkView(this)
        xWalkWebView!!.setBackgroundColor(Color.TRANSPARENT)
        xWalkWebView!!.load("https://crosswalk-project.org", null)
        rootLayout.addView(xWalkWebView)

        xWalkWebView!!.setBackgroundColor(Color.TRANSPARENT)
        val tuv = findXWalkTextureView(xWalkWebView)
        tuv.setOpaque(false)
    }

    private fun findXWalkTextureView(group: ViewGroup): TextureView? {
        val childCount = group.childCount
        for (i in 0..childCount - 1) {
            val child = group.getChildAt(i)
            if (child is TextureView) {
                val parentClassName = child.getParent().javaClass.toString()
                val isRightKindOfParent = parentClassName.contains("XWalk")
                if (isRightKindOfParent) {
                    return child
                }
            } else if (child is ViewGroup) {
                val textureView = findXWalkTextureView(child)
                if (textureView != null) {
                    return textureView
                }
            }
        }
        return null
    }
}
