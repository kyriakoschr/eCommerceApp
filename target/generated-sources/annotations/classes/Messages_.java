package classes;

import classes.MessagesPK;
import classes.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-30T02:10:15")
@StaticMetamodel(Messages.class)
public class Messages_ { 

    public static volatile SingularAttribute<Messages, Date> dateTime;
    public static volatile SingularAttribute<Messages, User> user1;
    public static volatile SingularAttribute<Messages, String> message;
    public static volatile SingularAttribute<Messages, User> user;
    public static volatile SingularAttribute<Messages, MessagesPK> messagesPK;
    public static volatile SingularAttribute<Messages, Boolean> seen;

}