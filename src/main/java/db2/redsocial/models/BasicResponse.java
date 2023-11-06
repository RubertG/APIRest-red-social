package db2.redsocial.models;

public class BasicResponse {
  public Integer id;
  public Integer idPost;
  public Integer idUser;

  public BasicResponse(Integer id, Integer idPost, Integer idUser) {
    this.id = id;
    this.idPost = idPost;
    this.idUser = idUser;
  }
}