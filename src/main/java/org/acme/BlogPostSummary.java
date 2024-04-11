package org.acme;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BlogPostSummary {
  @JsonProperty("description")
  private String description;

  @JsonProperty("title")
  private String title;

  @JsonProperty("label")
  private String label;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getBundle() {
    return bundle;
  }

  public void setBundle(String bundle) {
    this.bundle = bundle;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  public boolean isElevated() {
    return elevated;
  }

  public void setElevated(boolean elevated) {
    this.elevated = elevated;
  }

  public List<String> getSource() {
    return source;
  }

  public void setSource(List<String> source) {
    this.source = source;
  }

  public List<String> getDcTitle() {
    return dcTitle;
  }

  public void setDcTitle(List<String> dcTitle) {
    this.dcTitle = dcTitle;
  }

  public String getContentLanguage() {
    return contentLanguage;
  }

  public void setContentLanguage(String contentLanguage) {
    this.contentLanguage = contentLanguage;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public List<String> getAuthorLinks() {
    return authorLinks;
  }

  public void setAuthorLinks(List<String> authorLinks) {
    this.authorLinks = authorLinks;
  }

  public List<Long> getCategoryIds() {
    return categoryIds;
  }

  public void setCategoryIds(List<Long> categoryIds) {
    this.categoryIds = categoryIds;
  }

  @JsonProperty("bundle")
  private String bundle;

  @JsonProperty("url")
  private String url;

  @JsonProperty("id")
  private String id;

  // @JsonProperty("ds_created")
  // private Date createdDate;

  // @JsonProperty("post_date")
  // private Date postDate;

  @JsonProperty("score")
  private Long score;

  @JsonProperty("[elevated]")
  private boolean elevated;

  @JsonProperty("source")
  private List<String> source;

  @JsonProperty("dc_title")
  private List<String> dcTitle;

  @JsonProperty("content_language")
  private String contentLanguage;

  @JsonProperty("taxonomy_blog_post_category")
  private List<String> categories;

  @JsonProperty("post_authors")
  private List<String> authors;

  @JsonProperty("post_author_links")
  private List<String> authorLinks;

  @JsonProperty("taxonomy_blog_post_category_tid")
  private List<Long> categoryIds;
}