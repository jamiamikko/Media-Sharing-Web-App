/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mokhtar
 */
@Entity
@Table(name = "Usr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usr.findAll", query = "SELECT u FROM Usr u")
    , @NamedQuery(name = "Usr.findById", query = "SELECT u FROM Usr u WHERE u.id = :id")
    , @NamedQuery(name = "Usr.findByPrivilege", query = "SELECT u FROM Usr u WHERE u.privilege = :privilege")
    , @NamedQuery(name = "Usr.findByUserName", query = "SELECT u FROM Usr u WHERE u.userName = :userName")
    , @NamedQuery(name = "Usr.findByPassword", query = "SELECT u FROM Usr u WHERE u.password = :password")})
public class Usr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PRIVILEGE")
    private Boolean privilege;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "UserName")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Password")
    private String password;
    @JoinTable(name = "_friend_of_", joinColumns = {
        @JoinColumn(name = "User1", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "User2", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Usr> usrCollection;
    @ManyToMany(mappedBy = "usrCollection")
    private Collection<Usr> usrCollection1;
    @OneToMany(mappedBy = "owner")
    private Collection<Img> imgCollection;
    @OneToMany(mappedBy = "owner")
    private Collection<Feedback> feedbackCollection;

    public Usr() {
    }

    public Usr(Integer id) {
        this.id = id;
    }

    public Usr(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Usr(String Username, String Password) {
        this.userName = Username;
        this.password = Password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Boolean privilege) {
        this.privilege = privilege;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Usr> getUsrCollection() {
        return usrCollection;
    }

    public void setUsrCollection(Collection<Usr> usrCollection) {
        this.usrCollection = usrCollection;
    }

    @XmlTransient
    public Collection<Usr> getUsrCollection1() {
        return usrCollection1;
    }

    public void setUsrCollection1(Collection<Usr> usrCollection1) {
        this.usrCollection1 = usrCollection1;
    }

    @XmlTransient
    public Collection<Img> getImgCollection() {
        return imgCollection;
    }

    public void setImgCollection(Collection<Img> imgCollection) {
        this.imgCollection = imgCollection;
    }

    @XmlTransient
    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usr)) {
            return false;
        }
        Usr other = (Usr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usr[ id=" + id + " ]";
    }
    
}
