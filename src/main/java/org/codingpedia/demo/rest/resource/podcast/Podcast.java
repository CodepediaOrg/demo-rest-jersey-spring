package org.codingpedia.demo.rest.resource.podcast;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.beanutils.BeanUtils;
import org.codingpedia.demo.rest.dao.PodcastEntity;
import org.codingpedia.demo.rest.helpers.DateISO8601Adapter;

/**
 * Podcast resource placeholder for json/xml representation 
 * 
 * @author ama
 *
 */
@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Podcast implements Serializable {

	private static final long serialVersionUID = -8039686696076337053L;

	/** id of the podcast */
	@XmlElement(name = "id")	
	private Long id;
	
	/** title of the podcast */
	@XmlElement(name = "title")	
	private String title;
		
	/** link of the podcast on Podcastpedia.org */
	@XmlElement(name = "linkOnPodcastpedia")	
	private String linkOnPodcastpedia;
	
	/** url of the feed */
	@XmlElement(name = "feed")	
	private String feed;
	
	/** description of the podcast */
	@XmlElement(name = "description")
	@PodcastDetailedView	
	private String description; 
		
	/** insertion date in the database */
	@XmlElement(name = "insertionDate")
	@XmlJavaTypeAdapter(DateISO8601Adapter.class)	
	@PodcastDetailedView
	private Date insertionDate;

	public Podcast(PodcastEntity podcastEntity){
		try {
			BeanUtils.copyProperties(this, podcastEntity);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Podcast(String title, String linkOnPodcastpedia, String feed,
			String description) {
		
		this.title = title;
		this.linkOnPodcastpedia = linkOnPodcastpedia;
		this.feed = feed;
		this.description = description;
		
	}
	
	public Podcast(){}
		
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
