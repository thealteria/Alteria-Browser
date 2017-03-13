package com.thealteria.alteriabrowser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    WebView brow;
    EditText urledit;
    Button go, forward, back, clear, refresh;
    ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        brow = (WebView)findViewById(R.id.webview);
        pbar= (ProgressBar)findViewById(R.id.pbar);
        urledit= (EditText)findViewById(R.id.iurl);
        go= (Button)findViewById(R.id.btngo);
        forward= (Button)findViewById(R.id.fwd);
        back= (Button)findViewById(R.id.back);
        clear= (Button)findViewById(R.id.clr);
        refresh= (Button)findViewById(R.id.rsh);

        brow.setWebViewClient(new ourViewClient());
        brow.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pbar.setProgress(newProgress);
                if (newProgress == 100) {
                    pbar.setVisibility(View.GONE);
                } else {

                    pbar.setVisibility(View.VISIBLE);
                }
            }
        });


        WebSettings webSettings= brow.getSettings();
        webSettings.setJavaScriptEnabled(true);



        brow.loadUrl("https://www.google.co.in");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editextvalue=urledit.getText().toString();
                if(!editextvalue.startsWith("http://")) {
                    editextvalue = "http://" + editextvalue;
                    String url = editextvalue;

                    brow.loadUrl(url);
                }

                //Hide keyboard after using Editext
                InputMethodManager im= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(urledit.getWindowToken(),0);

            } });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brow.canGoForward())
                    brow.goForward();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brow.canGoBack())
                brow.goBack();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brow.clearHistory();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brow.reload();
            }
        });

    }
}
