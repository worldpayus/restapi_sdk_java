package SecureNetRestApiSDK.Api.Requests;


import SNET.Core.HttpMethodEnum;
import SecureNetRestApiSDK.Api.Models.Card;
import SecureNetRestApiSDK.Api.Models.Encryption;
import SecureNetRestApiSDK.Api.Models.ExtendedInformation;
import SecureNetRestApiSDK.Api.Models.PaymentVaultToken;

public class VerifyRequest extends SecureNetRequest
{

    /**
     * Amount of the charge to be authorized.
     */
    private double amount;
    public double getAmount() {
        return amount;
    }

    public void setAmount(double value) {
        amount = value;
    }

    /**
     * Credit-card-specific data. In the case of a card-present transaction, track data from a swiped transaction is the most commonly used property. Required for credit card charges.
     */
    private Card card;
    public Card getCard() {
        return card;
    }

    public void setCard(Card value) {
        card = value;
    }

    /**
     * Additional data to assist in reporting, ecommerce or moto transactions, and level 2 or level 3 processing. Includes user-defined fields and invoice-related information.
     */
    private ExtendedInformation extendedInformation;
    public ExtendedInformation getExtendedInformation() {
        return extendedInformation;
    }

    public void setExtendedInformation(ExtendedInformation value) {
        extendedInformation = value;
    }

    public String getUri() throws Exception {
        return "api/Payments/Verify";
    }

    public HttpMethodEnum getMethod() throws Exception {
        return HttpMethodEnum.POST;
    }

}
