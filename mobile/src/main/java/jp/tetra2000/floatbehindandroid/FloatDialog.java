package jp.tetra2000.floatbehindandroid;

import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class FloatDialog extends Dialog {
    private static final String TAG = "FloatDialog";

    private WebView webView;

    public FloatDialog(Context context) {
        super(context);

        init(context);
    }

    public FloatDialog(Context context, int themeResId) {
        super(context, themeResId);

        init(context);
    }

    protected FloatDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

        init(context);
    }

    private void init(Context context) {
        setContentView(R.layout.dialog_float);

        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        final Drawable wallpaperDrawable = wallpaperManager.getDrawable();
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        relativeLayout.setBackground(wallpaperDrawable);

        webView =(WebView)findViewById(R.id.xwalkWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                Log.d(TAG, url);

                view.loadUrl(url);
                return true;
            }
        });
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.loadUrl("http://floatbehind.japaneast.cloudapp.azure.com:3000/oauth/slack", null);
    }
}
