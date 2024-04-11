package org.acme;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BlogPostSummaryRoot {
  @JsonProperty("statusCode")
  public int statusCode;
  @JsonProperty("statusMessage")
  public String statusMessage;
  @JsonProperty("responseHeader")
  public List<Object> responseHeader;
  @JsonProperty("headers")
  public List<String> headers;
  @JsonProperty("body")
  public BlogPostSummaryList body;
  @JsonProperty("fusionQueryId")
  public String fusionQueryId;
}
