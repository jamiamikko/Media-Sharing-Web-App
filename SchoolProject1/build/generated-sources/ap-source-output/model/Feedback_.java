package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Feedback;
import model.Img;
import model.Usr;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-01T13:03:34")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, Usr> owner;
    public static volatile SingularAttribute<Feedback, Date> uploadDate;
    public static volatile SingularAttribute<Feedback, Feedback> onFeedback;
    public static volatile CollectionAttribute<Feedback, Feedback> feedbackCollection;
    public static volatile SingularAttribute<Feedback, Img> onContent;
    public static volatile SingularAttribute<Feedback, Integer> id;
    public static volatile SingularAttribute<Feedback, String> content;

}