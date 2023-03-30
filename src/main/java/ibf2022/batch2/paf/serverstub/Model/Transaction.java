package ibf2022.batch2.paf.serverstub.Model;

public class Transaction {

    private String transcation_id;
    private String sender;
    private String receiver;
    private Float amount;

    public String getTranscation_id() {
        return transcation_id;
    }
    public void setTranscation_id(String transcation_id) {
        this.transcation_id = transcation_id;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    


    
}
