package com.nicktee.kick;

import java.util.ArrayList;
import java.util.List;

import models.Reddit;

import org.codehaus.jackson.JsonNode;

import services.RedditService;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.fragment_reddit)
@OptionsMenu(R.menu.activity_main)
public class MainActivity extends SherlockActivity {

	@ViewById
	TextView textView1;

	@ViewById
	ListView listViewToDo;
	
	@ViewById
	ProgressBar progress;
	
	public List<Reddit> reddits = new ArrayList<Reddit>();
	
	
	@AfterViews
	void afterViews() {
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
	void menu_item1Selected() {
		// do work when item1 is clicked
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
		showResult(this.reddits);
	}


	@UiThread
	void showResult(List<Reddit> redditList) {
		// load adapter
		RedditArrayAdapter adapter = new RedditArrayAdapter(this, R.layout.row_reddit, redditList);
		listViewToDo.setAdapter(adapter);
		// hide progress bar
		progress.setVisibility(View.GONE) ;
	}
	
	@ItemClick
	protected void listViewToDoItemClicked(Reddit selectedRedditItem) {
		// open browser with article url
		String url = selectedRedditItem.getUrl();
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

}
