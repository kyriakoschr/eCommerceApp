package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.BidsPK;
import model.Item;
import model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-30T04:03:19")
@StaticMetamodel(Bids.class)
public class Bids_ { 

    public static volatile SingularAttribute<Bids, BidsPK> bidsPK;
    public static volatile SingularAttribute<Bids, Float> amount;
    public static volatile SingularAttribute<Bids, Item> item;
    public static volatile SingularAttribute<Bids, User> user;

}