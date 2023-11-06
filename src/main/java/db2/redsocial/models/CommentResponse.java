package db2.redsocial.models;

import java.util.Date;

public class CommentResponse {
  public Integer idComment;
  public String content;
  public Date date;
  public Integer idOwner;
  public Integer idParent;

  public CommentResponse(
      Integer idComment,
      String content,
      Date date,
      Integer idOwner,
      Integer idParent) {
    this.idComment = idComment;
    this.idOwner = idOwner;
    this.content = content;
    this.date = date;
    this.idParent = idParent;
  }
}
