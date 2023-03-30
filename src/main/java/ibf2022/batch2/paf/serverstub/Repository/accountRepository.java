package ibf2022.batch2.paf.serverstub.Repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.paf.serverstub.Model.Account;


@Repository
public class accountRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Check if sender & receiver exist in mySQL database 
    // get acc_balance of both sender & receiver
    // check if sender's balance > receiver's balance
    // deduct alance from sender, increase balance for receiver

    // find by name
    private final String SEARCH_BY_NAME =  "select * from account_detail where acc_name = ?";
    private final String WITHDRAW_BY_ID = "update account_detail set acc_balance = acc_balance - ? where acc_id = ?";
    private final String DEPOSIT_BY_ID = "update account_detail set acc_balance = acc_balance + ? where acc_id = ?";

    public Account findByName(String name){

        Account acc = jdbcTemplate.queryForObject(SEARCH_BY_NAME, BeanPropertyRowMapper.newInstance(Account.class), name);

        System.out.printf(">>>>> %s, %d, %f\n", acc.getAccName(), acc.getAccId(), acc.getAccBalance());

        //Optional<Account> account = Optional.ofNullable(acc);


        return acc;
        
    }

    // withdraw
    public Integer withdraw (Float amount, Account sender){
        
        return jdbcTemplate.update(WITHDRAW_BY_ID, amount, sender.getAccId());

    }



    // deposit
    public Integer deposit (Float amount, Account receiver){
        
        return jdbcTemplate.update(DEPOSIT_BY_ID, amount, receiver.getAccId());

    }

    

}
