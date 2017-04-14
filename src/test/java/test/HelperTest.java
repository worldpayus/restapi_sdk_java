package test;

import SecureNetRestApiSDK.Api.Models.*;
import SecureNetRestApiSDK.Api.Requests.*;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

public class HelperTest {
    private Boolean _isSoftDescriptorEnabled ;
    private Boolean _isDynamicMCCEnabled ;
    private String SoftDescriptorValue = "Valid Soft Descriptor";
    private String DynamicMCCValue = "2047";
    private Properties config ;
    private String _requestSoftDescriptor;
    private String _responseSoftDescriptor;
    private String _requestDynamicMCC;
    private String _responseDynamicMCC;



    public HelperTest() throws IOException {
        InputStream stream  = this.getClass().getResourceAsStream("/config.properties");
        config = new Properties();
        config.load(stream);
        _isSoftDescriptorEnabled = Boolean.parseBoolean(config.getProperty("isSoftDescriptorEnabled"));
        _requestSoftDescriptor = _isSoftDescriptorEnabled ? SoftDescriptorValue : null;
        _responseSoftDescriptor = _isSoftDescriptorEnabled ? SoftDescriptorValue : "";
        _isDynamicMCCEnabled = Boolean.parseBoolean(config.getProperty("isDynamicMCCEnabled"));
        _requestDynamicMCC = _isDynamicMCCEnabled ? DynamicMCCValue : null;
        _responseDynamicMCC = _isDynamicMCCEnabled ? DynamicMCCValue : "";
    }

    public String getRequestSoftDescriptor()
    {
        return _requestSoftDescriptor;
    }

    public String getResponseSoftDescriptor()
    {
        return _responseSoftDescriptor;
    }

    public String getRequestDynamicMCC()
    {
        return _requestDynamicMCC;
    }

    public String getResponseDynamicMCC()
    {
        return _responseDynamicMCC;
    }

    public String getCATIndicator(){
        PosCardholderActivatedTerminal catIndicator = PosCardholderActivatedTerminal.TransponderTransactio;
        return catIndicator.getCATIndicatorValue();
    }

    public AuthorizeRequest getAnAuthorizeRequiest(boolean containCATIndicator){
        AuthorizeRequest request = new AuthorizeRequest();
        request.setCard(getCard());
        request.setAmount(20d);
        request.setDeveloperApplication(getDeveloperApplication());
        request.setExtendedInformation(getExtendedInformation(containCATIndicator));
        return request;
    }

    public PriorAuthCaptureRequest getAPriorAuthCaptureRequest(int transactionId,boolean includeTip){
        PriorAuthCaptureRequest request = new PriorAuthCaptureRequest();
        request.setAmount(20d);
        request.setTransactionId(transactionId);
        request.setDeveloperApplication(getDeveloperApplication());
        if(includeTip){
            ExtendedInformation extendedInfo = new ExtendedInformation();
            ServiceData serviceData = new ServiceData();
            serviceData.setGratuityAmount(1.75);
            extendedInfo.setServiceData(serviceData);
            request.setExtendedInformation(extendedInfo);
        }
        return request;
    }

    public ChargeRequest getAChargeRequest(boolean containCATIndicator){
        ChargeRequest request = new ChargeRequest();
        request.setAmount(20d);
        request.setCard(getCard());
        request.setDeveloperApplication(getDeveloperApplication());
        request.setExtendedInformation(getExtendedInformation(containCATIndicator));
        return request;
    }



    public CreditRequest getACreditRequest(boolean containCATIndicator){
        CreditRequest request = new CreditRequest();
        request.setCard(getCard());
        request.setAmount(1.05d);
        request.setDeveloperApplication(getDeveloperApplication());
        request.setExtendedInformation(getExtendedInformation(containCATIndicator));
        return request;
    }

    public RefundRequest getARefundRequest(int transactionId){
        RefundRequest request = new RefundRequest();
        request.setDeveloperApplication(getDeveloperApplication());
        request.setTransactionId(transactionId);
        return request;
    }

    public VoidRequest getAVoidRequest(int transactionId){
        VoidRequest request = new VoidRequest();
        request.setTransactionId(transactionId);
        request.setDeveloperApplication(getDeveloperApplication());
        return request;
    }

    public VerifyRequest getAVerifyRequest(boolean containCATIndicator){
        VerifyRequest request = new VerifyRequest();
        request.setCard(getCard());
        request.setAmount(1.05d);
        request.setDeveloperApplication(getDeveloperApplication());
        request.setExtendedInformation(getExtendedInformation(containCATIndicator));
        return request;
    }

    public TransactionSearchRequest getATransactionSearchRequiest() throws ParseException {
        TransactionSearchRequest request = new TransactionSearchRequest();
        request.setDeveloperApplication(getDeveloperApplication());
        request.setAmount(20d);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        request.setStartDate(formatter.parse("02/01/2016"));
        request.setEndDate(formatter.parse("05/31/2017"));
        return request;
    }

    public TransactionRetrieveRequest getATransactionRetrieveRequest(){
        TransactionRetrieveRequest request = new TransactionRetrieveRequest();
        request.setDeveloperApplication(getDeveloperApplication());
        return request;
    }

    public TransactionUpdateRequest getATransactionUpdateRequest(){
        TransactionUpdateRequest request = new TransactionUpdateRequest();
        request.setDeveloperApplication(getDeveloperApplication());
        return request;
    }

    public BatchCloseRequest getABachCloseRequest(){
        BatchCloseRequest request = new BatchCloseRequest();
        request.setDeveloperApplication(getDeveloperApplication());
        return request;
    }

    public BatchRetrieveRequest getABatchRetrieveRequest(){
        BatchRetrieveRequest request = new BatchRetrieveRequest();
        request.setDeveloperApplication(getDeveloperApplication());
        return request;
    }

    public BatchCurrentRequest getABatchCurrentRequest(){
        BatchCurrentRequest request = new BatchCurrentRequest();
        request.setDeveloperApplication(getDeveloperApplication());
        return request;
    }

    public DeveloperApplication getDeveloperApplication() {
        DeveloperApplication devApp = new DeveloperApplication();
        devApp.setDeveloperId(Integer.parseInt(config.getProperty("developerId")));
        devApp.setVersion(config.getProperty("versionId"));
        return devApp;
    }

    public Card getCard(){
        Card card = new Card();
        card.setAddress(getAddress());
        card.setCvv("123");
        card.setExpirationDate("07/2018");
        card.setNumber("4111111111111111");
        return card;
    }

    public Check getCheck() {
        Check check = new Check();
        check.setFirstName("Bruce");
        check.setLastName("Wayne");
        check.setRoutingNumber("222371863");
        check.setAccountNumber("123456");
        return check;
    }

    public ExtendedInformation getExtendedInformation(boolean containCATIndicator) {
        ExtendedInformation extendedInfo = new ExtendedInformation();
        extendedInfo.setSoftDescriptor(getRequestSoftDescriptor());
        extendedInfo.setDynamicMCC(getRequestDynamicMCC());
        if(containCATIndicator){
            AdditionalTerminalInfo additionalTerminalInfo = new AdditionalTerminalInfo();
            additionalTerminalInfo.setCATIndicator(getCATIndicator());
            extendedInfo.setAdditionalTerminalInfo(additionalTerminalInfo);
        }
        return extendedInfo;
    }

    public Address getAddress() {
        Address address = new Address();
        address.setCity("Austin");
        address.setCountry("US");
        address.setLine1("123 Main St.");
        address.setState("TX");
        address.setZip("78759");
        return address;
    }

    public Transaction getTransactionFromTransactionList(int transactionId, List<Transaction> transactionList) {
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionId() == transactionId) {
                return transaction;
            }
        }
        return null;
    }
}