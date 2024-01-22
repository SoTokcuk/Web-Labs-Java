package by.bsu.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Question.class)
public abstract class Question_ {

	public static volatile SingularAttribute<Question, Test> test;
	public static volatile SingularAttribute<Question, String> subject;
	public static volatile SingularAttribute<Question, String> description;
	public static volatile SingularAttribute<Question, Integer> id;

	public static final String TEST = "test";
	public static final String SUBJECT = "subject";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

