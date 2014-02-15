package com.nicktee.kick;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_web)
@OptionsMenu(R.menu.activity_main)
public class WebActivity extends SherlockFragmentActivity {
	
	@ViewById
	WebView webView;
	
	
	@AfterViews
	void afterView(){
		setWebView();
	}
	
	void setWebView(){
		String url ="";
		Bundle extras = getIntent().getExtras(); 
		if(extras !=null) {
		   url  = extras.getString("Url");
		}
        webView.setWebViewClient(new WebViewClient());
		webView.loadUrl(url);
	}
	
	

}
