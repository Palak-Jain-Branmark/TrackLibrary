package in.branmark.track_webview;

import android.content.Context;
import android.webkit.WebView;

public class WebViewBridge {

    public static WebViewBridgeInstance register(Context context, WebView webView){
        //Start the default instance of WebViewBridgeInstance by calling WebViewBridge.register(getApplication(), webview)
        WebViewBridgeInstance webViewBridgeInstance = new WebViewBridgeInstance(context,webView);

        return webViewBridgeInstance;
    }
}
