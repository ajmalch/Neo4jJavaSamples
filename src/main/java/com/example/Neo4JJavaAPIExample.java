package com.example;



import java.math.BigDecimal;
import java.util.Date;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.rest.graphdb.RestGraphDatabase;

import com.example.model.NodeLabels;
import com.example.model.RelationTypes;

/**
 * Hello world!
 *
 */
public class Neo4JJavaAPIExample 
{
	private static final String fileseparator=System.getProperty("file.separator");
	private static final String GraphDB_Path="C:"+fileseparator+"Users"+fileseparator+"Dell"+fileseparator+"Documents"+fileseparator+"Neo4j"+fileseparator+"default.graphdb";

//	@SuppressWarnings("deprecation")
	public static void main( String[] args )
    {
    	
    	//GraphDatabaseFactory neo4jFactory=new GraphDatabaseFactory();
        //GraphDatabaseService graphDB=neo4jFactory.newEmbeddedDatabase(GraphDB_Path);
		String SERVER_ROOT_URI ="http://ec2-54-86-197-61.compute-1.amazonaws.com:7474";
		
		RestGraphDatabase graphDB=new RestGraphDatabase(SERVER_ROOT_URI+"/db/data/","neo4j", "training");
		try(Transaction tx=graphDB.beginTx()){
        	
        	Node Ajmal=graphDB.createNode(NodeLabels.PERSON);	
        	Ajmal.addLabel(NodeLabels.EMPLOYEE);
        	Ajmal.setProperty("Name", "Ajmal");
        	Ajmal.setProperty("Salary", new BigDecimal(54657579.45).doubleValue());
        	
        	Node Maliha=graphDB.createNode(NodeLabels.PERSON);	
        	Maliha.addLabel(NodeLabels.STUDENT);
        	Maliha.setProperty("Name", "Maliha");
        	Maliha.setProperty("Grade", 5);
        	
        	Node Shadiya=graphDB.createNode(NodeLabels.PERSON);	
        	Shadiya.setProperty("Name", "Shadiya");
        	
        	Relationship Ajmal_Shadi=Ajmal.createRelationshipTo(Shadiya, RelationTypes.HUSBAND);
        	Ajmal_Shadi.setProperty("Since", new Date(2008, 12, 27).toString());
        	
        	Relationship Ajmal_Maliha=Ajmal.createRelationshipTo(Maliha, RelationTypes.FATHER);
        	
        	Relationship Shadi_Maliha=Shadiya.createRelationshipTo(Maliha, RelationTypes.MOTHER);
        	
        	Node iphone5=graphDB.createNode(NodeLabels.PHONE);
        	iphone5.setProperty("ManuFacturer", "Apple US");
        	
        	Node htcOne=graphDB.createNode(NodeLabels.PHONE);
        	htcOne.setProperty("ManuFacturer", "HTC US");
        	
        	Relationship Ajmal_HTC=Ajmal.createRelationshipTo(htcOne, RelationTypes.OWNS);
        	Ajmal_Shadi.setProperty("Since", new Date(2015, 01, 27).toString());
        	
        	Relationship Shadi_iphone5=Ajmal.createRelationshipTo(iphone5, RelationTypes.OWNS);
        	Shadi_iphone5.setProperty("Since", new Date(2014, 01, 27).toString());
        	
        	Relationship Mali_iphone5=Maliha.createRelationshipTo(iphone5, RelationTypes.LIKES);
        	
        	tx.success();
        	
        }
        graphDB.shutdown();
    }
}
