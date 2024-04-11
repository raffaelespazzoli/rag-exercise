package org.acme;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BlogPostSummaryList {
  @JsonProperty("numFound")
  public int numFound;
  @JsonProperty("start")
  public int start;
  @JsonProperty("maxScore")
  public long maxScore;
  @JsonProperty("numFoundExact")
  public boolean numFoundExact;
  @JsonProperty("docs")
  public List<BlogPostSummary> docs;
  public int getNumFound() {
    return numFound;
  }
  public void setNumFound(int numFound) {
    this.numFound = numFound;
  }
  public int getStart() {
    return start;
  }
  public void setStart(int start) {
    this.start = start;
  }
  public long getMaxScore() {
    return maxScore;
  }
  public void setMaxScore(long maxScore) {
    this.maxScore = maxScore;
  }
  public boolean isNumFoundExact() {
    return numFoundExact;
  }
  public void setNumFoundExact(boolean numFoundExact) {
    this.numFoundExact = numFoundExact;
  }
  public List<BlogPostSummary> getDocs() {
    return docs;
  }
  public void setDocs(List<BlogPostSummary> docs) {
    this.docs = docs;
  }
}
