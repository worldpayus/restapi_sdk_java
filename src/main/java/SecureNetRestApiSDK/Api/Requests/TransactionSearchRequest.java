//
//  9/17/2015 1:32:19 PM
//

package SecureNetRestApiSDK.Api.Requests;

import java.util.Date;

import SNET.Core.HttpMethodEnum;

public class TransactionSearchRequest  extends SecureNetRequest 
{
    private int transactionId;
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int value) {
        transactionId = value;
    }

    private String customerId = new String();
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String value) {
        customerId = value;
    }

    private Date startDate = new Date();
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date value) {
        startDate = value;
    }

    private Date endDate = new Date();
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date value) {
        endDate = value;
    }

    private double amount;
    public double getAmount() {
        return amount;
    }

    public void setAmount(double value) {
        amount = value;
    }


    public String getUri() throws Exception {
        return "api/transactions/Search";
    }

    public HttpMethodEnum getMethod() throws Exception {
        return HttpMethodEnum.POST;
    }

}


