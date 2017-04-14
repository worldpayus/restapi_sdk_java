//
//  9/17/2015 1:32:19 PM
//

package SecureNetRestApiSDK.Api.Requests;

import SecureNetRestApiSDK.Api.Requests.SecureNetRequest;
import SNET.Core.HttpMethodEnum;

public class TransactionRetrieveRequest  extends SecureNetRequest 
{
    private int transactionId;
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int value) {
        transactionId = value;
    }

    public String getUri() throws Exception {
        return String.format("api/transactions/%d", getTransactionId());

    }

    public HttpMethodEnum getMethod() throws Exception {
        return HttpMethodEnum.GET;
    }

}


