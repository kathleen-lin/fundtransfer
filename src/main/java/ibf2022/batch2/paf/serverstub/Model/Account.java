package ibf2022.batch2.paf.serverstub.Model;

public class Account {
    
    private Integer accId;
    private String accName;
    private Float accBalance;

     /*
    acc_id smallint(5) not null auto_increment,
    acc_name varchar(100) not null,
    acc_balance float
     */

    public Integer getAccId() {
        return accId;
    }
    public void setAccId(Integer accId) {
        this.accId = accId;
    }
    public String getAccName() {
        return accName;
    }
    public void setAccName(String accName) {
        this.accName = accName;
    }
    public Float getAccBalance() {
        return accBalance;
    }
    public void setAccBalance(Float accBalance) {
        this.accBalance = accBalance;
    }

    
}
