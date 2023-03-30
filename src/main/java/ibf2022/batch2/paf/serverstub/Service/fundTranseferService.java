package ibf2022.batch2.paf.serverstub.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.batch2.paf.serverstub.Model.Account;
import ibf2022.batch2.paf.serverstub.Repository.accountRepository;

@Service
public class fundTranseferService {
    
    @Autowired
    accountRepository accRepo;


    @Transactional
    public Boolean fundTransfer(String senderName, String receiverName, Float amt){
        
        Boolean proceed = true;

        while (proceed){
            
            // check if both users exist
            Account sender = accRepo.findByName(senderName);
            Account receiver = accRepo.findByName(receiverName);

            if (receiver == null|| sender == null){
                proceed = false;
                break;
            }

    
    
            // check if sender balance > transfer amount
            if (sender.getAccBalance() < amt){
                proceed = false;
                break;
            }

            // perform withdraw and deposit
            Integer withdrawn = accRepo.withdraw(amt, sender);
            Integer deposited = accRepo.deposit(amt, receiver);

            if (withdrawn < 1 || deposited < 1){
                proceed = false;
                break;
            }

            break;
        }
        
        // commit if managed to get here
        return proceed;

    }
}
