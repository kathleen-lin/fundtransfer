package ibf2022.batch2.paf.serverstub.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch2.paf.serverstub.Model.Transaction;
import ibf2022.batch2.paf.serverstub.Payload.transerPayload;
import ibf2022.batch2.paf.serverstub.Repository.transactionRepository;
import ibf2022.batch2.paf.serverstub.Service.fundTranseferService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping ("/api")
public class FundsTransferController {

	@Autowired
	fundTranseferService transferSvc;

	@Autowired
	transactionRepository tRepo;

	@PostMapping("/transfer")
	public ResponseEntity<String> postTransfer(@RequestBody transerPayload payload) {


		Float amount = payload.getAmount();
		String senderName = payload.getSrcAcct();
		String receiverName = payload.getDestAcct();

		Boolean transferSuccess = transferSvc.fundTransfer(senderName, receiverName, amount);
		
		// Transfer successful return the following JSON object
		// { "transactionId", "aTransactionId" }
		if (transferSuccess){

			String transcationId = UUID.randomUUID().toString();

			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setReceiver(receiverName);
			transaction.setSender(senderName);
			transaction.setTranscation_id(transcationId);

			// create a Document and insert into mongodb
			tRepo.insertTransaction(transaction);

			JsonObject json = Json.createObjectBuilder().add("transactionId", transcationId).build();

			ResponseEntity<String> resp = new ResponseEntity<String>(json.toString(), HttpStatus.OK);

			return resp;
		}
	
		// Transfer failed return the following JSON object
		// { "message", "Error message" }
		JsonObject json = Json.createObjectBuilder().add("message", "Cannot perform the transfer").build();

	
		return new ResponseEntity<String>(json.toString(), HttpStatus.BAD_REQUEST);
	}
}
