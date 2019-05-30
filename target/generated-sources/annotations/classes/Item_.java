package classes;

import classes.Bids;
import classes.Category;
import classes.Images;
import classes.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-30T02:10:15")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, String> country;
    public static volatile CollectionAttribute<Item, Category> categoryCollection;
    public static volatile SingularAttribute<Item, Date> endDate;
    public static volatile SingularAttribute<Item, Float> currentPrice;
    public static volatile SingularAttribute<Item, String> description;
    public static volatile CollectionAttribute<Item, Images> imagesCollection;
    public static volatile SingularAttribute<Item, User> sellerID;
    public static volatile SingularAttribute<Item, Float> firstBid;
    public static volatile SingularAttribute<Item, String> name;
    public static volatile CollectionAttribute<Item, Bids> bidsCollection;
    public static volatile SingularAttribute<Item, String> location;
    public static volatile SingularAttribute<Item, Integer> id;
    public static volatile SingularAttribute<Item, Integer> numofbids;
    public static volatile SingularAttribute<Item, Date> startDate;

}