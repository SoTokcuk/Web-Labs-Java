package org.example.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Answer.class)
public abstract class Answer_ {

	public static volatile SingularAttribute<Answer, String> result;
	public static volatile SingularAttribute<Answer, Test> test;
	public static volatile SingularAttribute<Answer, String> answer;
	public static volatile SingularAttribute<Answer, Student> student;
	public static volatile SingularAttribute<Answer, Integer> id;

	public static final String RESULT = "result";
	public static final String TEST = "test";
	public static final String ANSWER = "answer";
	public static final String STUDENT = "student";
	public static final String ID = "id";

}

