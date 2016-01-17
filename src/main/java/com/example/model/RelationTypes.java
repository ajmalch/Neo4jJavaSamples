package com.example.model;

import org.neo4j.graphdb.RelationshipType;



public enum RelationTypes implements RelationshipType{

	MOTHER,FATHER,SON,DAUGHTER,HUSBAND,OWNS,LIKES;
}
