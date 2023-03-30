package ibf2022.batch2.paf.serverstub.Repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.paf.serverstub.Model.Transaction;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Repository
public class transactionRepository {
    
    @Autowired
    MongoTemplate mongotemplate;

    /*
        db.getCollection("information").insert({
        transaction_id: "123abc", 
        from: "Abby", 
        to: "Benny",
        amount: 5.0
        })

     */

     // object -> JSON -> document -> insert with mongotemplate
     public void insertTransaction(Transaction transaction){

        JsonObject transactionJson = Json.createObjectBuilder()
                                    .add("transaction_id", transaction.getTranscation_id())
                                    .add("from", transaction.getSender())
                                    .add("to", transaction.getReceiver())
                                    .add("amount", transaction.getAmount())
                                    .build();

        Document toInsert = Document.parse(transactionJson.toString());
        Document doc = mongotemplate.insert(toInsert,"information");

        if (doc != null){
            System.out.println("success");
        }

        System.out.println("failed");

        /*
         
            ObjectId id = newDoc.getObjectId();
            List<Document> docsToInsert = new LinkedList<>();
            // Add Document to list
            ...
            Collection<Document> newDocs = mongoTemplate.insert(
            docsToInsert, “tv_shows”)
         */


     }
    
     
    

}
