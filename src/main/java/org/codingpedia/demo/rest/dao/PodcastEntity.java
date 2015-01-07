package org.codingpedia.demo.rest.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.codingpedia.demo.rest.resource.podcast.Podcast;

/**
 * Podcast entity 
 * 
 * @author ama
 *
 */
@Entity
@Table(name="podcasts")
public class PodcastEntity implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;

	/** id of the podcast */
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	/** title of the podcast */
	@Column(name="title")
	private String title;
		
	/** link of the podcast on Podcastpedia.org */
	@Column(name="link_on_podcastpedia")
	private String linkOnPodcastpedia;
	
	/** url of the feed */
	@Column(name="feed")
	private String feed;
	
	/** description of the podcast */
	@Column(name="description")
	private String description; 
		
	/** insertion date in the database */
	@Column(name="insertion_date")
	private Date insertionDate;

	public PodcastEntity(){}
	
	public PodcastEntity(String title, String linkOnPodcastpedia, String feed,
			String description) {
		
		this.title = title;
		this.linkOnPodcastpedia = linkOnPodcastpedia;
		this.feed = feed;
		this.description = description;
		
	}
	
	public PodcastEntity(Podcast podcast){
		try {
			BeanUtils.copyProperties(this, podcast);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkOnPodcastpedia() {
		return linkOnPodcastpedia;
	}

	public void setLinkOnPodcastpedia(String linkOnPodcastpedia) {
		this.linkOnPodcastpedia = linkOnPodcastpedia;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeed() {
		return feed;
	}

	public void setFeed(String feed) {
		this.feed = feed;
	}

	public Date getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}
		
}
