/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mokhtar
 */
@Entity
@Table(name = "Img")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Img.findAll", query = "SELECT i FROM Img i")
    , @NamedQuery(name = "Img.findById", query = "SELECT i FROM Img i WHERE i.id = :id")
    , @NamedQuery(name = "Img.findByUrl", query = "SELECT i FROM Img i WHERE i.url = :url")
    , @NamedQuery(name = "Img.findByName", query = "SELECT i FROM Img i WHERE i.name = :name")
    , @NamedQuery(name = "Img.findByDescription", query = "SELECT i FROM Img i WHERE i.description = :description")
    , @NamedQuery(name = "Img.findByUploadDate", query = "SELECT i FROM Img i WHERE i.uploadDate = :uploadDate")
    //, @NamedQuery(name = "Img.findOneByMaxFeedback", query = "SELECT i FROM Img i ORDER BY (SELECT COUNT(*) FROM Feedback WHERE Feedback.OnContent = i.id) DESC LIMiT 1") })
    //    , @NamedQuery(name = "Img.findOneByMaxFeedback", query = "SELECT i FROM Img i ORDER BY (SELECT COUNT(f.id) FROM Feedback f WHERE f.OnContent = i.id) as t DESC, LIMIT 1") })
            , @NamedQuery(name = "Img.findOneByMaxFeedback", query = "SELECT i FROM Img i ORDER BY i.uploadDate") })

public class Img implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "URL")
    private String url;
    @Size(max = 200)
    @Column(name = "Name")
    private String name;
    @Size(max = 200)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UploadDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @JoinColumn(name = "Owner", referencedColumnName = "ID")
    @ManyToOne
    private Usr owner;
    @OneToMany(mappedBy = "onContent")
    private Collection<Feedback> feedbackCollection;

    public Img() {
    }

    public Img(Integer id) {
        this.id = id;
    }

    public Img(Integer id, String url, Date uploadDate) {
        this.id = id;
        this.url = url;
        this.uploadDate = uploadDate;
    }

    public Img(String url, String name, String description, Date uploadDate, Usr owner) {
        this.url = url;
        this.name = name;
        this.description = description;
        this.uploadDate = uploadDate;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Usr getOwner() {
        return owner;
    }

    public void setOwner(Usr owner) {
        this.owner = owner;
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
        if (!(object instanceof Img)) {
            return false;
        }
        Img other = (Img) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Img[ id=" + id + " ]";
    }

    public void setOwner(int id) {
        this.id = id;
    }
    
}
