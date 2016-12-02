package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Feedback;
import model.Img;
import model.Usr;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-02T09:11:53")
@StaticMetamodel(Usr.class)
public class Usr_ { 

    public static volatile CollectionAttribute<Usr, Usr> usrCollection1;
    public static volatile SingularAttribute<Usr, String> password;
    public static volatile CollectionAttribute<Usr, Feedback> feedbackCollection;
    public static volatile SingularAttribute<Usr, Integer> id;
    public static volatile SingularAttribute<Usr, Boolean> privilege;
    public static volatile SingularAttribute<Usr, String> userName;
    public static volatile CollectionAttribute<Usr, Usr> usrCollection;
    public static volatile CollectionAttribute<Usr, Img> imgCollection;

}