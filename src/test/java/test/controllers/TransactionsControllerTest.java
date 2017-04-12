//
//

package test.controllers;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import SecureNetRestApiSDK.Api.Models.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import SNET.Core.APIContext;
import SecureNetRestApiSDK.Api.Controllers.PaymentsController;
import SecureNetRestApiSDK.Api.Controllers.TransactionsController;
import SecureNetRestApiSDK.Api.Models.Address;
import SecureNetRestApiSDK.Api.Models.Card;
import SecureNetRestApiSDK.Api.Models.DeveloperApplication;
import SecureNetRestApiSDK.Api.Requests.AuthorizeRequest;
import SecureNetRestApiSDK.Api.Requests.TransactionRetrieveRequest;
import SecureNetRestApiSDK.Api.Requests.TransactionSearchRequest;
import SecureNetRestApiSDK.Api.Requests.TransactionUpdateRequest;
import SecureNetRestApiSDK.Api.Responses.AuthorizeResponse;
import SecureNetRestApiSDK.Api.Responses.TransactionRetrieveResponse;
import SecureNetRestApiSDK.Api.Responses.TransactionSearchResponse;
import SecureNetRestApiSDK.Api.Responses.TransactionUpdateResponse;
import test.HelperTest;

public class TransactionsControllerTest   
{
	
	Properties config ;
	HelperTest helper;
	
	@Before
	public void before() throws Exception{
		InputStream stream  = this.getClass().getResourceAsStream("/config.properties");
		config = new Properties();
		config.load(stream);
		helper = new HelperTest();
	}
    
    /**
    * Successful response returned from a Search Transaction request.
    * https://apidocs.securenet.com/docs/transactions.html?lang=csharp#search
    */
	@Test
    public void transactionreportingandmanagementsearchtransactionrequestreturnssuccessfully() throws Exception {
        // Arramge
        TransactionSearchRequest request = helper.getATransactionSearchRequiest();
        boolean containCATIndicator = false;
		request.setTransactionId(createTransaction(containCATIndicator));
        APIContext apiContext = new APIContext();
        TransactionsController controller = new TransactionsController();
        // Act
        TransactionSearchResponse response = (TransactionSearchResponse) controller.processRequest(apiContext,request,TransactionSearchResponse.class);
        // Assert
        Assert.assertTrue(response.toResponseString(), response.getSuccess());
    }

	/**
	 * Successful response returned from a Search Transaction request Include CAT field.
	 * https://apidocs.securenet.com/docs/transactions.html?lang=csharp#search
	 */
	@Test
	public void transactionreportingandmanagementsearchtransactionrequestWithCATIndicatorreturnssuccessfully() throws Exception {
		// Arramge
		TransactionSearchRequest request = helper.getATransactionSearchRequiest();
		boolean containCATIndicator = true;
		request.setTransactionId(createTransaction(containCATIndicator));
		APIContext apiContext = new APIContext();
		TransactionsController controller = new TransactionsController();
		// Act
		TransactionSearchResponse response = (TransactionSearchResponse) controller.processRequest(apiContext,request,TransactionSearchResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		List<Transaction> transactions = response.getTransactions();
		for(Transaction transaction : transactions){
			Assert.assertEquals(transaction.getCATIndicator(),helper.getCATIndicator());
		}
	}

    /**
    * Successful response returned from a Retrieve Transaction request.
    * https://apidocs.securenet.com/docs/transactions.html?lang=csharp#retrieve
    */
	@Test
    public void transactionreportingandmanagementretrievetransactionrequestreturnssuccessfully() throws Exception {
        // Arrange
        TransactionRetrieveRequest request = helper.getATransactionRetrieveRequest();
		boolean containCATIndicator = false;
        request.setTransactionId(createTransaction(containCATIndicator));
        APIContext apiContext = new APIContext();
        TransactionsController controller = new TransactionsController();
        // Act
        TransactionRetrieveResponse response = (TransactionRetrieveResponse) controller.processRequest(apiContext,request,TransactionRetrieveResponse.class);
        // Assert
        Assert.assertTrue(response.toResponseString(), response.getSuccess());
    }

	/**
	 * Successful response returned from a Retrieve Transaction request Include CAT field.
	 * https://apidocs.securenet.com/docs/transactions.html?lang=csharp#retrieve
	 */
	@Test
	public void transactionreportingandmanagementretrievetransactionrequestWithCATIndicatorreturnssuccessfully() throws Exception {
		// Arrange
		TransactionRetrieveRequest request = helper.getATransactionRetrieveRequest();
		boolean containCATIndicator = true;
		request.setTransactionId(createTransaction(containCATIndicator));
		APIContext apiContext = new APIContext();
		TransactionsController controller = new TransactionsController();
		// Act
		TransactionRetrieveResponse response = (TransactionRetrieveResponse) controller.processRequest(apiContext,request,TransactionRetrieveResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		List<Transaction> transactions = response.getTransactions();
		for(Transaction transaction : transactions){
			Assert.assertEquals(transaction.getCATIndicator(),helper.getCATIndicator());
		}
	}

    /**
    * Successful response returned from an Update Transaction request.
    * https://apidocs.securenet.com/docs/transactions.html?lang=csharp#update
    */
	@Test
    public void transactionreportingandmanagementupdatetransactionrequestreturnssuccessfully() throws Exception {
        // Arrange
        TransactionUpdateRequest request = helper.getATransactionUpdateRequest();
        boolean containCATIndicator = false;
        request.setReferenceTransactionId(createTransaction(containCATIndicator));
        APIContext apiContext = new APIContext();
        TransactionsController controller = new TransactionsController();
        // Act
        TransactionUpdateResponse response = (TransactionUpdateResponse) controller.processRequest(apiContext,request,TransactionUpdateResponse.class);
        // Assert
        Assert.assertTrue(response.toResponseString(), response.getSuccess());
    }

    private int createTransaction(boolean containCATIndicator)
			throws Exception {
		// Arrange
		AuthorizeRequest request = helper.getAnAuthorizeRequiest(containCATIndicator);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		AuthorizeResponse response = (AuthorizeResponse) controller.processRequest(apiContext, request,AuthorizeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		return response.getTransaction().getTransactionId();
	}

}


