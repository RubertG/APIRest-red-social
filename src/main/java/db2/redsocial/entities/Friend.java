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
@Table(name = "friends")
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_friendship", nullable = false)
    private Integer idFriendship;

    @JoinColumn(name = "id_user_1", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(optional = false)
    private User idUser1;

    @JoinColumn(name = "id_user_2", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(optional = false)
    private User idUser2;

    public Friend() {
    }

    public Friend(Integer idFriendship) {
        this.idFriendship = idFriendship;
    }

    public Integer getIdFriendship() {
        return idFriendship;
    }

    public void setIdFriendship(Integer idFriendship) {
        this.idFriendship = idFriendship;
    }

    public User getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(User idUser1) {
        this.idUser1 = idUser1;
    }

    public User getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(User idUser2) {
        this.idUser2 = idUser2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFriendship != null ? idFriendship.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Friend)) {
            return false;
        }
        Friend other = (Friend) object;
        return !((this.idFriendship == null && other.idFriendship != null) || (this.idFriendship != null && !this.idFriendship.equals(other.idFriendship)));
    }

    @Override
    public String toString() {
        return "db2.redsocial.entities.Friends[ idFriendship=" + idFriendship + " ]";
    }
    
}
