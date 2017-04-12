//
//

package test.controllers;

import java.io.InputStream;
import java.util.Properties;

import SecureNetRestApiSDK.Api.Controllers.PaymentsController;
import SecureNetRestApiSDK.Api.Models.Transaction;
import SecureNetRestApiSDK.Api.Requests.ChargeRequest;
import SecureNetRestApiSDK.Api.Responses.ChargeResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import SNET.Core.APIContext;
import SecureNetRestApiSDK.Api.Controllers.BatchesController;
import SecureNetRestApiSDK.Api.Models.DeveloperApplication;
import SecureNetRestApiSDK.Api.Requests.BatchCloseRequest;
import SecureNetRestApiSDK.Api.Requests.BatchCurrentRequest;
import SecureNetRestApiSDK.Api.Requests.BatchRetrieveRequest;
import SecureNetRestApiSDK.Api.Responses.BatchCloseResponse;
import SecureNetRestApiSDK.Api.Responses.BatchCurrentResponse;
import SecureNetRestApiSDK.Api.Responses.BatchRetrieveResponse;
import test.HelperTest;

public class BatchesControllerTest {

    Properties config;
    HelperTest helper;

    @Before
    public void before() throws Exception {
        InputStream stream = this.getClass().getResourceAsStream("/config.properties");
        config = new Properties();
        config.load(stream);
        helper = new HelperTest();
    }

    /**
     * Successful response returned from a Settlement Close Batch request.
     * https://apidocs.securenet.com/docs/settlement.html?lang=csharp#closebatch
     */
    @Test
    public void settlementclosebatchrequestreturnssuccessfully() throws Exception {
        boolean containCATIndicator = false;
        createTransaction(containCATIndicator);
        getBatchCloseResponse();
    }

    /**
     * Successful response returned from a Settlement Close Batch request Include CAT field.
     * https://apidocs.securenet.com/docs/settlement.html?lang=csharp#closebatch
     */
    @Test
    public void settlementclosebatchrequestWithCATIndicatorreturnssuccessfully() throws Exception {
        boolean containCATIndicator = true;
        int transactionId = createTransaction(containCATIndicator);
        BatchCloseResponse closeResponse = getBatchCloseResponse();
        Transaction searchedTransaction = helper.getTransactionFromTransactionList(transactionId,closeResponse.getTransactions());
        Assert.assertEquals(searchedTransaction.getCATIndicator(),helper.getCATIndicator());
    }

    /**
     * Successful response returned from a Settlement Retrieve Closed Batch request.
     * https://apidocs.securenet.com/docs/settlement.html?lang=csharp#retrievebatch
     */
    @Test
    public void settlementretrieveclosedbatchrequestreturnssuccessfully() throws Exception {
        // Arrange
        boolean containCATIndicator = false;
        createTransaction(containCATIndicator);
        BatchCloseResponse closeResponse = getBatchCloseResponse();

        BatchRetrieveRequest request = helper.getABatchRetrieveRequest();
        request.setId(Integer.valueOf(closeResponse.getBatchId()));
        APIContext apiContext = new APIContext();
        BatchesController controller = new BatchesController();
        // Act
        BatchRetrieveResponse response = (BatchRetrieveResponse) controller.processRequest(apiContext, request, BatchRetrieveResponse.class);
        // Assert
        Assert.assertTrue(response.toResponseString(), response.getSuccess());

    }

    /**
     * Successful response returned from a Settlement Retrieve Closed Batch request Include CAT field.
     * https://apidocs.securenet.com/docs/settlement.html?lang=csharp#retrievebatch
     */
    @Test
    public void settlementretrieveclosedbatchrequestWithCATIndicatorreturnssuccessfully() throws Exception {
        // Arrange
        boolean containCATIndicator = true;
        int transactionId = createTransaction(containCATIndicator);
        BatchCloseResponse closeResponse = getBatchCloseResponse();

        BatchRetrieveRequest request = helper.getABatchRetrieveRequest();
        request.setId(Integer.valueOf(closeResponse.getBatchId()));
        APIContext apiContext = new APIContext();
        BatchesController controller = new BatchesController();
        // Act
        BatchRetrieveResponse response = (BatchRetrieveResponse) controller.processRequest(apiContext, request, BatchRetrieveResponse.class);
        // Assert
        Assert.assertTrue(response.toResponseString(), response.getSuccess());
        Transaction searchedTransaction = helper.getTransactionFromTransactionList(transactionId,response.getTransactions());
        Assert.assertEquals(searchedTransaction.getCATIndicator(),helper.getCATIndicator());
    }

    /*
    * Successful response returned from a Settlement Retrieve Current Batch request.
    * https://apidocs.securenet.com/docs/settlement.html?lang=csharp#currentbatch
    */
    @Test
    public void settlementretrievingcurrentbatchrequestreturnssuccessfully() throws Exception {
        // Arrange
        BatchCurrentRequest request = helper.getABatchCurrentRequest();
        APIContext apiContext = new APIContext();
        BatchesController controller = new BatchesController();
        // Act
        BatchCurrentResponse response = (BatchCurrentResponse) controller.processRequest(apiContext, request, BatchCurrentResponse.class);
        // Assert
        Assert.assertTrue(response.toResponseString(), response.getSuccess());
    }

    private BatchCloseResponse getBatchCloseResponse() throws Exception {
        BatchCloseRequest request = helper.getABachCloseRequest();
        APIContext apiContext = new APIContext();
        BatchesController controller = new BatchesController();
        BatchCloseResponse response = (BatchCloseResponse) controller.processRequest(apiContext, request, BatchCloseResponse.class);
        Assert.assertTrue(response.toResponseString(), response.getSuccess());
        return response;
    }

    private int createTransaction(boolean containCATIndicator)
            throws Exception {
        // Arrange
        ChargeRequest request = helper.getAChargeRequest(containCATIndicator);
        APIContext apiContext = new APIContext();
        PaymentsController controller = new PaymentsController();
        // Act
        ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request, ChargeResponse.class);
        // Assert
        Assert.assertTrue(response.toResponseString(), response.getSuccess());
        return response.getTransaction().getTransactionId();
    }

}