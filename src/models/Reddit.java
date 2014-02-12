package models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reddit {

	// constructor
	public Reddit() {
		super();
	}

	// properties
	@JsonProperty("url")
	private String url;

	@JsonProperty("selftext")
	private String selftext;

	@JsonProperty("thumbnail")
	private String thumbnail;
	
	@JsonProperty("author_flair_text")
	private String author_flair_text;
	
	@JsonProperty("title")
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// getter and setters
	public String getAuthor_flair_text() {
		return author_flair_text;
	}

	public void setAuthor_flair_text(String author_flair_text) {
		this.author_flair_text = author_flair_text;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getSelfText() {
		return selftext;
	}

	public void setSelfText(String selftext) {
		this.selftext = selftext;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return this.url;
	}

}
