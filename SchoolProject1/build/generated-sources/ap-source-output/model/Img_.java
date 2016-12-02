package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Feedback;
import model.Usr;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-02T09:11:52")
@StaticMetamodel(Img.class)
public class Img_ { 

    public static volatile SingularAttribute<Img, Usr> owner;
    public static volatile SingularAttribute<Img, Date> uploadDate;
    public static volatile CollectionAttribute<Img, Feedback> feedbackCollection;
    public static volatile SingularAttribute<Img, String> name;
    public static volatile SingularAttribute<Img, String> description;
    public static volatile SingularAttribute<Img, Integer> id;
    public static volatile SingularAttribute<Img, String> url;

}