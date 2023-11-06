package db2.redsocial.models;

import java.util.Date;

public class PostResponse {
  public Integer idPost;
  public String title;
  public String content;
  public Date date;
  public Integer idOwner;

  public PostResponse(Integer idPost, String title, String content, Date date, Integer idOwner) {
    this.idPost = idPost;
    this.title = title;
    this.idOwner = idOwner;
    this.content = content;
    this.date = date;
  }
}
