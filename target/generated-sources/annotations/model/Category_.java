package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Item;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-30T04:03:19")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile CollectionAttribute<Category, Item> itemCollection;
    public static volatile SingularAttribute<Category, String> name;

}