/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
@Table(name = "Feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
    , @NamedQuery(name = "Feedback.findById", query = "SELECT f FROM Feedback f WHERE f.id = :id")
    , @NamedQuery(name = "Feedback.findByContent", query = "SELECT f FROM Feedback f WHERE f.content = :content")
    , @NamedQuery(name = "Feedback.findByUploadDate", query = "SELECT f FROM Feedback f WHERE f.uploadDate = :uploadDate")})

public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UploadDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @OneToMany(mappedBy = "onFeedback")
    private Collection<Feedback> feedbackCollection;
    @JoinColumn(name = "OnFeedback", referencedColumnName = "ID")
    @ManyToOne
    private Feedback onFeedback;
    @JoinColumn(name = "Owner", referencedColumnName = "ID")
    @ManyToOne
    private Usr owner;
    @JoinColumn(name = "OnContent", referencedColumnName = "ID")
    @ManyToOne
    private Img onContent;

    
    public Feedback() {
    }

    public Feedback(Integer id) {
        this.id = id;
    }

    public Feedback(Integer id, String content, Date uploadDate) {
        this.id = id;
        this.content = content;
        this.uploadDate = uploadDate;
    }

    public Feedback(String content, Date uploadDate) {
        this.content = content;
        this.uploadDate = uploadDate;
    }

    public Feedback(String content, Date uploadDate, Usr owner, Img onContent) {
        this.content = content;
        this.uploadDate = uploadDate;
        this.owner = owner;
        this.onContent = onContent;
    }

    public Feedback(String content, Date uploadDate, Feedback onFeedback, Usr owner, Img onContent) {
        this.content = content;
        this.uploadDate = uploadDate;
        this.onFeedback = onFeedback;
        this.owner = owner;
        this.onContent = onContent;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @XmlTransient
    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }

    public Feedback getOnFeedback() {
        return onFeedback;
    }

    public void setOnFeedback(Feedback onFeedback) {
        this.onFeedback = onFeedback;
    }

    public Usr getOwner() {
        return owner;
    }

    public void setOwner(Usr owner) {
        this.owner = owner;
    }

    public Img getOnContent() {
        return onContent;
    }

    public void setOnContent(Img onContent) {
        this.onContent = onContent;
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
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Feedback[ id=" + id + " ]";
    }
    
    
}
