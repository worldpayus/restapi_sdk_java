//
//  9/17/2015 1:32:18 PM
//

package SecureNetRestApiSDK.Api.Models;


public class Transaction   
{
    private int secureNetId;
    public int getSecureNetId() {
        return secureNetId;
    }

    public void setSecureNetId(int value) {
        secureNetId = value;
    }

    private String transactionType = new String();
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String value) {
        transactionType = value;
    }

    private String orderId = new String();
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String value) {
        orderId = value;
    }

    private int transactionId;
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int value) {
        transactionId = value;
    }

    private String authorizationCode = new String();
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String value) {
        authorizationCode = value;
    }

    private double authorizedAmount;
    public double getAuthorizedAmount() {
        return authorizedAmount;
    }

    public void setAuthorizedAmount(double value) {
        authorizedAmount = value;
    }

    private boolean allowedPartialCharges ;
    public boolean getAllowedPartialCharges() {
        return allowedPartialCharges;
    }

    public void setAllowedPartialCharges(boolean value) {
        allowedPartialCharges = value;
    }

    private String paymentTypeCode = new String();
    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String value) {
        paymentTypeCode = value;
    }

    private String paymentTypeResult = new String();
    public String getPaymentTypeResult() {
        return paymentTypeResult;
    }

    public void setPaymentTypeResult(String value) {
        paymentTypeResult = value;
    }

    private boolean level2Valid ;
    public boolean getLevel2Valid() {
        return level2Valid;
    }

    public void setLevel2Valid(boolean value) {
        level2Valid = value;
    }

    private boolean level3Valid ;
    public boolean getLevel3Valid() {
        return level3Valid;
    }

    public void setLevel3Valid(boolean value) {
        level3Valid = value;
    }

    private TransactionData transactionData;
    public TransactionData getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(TransactionData value) {
        transactionData = value;
    }

    private Object settlementData = new Object();
    public Object getSettlementData() {
        return settlementData;
    }

    public void setSettlementData(Object value) {
        settlementData = value;
    }

    private Object vaultData = new Object();
    public Object getVaultData() {
        return vaultData;
    }

    public void setVaultData(Object value) {
        vaultData = value;
    }

    private String creditCardType = new String();
    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String value) {
        creditCardType = value;
    }

    private String cardNumber = new String();
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String value) {
        cardNumber = value;
    }

    private String avsCode = new String();
    public String getAvsCode() {
        return avsCode;
    }

    public void setAvsCode(String value) {
        avsCode = value;
    }

    private String avsResult = new String();
    public String getAvsResult() {
        return avsResult;
    }

    public void setAvsResult(String value) {
        avsResult = value;
    }

    private String cardHolderFirstName = new String();
    public String getCardHolderFirstName() {
        return cardHolderFirstName;
    }

    public void setCardHolderFirstName(String value) {
        cardHolderFirstName = value;
    }

    private String cardHolderLastName = new String();
    public String getCardHolderLastName() {
        return cardHolderLastName;
    }

    public void setCardHolderLastName(String value) {
        cardHolderLastName = value;
    }

    private String expirationDate = new String();
    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String value) {
        expirationDate = value;
    }

    private Address billAddress;
    public Address getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(Address value) {
        billAddress = value;
    }

    private Object email = new Object();
    public Object getEmail() {
        return email;
    }

    public void setEmail(Object value) {
        email = value;
    }

    private Object phone = new Object();
    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object value) {
        phone = value;
    }

    private Object company = new Object();
    public Object getCompany() {
        return company;
    }

    public void setCompany(Object value) {
        company = value;
    }

    private String cardCodeCode = new String();
    public String getCardCodeCode() {
        return cardCodeCode;
    }

    public void setCardCodeCode(String value) {
        cardCodeCode = value;
    }

    private String cardCodeResult = new String();
    public String getCardCodeResult() {
        return cardCodeResult;
    }

    public void setCardCodeResult(String value) {
        cardCodeResult = value;
    }

    private Object accountName = new Object();
    public Object getAccountName() {
        return accountName;
    }

    public void setAccountName(Object value) {
        accountName = value;
    }

    private Object accountType = new Object();
    public Object getAccountType() {
        return accountType;
    }

    public void setAccountType(Object value) {
        accountType = value;
    }

    private Object accountNumber = new Object();
    public Object getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Object value) {
        accountNumber = value;
    }

    private Object checkNumber = new Object();
    public Object getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(Object value) {
        checkNumber = value;
    }

    private Object traceNumber = new Object();
    public Object getTraceNumber() {
        return traceNumber;
    }

    public void setTraceNumber(Object value) {
        traceNumber = value;
    }

    private double surchargeAmount;
    public double getSurchargeAmount() {
        return surchargeAmount;
    }

    public void setSurchargeAmount(double value) {
        surchargeAmount = value;
    }

    private double cashbackAmount;
    public double getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(double value) {
        cashbackAmount = value;
    }

    private Object fnsNumber = new Object();
    public Object getFnsNumber() {
        return fnsNumber;
    }

    public void setFnsNumber(Object value) {
        fnsNumber = value;
    }

    private Object voucherNumber = new Object();
    public Object getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(Object value) {
        voucherNumber = value;
    }

    private Object fleetCardInfo = new Object();
    public Object getFleetCardInfo() {
        return fleetCardInfo;
    }

    public void setFleetCardInfo(Object value) {
        fleetCardInfo = value;
    }

    private double gratuity;
    public double getGratuity() {
        return gratuity;
    }

    public void setGratuity(double value) {
        gratuity = value;
    }

    private String industrySpecificData = new String();
    public String getIndustrySpecificData() {
        return industrySpecificData;
    }

    public void setIndustrySpecificData(String value) {
        industrySpecificData = value;
    }

    private String marketSpecificData = new String();
    public String getMarketSpecificData() {
        return marketSpecificData;
    }

    public void setMarketSpecificData(String value) {
        marketSpecificData = value;
    }

    private String networkCode = new String();
    public String getNetworkCode() {
        return networkCode;
    }

    public void setNetworkCode(String value) {
        networkCode = value;
    }

    private double additionalAmount;
    public double getAdditionalAmount() {
        return additionalAmount;
    }

    public void setAdditionalAmount(double value) {
        additionalAmount = value;
    }

    private Object additionalData1 = new Object();
    public Object getAdditionalData1() {
        return additionalData1;
    }

    public void setAdditionalData1(Object value) {
        additionalData1 = value;
    }

    private Object additionalData2 = new Object();
    public Object getAdditionalData2() {
        return additionalData2;
    }

    public void setAdditionalData2(Object value) {
        additionalData2 = value;
    }

    private Object additionalData3 = new Object();
    public Object getAdditionalData3() {
        return additionalData3;
    }

    public void setAdditionalData3(Object value) {
        additionalData3 = value;
    }

    private String additionalData4 = new String();
    public String getAdditionalData4() {
        return additionalData4;
    }

    public void setAdditionalData4(String value) {
        additionalData4 = value;
    }

    private String additionalData5 = new String();
    public String getAdditionalData5() {
        return additionalData5;
    }

    public void setAdditionalData5(String value) {
        additionalData5 = value;
    }

    private String method = new String();
    public String getMethod() {
        return method;
    }

    public void setMethod(String value) {
        method = value;
    }

    private Object imageResult = new Object();
    public Object getImageResult() {
        return imageResult;
    }

    public void setImageResult(Object value) {
        imageResult = value;
    }

    private String softDescriptor = new String();
    public String getSoftDescriptor() {
        return softDescriptor;
    }

    public void setSoftDescriptor(String value) {
        softDescriptor = value;
    }

    private String dynamicMCC = new String();
    public String getDynamicMCC() {
        return dynamicMCC;
    }

    public void setDynamicMCC(String value) {
        dynamicMCC = value;
    }

    private String catIndicator = new String();
    public String getCATIndicator() {
        return catIndicator;
    }

    public void setCATIndicator(String value) {
        catIndicator = value;
    }
}


