package db2.redsocial.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reactionspost")
public class Reactionpost implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reaction", nullable = false)
    private Integer idReaction;

    @JoinColumn(name = "id_post", referencedColumnName = "id_post", nullable = false)
    @ManyToOne(optional = false)
    private Post idPost;

    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(optional = false)
    private User idUser;

    public Reactionpost() {
    }

    public Reactionpost(Integer idReaction) {
        this.idReaction = idReaction;
    }

    public Integer getIdReaction() {
        return idReaction;
    }

    public void setIdReaction(Integer idReaction) {
        this.idReaction = idReaction;
    }

    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        this.idPost = idPost;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReaction != null ? idReaction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Reactionpost)) {
            return false;
        }
        Reactionpost other = (Reactionpost) object;
        return !((this.idReaction == null && other.idReaction != null) || (this.idReaction != null && !this.idReaction.equals(other.idReaction)));
    }

    @Override
    public String toString() {
        return "db2.redsocial.entities.Reactionspost[ idReaction=" + idReaction + " ]";
    }
    
}
