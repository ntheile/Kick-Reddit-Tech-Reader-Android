package com.nicktee.kick;

import java.util.ArrayList;
import java.util.List;
import models.Reddit;
import services.DatabaseManager;
import services.RedditService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.activity_main)
public class MainActivity extends SherlockFragmentActivity {

	@ViewById
	ListView listViewToDo;
	
	@ViewById
	ProgressBar progress;
	
	@Extra
    String url;
	
	public List<Reddit> reddits = new ArrayList<Reddit>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // For all ormlite calls use the DatabaseManager
        DatabaseManager.init(this);
    }
		
	@AfterViews
	void afterViews() {
		getCachedReddits();
		getRedditInBackground();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater();
		return true;
	}

	
	@OptionsItem
	void menu_settingsSelected() {
		// do work when settings is clicked
	}

	@OptionsItem
	void menu_refreshSelected() {
		getRedditInBackground();	
	}

	@OptionsItem
	void menu_redditSelected() {
		// do work when reddit is clicked
	}
	
	@Bean
	RedditService redditService;
	
	@Background
	void getRedditInBackground() {
		this.reddits = redditService.getRedditsList();
		populateRedditCache(this.reddits);
		showResult(this.reddits, true);
	}


	@UiThread
	void showResult(List<Reddit> redditList, boolean shouldHideProgress) {
		// load adapter
		RedditArrayAdapter adapter = new RedditArrayAdapter(this, R.layout.row_reddit, redditList);
		listViewToDo.setAdapter(adapter);
		// hide progress bar
		if (shouldHideProgress){
			progress.setVisibility(View.GONE) ;	
		}
	}
	

	@ItemClick
	protected void listViewToDoItemClicked(Reddit selectedRedditItem) {
		// open browser with article url
		String url = selectedRedditItem.getUrl();
		Intent i = new Intent(getApplicationContext(), WebActivity_.class);
		i.putExtra("Url", url);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}
	
	@Background
	void getCachedReddits(){
		this.reddits = DatabaseManager.getInstance().getAllReddits();
		showResult(this.reddits, false);
    }
	
	void populateRedditCache(List<Reddit> rs){
		// delete all the old reddits
		DatabaseManager.getInstance().deleteReddits();
		// add all the new reddits
		for (Reddit red : rs){
			DatabaseManager.getInstance().addReddit(red);
		}
	}
    
}
