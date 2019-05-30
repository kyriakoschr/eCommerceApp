package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Bids;
import model.Item;
import model.Messages;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-30T04:03:19")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> country;
    public static volatile SingularAttribute<User, String> address;
    public static volatile CollectionAttribute<User, Item> itemCollection;
    public static volatile SingularAttribute<User, Long> rating;
    public static volatile SingularAttribute<User, Long> afm;
    public static volatile SingularAttribute<User, Long> telephone;
    public static volatile CollectionAttribute<User, Messages> messagesCollection1;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SingularAttribute<User, String> name;
    public static volatile CollectionAttribute<User, Bids> bidsCollection;
    public static volatile SingularAttribute<User, String> location;
    public static volatile SingularAttribute<User, String> email;
    public static volatile CollectionAttribute<User, Messages> messagesCollection;
    public static volatile SingularAttribute<User, String> username;

}