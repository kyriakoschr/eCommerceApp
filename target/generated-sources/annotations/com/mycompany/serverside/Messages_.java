package com.mycompany.serverside;

import com.mycompany.serverside.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-30T16:24:08")
@StaticMetamodel(Messages.class)
public class Messages_ { 

    public static volatile SingularAttribute<Messages, Date> dateTime;
    public static volatile SingularAttribute<Messages, User> fromUserID;
    public static volatile SingularAttribute<Messages, Integer> id;
    public static volatile SingularAttribute<Messages, String> message;
    public static volatile SingularAttribute<Messages, User> toUserID;
    public static volatile SingularAttribute<Messages, Boolean> seen;

}