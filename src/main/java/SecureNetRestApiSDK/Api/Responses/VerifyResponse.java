package SecureNetRestApiSDK.Api.Responses;

import SecureNetRestApiSDK.Api.Models.Transaction;

public class VerifyResponse extends SecureNetResponse
{
    private Transaction transaction;
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction value) {
        transaction = value;
    }

}
