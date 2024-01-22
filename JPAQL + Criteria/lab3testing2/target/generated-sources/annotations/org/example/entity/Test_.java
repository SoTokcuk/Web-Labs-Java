package org.example.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Test.class)
public abstract class Test_ {

	public static volatile SingularAttribute<Test, String> name;
	public static volatile SingularAttribute<Test, Integer> id;

	public static final String NAME = "name";
	public static final String ID = "id";

}

