//
//

package test.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import SecureNetRestApiSDK.Api.Requests.*;
import SecureNetRestApiSDK.Api.Responses.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import SNET.Core.APIContext;
import SecureNetRestApiSDK.Api.Controllers.PaymentsController;
import SecureNetRestApiSDK.Api.Models.AdditionalTerminalInfo;
import SecureNetRestApiSDK.Api.Models.Address;
import SecureNetRestApiSDK.Api.Models.Card;
import SecureNetRestApiSDK.Api.Models.Check;
import SecureNetRestApiSDK.Api.Models.DeveloperApplication;
import SecureNetRestApiSDK.Api.Models.ExtendedInformation;
import test.HelperTest;

public class PaymentsControllerTest {
	
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
	 * Unit Tests for an AuthorizationOnly request and a subsequent
	 * PriorAuthCapture request. Tests combined in one method to pass the
	 * required transaction identifier and guaranteee the order of operation.
	 */
	@Test
	public void creditcardpresentauthorizationOnlyandpriorAuthCapturerequestsreturnssuccessfully()
			throws Exception {
		// Arrange
		int transactionId = creditcardpresentauthorizationOnlyrequestreturnssuccessfully();
		boolean includeTip = false;
		PriorAuthCaptureRequest request = helper.getAPriorAuthCaptureRequest(transactionId,includeTip);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		PriorAuthCaptureResponse response = (PriorAuthCaptureResponse) controller.processRequest(apiContext, request, PriorAuthCaptureResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
	}

	/**
	 * Successful response returned from a Credit Card Present Authorization
	 * Only request.
	 * https://apidocs.securenet.com/docs/creditcardpresent.html?lang
	 * =JSON#authonly
	 */
	private int creditcardpresentauthorizationOnlyrequestreturnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = false;
		AuthorizeRequest request = helper.getAnAuthorizeRequiest(containCATIndicator);
		request.setAddToVault(true);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		AuthorizeResponse response = (AuthorizeResponse) controller.processRequest(apiContext, request,AuthorizeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());
		return response.getTransaction().getTransactionId();
	}

	/**
	 * Unit Tests for an AuthorizationOnly request and a subsequent
	 * PriorAuthCapture request including CAT field. Tests combined in one method to pass the
	 * required transaction identifier and guaranteee the order of operation.
	 */
	@Test
	public void creditcardpresentauthorizationOnlyandpriorAuthCapturerequestsWithCATIndicatorreturnssuccessfully()
			throws Exception {
		// Arrange
		int transactionId = creditcardpresentauthorizationOnlyrequestWithCATIndicatorreturnssuccessfully();
		boolean includeTip = false;
		PriorAuthCaptureRequest request = helper.getAPriorAuthCaptureRequest(transactionId,includeTip);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		PriorAuthCaptureResponse response = (PriorAuthCaptureResponse) controller.processRequest(apiContext, request, PriorAuthCaptureResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());
	}

	/**
	 * Successful response returned from a Credit Card Present Authorization including CAT field
	 * Only request.
	 * https://apidocs.securenet.com/docs/creditcardpresent.html?lang
	 * =JSON#authonly
	 */
	private int creditcardpresentauthorizationOnlyrequestWithCATIndicatorreturnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = true;
		AuthorizeRequest request = helper.getAnAuthorizeRequiest(containCATIndicator);
		request.setAddToVault(true);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		AuthorizeResponse response = (AuthorizeResponse) controller.processRequest(apiContext, request,AuthorizeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());
		return response.getTransaction().getTransactionId();
	}


	/**
	 * Successful response returned from a Credit Card Present Charge request.
	 * https
	 * ://apidocs.securenet.com/docs/creditcardpresent.html?lang=JSON#charge
	 */
	@Test
	public void creditcardpresentchargerequestreturnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = false;
		ChargeRequest request = helper.getAChargeRequest(containCATIndicator);
		request.setAddToVault(true);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());
	}

	/**
	 * Successful response returned from a Credit Card Present Charge request including CAT field.
	 * https
	 * ://apidocs.securenet.com/docs/creditcardpresent.html?lang=JSON#charge
	 */
	@Test
	public void creditcardpresentchargerequestWitchCATIndicatorreturnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = true;
		ChargeRequest request = helper.getAChargeRequest(containCATIndicator);
		request.setAddToVault(true);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());
	}

	/**
	 * Unit Tests for an IncludeTip AuthorizationOnly request and a subsequent
	 * PriorAuthCapture request. Tests combined in one method to pass the
	 * required transaction identifier and guaranteee the order of operation.
	 */
	@Test
	public void creditcardpresentincludeTipauthorizationOnlyandpriorAuthCapturerequestsreturnssuccessfully()
			throws Exception {
		// Arrange
		int transactionId = creditcardpresentincludeTipauthorizationOnlyrequestreturnssuccessfully();
		boolean includeTip = true;
		PriorAuthCaptureRequest request = helper.getAPriorAuthCaptureRequest(transactionId,includeTip);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		PriorAuthCaptureResponse response = (PriorAuthCaptureResponse) controller.processRequest(apiContext, request,PriorAuthCaptureResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
	}

	/**
	 * Successful response returned from a Credit Card Present Include Tip
	 * AuthorizationOnly request.
	 * https://apidocs.securenet.com/docs/creditcardpresent
	 * .html?lang=JSON#includetip
	 */
	private int creditcardpresentincludeTipauthorizationOnlyrequestreturnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = false;
		AuthorizeRequest request =helper.getAnAuthorizeRequiest(containCATIndicator);
		request.setAddToVault(true);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		AuthorizeResponse response = (AuthorizeResponse) controller.processRequest(apiContext, request,AuthorizeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());
		return response.getTransaction().getTransactionId();
	}

	/**
	 * Unit Tests for an IncludeTip AuthorizationOnly request and a subsequent
	 * PriorAuthCapture request Include CAT field. Tests combined in one method to pass the
	 * required transaction identifier and guaranteee the order of operation.
	 */
	@Test
	public void creditcardpresentincludeTipauthorizationOnlyandpriorAuthCapturerequestsWithCATIndicatorreturnssuccessfully()
			throws Exception {
		// Arrange
		int transactionId = creditcardpresentincludeTipauthorizationOnlyrequestretWithCATIndicatorurnssuccessfully();
		boolean includeTip = true;
		PriorAuthCaptureRequest request = helper.getAPriorAuthCaptureRequest(transactionId,includeTip);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		PriorAuthCaptureResponse response = (PriorAuthCaptureResponse) controller.processRequest(apiContext, request,PriorAuthCaptureResponse.class);
		// Assert
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
	}

	/**
	 * Successful response returned from a Credit Card Present Include Tip
	 * AuthorizationOnly request Include CAT field.
	 * https://apidocs.securenet.com/docs/creditcardpresent
	 * .html?lang=JSON#includetip
	 */
	private int creditcardpresentincludeTipauthorizationOnlyrequestretWithCATIndicatorurnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = true;
		AuthorizeRequest request =helper.getAnAuthorizeRequiest(containCATIndicator);
		request.setAddToVault(true);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		AuthorizeResponse response = (AuthorizeResponse) controller.processRequest(apiContext, request,AuthorizeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());
		return response.getTransaction().getTransactionId();
	}


	/**
	 * Successful response returned from a Credit Card Present Charge request
	 * that includes the address.
	 * https://apidocs.securenet.com/docs/creditcardpresent
	 * .html?lang=JSON#includeaddress
	 */
	@Test
	public void creditcardpresentchargerequestincludingaddressreturnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = false;
		ChargeRequest request = helper.getAChargeRequest(containCATIndicator);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());
	}

	/**
	 * Successful response returned from a Credit Card Present Charge request
	 * that includes the address and CAT field.
	 * https://apidocs.securenet.com/docs/creditcardpresent
	 * .html?lang=JSON#includeaddress
	 */
	@Test
	public void creditcardpresentchargerequestincludingaddressWithCATIndicatorreturnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = true;
		ChargeRequest request = helper.getAChargeRequest(containCATIndicator);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());
	}

	/**
	 * Successful response returned from a Verify request including CAT field
	 * Only request.
	 * https://apidocs.securenet.com/docs/creditcardpresent.html?lang
	 * =json#verify
	 */
	@Test
	public void creditcardpresentverifyrequestreturnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = false;
		VerifyRequest request = helper.getAVerifyRequest(containCATIndicator);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		VerifyResponse response = (VerifyResponse) controller.processRequest(apiContext, request,VerifyResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());
	}

	/**
	 * Successful response returned from a Verify request including CAT field
	 * Only request.
	 * https://apidocs.securenet.com/docs/creditcardpresent.html?lang
	 * =json#verify
	 */
	@Test
	public void creditcardpresentverifyrequestWithCATIndicatorreturnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = true;
		VerifyRequest request = helper.getAVerifyRequest(containCATIndicator);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		VerifyResponse response = (VerifyResponse) controller.processRequest(apiContext, request,VerifyResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());
	}

	/**
	 * Unit Tests for an AuthorizationOnly request and a subsequent
	 * PriorAuthCapture request. Tests combined in one method to pass the
	 * required transaction identifier and guaranteee the order of operation.
	 */
	@Test
	public void creditcardnotpresentauthorizationOnlyandpriorAuthCapturerequestsreturnssuccessfully()
			throws Exception {
		// Arrange
		int transactionId = creditcardnotpresentauthorizationOnlyrequestreturnssuccessfully();
		PriorAuthCaptureRequest request = new PriorAuthCaptureRequest();
		request.setAmount(10d);
		request.setDeveloperApplication(helper.getDeveloperApplication());
		request.setTransactionId(transactionId);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		PriorAuthCaptureResponse response = (PriorAuthCaptureResponse) controller.processRequest(apiContext, request,PriorAuthCaptureResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
	}

	/**
	 * Successful response returned from a Credit Card Not Present Authorization
	 * Only request.
	 * https://apidocs.securenet.com/docs/creditcardnotpresent.html
	 * ?lang=JSON#authonly
	 */
	private int creditcardnotpresentauthorizationOnlyrequestreturnssuccessfully()
			throws Exception {
		// Arrange
		AuthorizeRequest request = new AuthorizeRequest();
		request.setDeveloperApplication(helper.getDeveloperApplication());
		request.setCard(helper.getCard());
		request.setAddToVault(true);
		request.setAmount(20d);
		request.setExtendedInformation(helper.getExtendedInformation(false));
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		AuthorizeResponse response = (AuthorizeResponse) controller.processRequest(apiContext, request,AuthorizeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());

		return response.getTransaction().getTransactionId();
	}


	/**
	 * Successful response returned from a Charge-Authorization and Capture
	 * request.
	 * https://apidocs.securenet.com/docs/creditcardnotpresent.html?lang
	 * =JSON#charge
	 */
	@Test
	public void creditcardnotpresentchargeauthorizationandcapturerequestreturnssuccessfully()
			throws Exception {
		// Arrange
		ChargeRequest request = new ChargeRequest();
		request.setCard(helper.getCard());
		request.setAddToVault(true);
		request.setAmount(100d);
		request.setDeveloperApplication(helper.getDeveloperApplication());
		ExtendedInformation extendedInfo = helper.getExtendedInformation(false);
		extendedInfo.setTypeOfGoods("PHYSICAL");
		request.setExtendedInformation(extendedInfo);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());
	}

	/**
	 * Successful response returned from an Include Address request.
	 * https://apidocs
	 * .securenet.com/docs/creditcardnotpresent.html?lang=JSON#includeaddress
	 */
	@Test
	public void creditcardnotpresentincludeaddresrequestreturnssuccessfully()
			throws Exception {
		// Arrange
		ChargeRequest request = new ChargeRequest();
		request.setCard(helper.getCard());
		request.setAmount(80d);
		request.setDeveloperApplication(helper.getDeveloperApplication());
		ExtendedInformation extendedInfo = helper.getExtendedInformation(false);
		extendedInfo.setTypeOfGoods("PHYSICAL");
		request.setExtendedInformation(extendedInfo);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());
	}

	/**
	 * Successful response returned from an Charge using Tokenization request.
	 * https://apidocs.securenet.com/docs/creditcardnotpresent.html?lang=csharp#
	 * tokenization
	 */
	private void creditcardnotpresentchargeusingtokenizationrequestreturnssuccessfully(
			String token) throws Exception {
		// Arrange
		ChargeRequest request = new ChargeRequest();
		request.setCard(helper.getCard());
		request.setAmount(80d);
		request.setDeveloperApplication(helper.getDeveloperApplication());
		ExtendedInformation extendedInfo = helper.getExtendedInformation(false);
		extendedInfo.setTypeOfGoods("PHYSICAL");
		request.setExtendedInformation(extendedInfo);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());
	}

	/**
	 * Successful response returned from an ACH Pay By Check Charge request.
	 * https://apidocs.securenet.com/docs/ach.html?lang=csharp#charge
	 */
	@Test
	public void aCHpaybycheckchargerequestreturnssuccessfully()
			throws Exception {
		// Arrange
		ChargeRequest request = new ChargeRequest();
		request.setCheck(helper.getCheck());
		request.setAmount(100d);
		request.setDeveloperApplication(helper.getDeveloperApplication());
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
	}

	/**
	 * Successful response returned from an ACH POS Charge request.
	 * https://apidocs.securenet.com/docs/ach.html?lang=csharp#chargepos
	 */
	@Test
	public void aCHchargeaccountusingPOSrequestreturnssuccessfully()
			throws Exception {
		// Arrange
		ChargeRequest request = new ChargeRequest();

		ExtendedInformation extendedInfo = new ExtendedInformation();
		AdditionalTerminalInfo additionalInfo = new AdditionalTerminalInfo();
		additionalInfo.setStoreNumber("452");
		additionalInfo.setTerminalCity("Austin");
		additionalInfo.setTerminalId("1234");
		additionalInfo.setTerminalLocation("Office");
		additionalInfo.setTerminalState("TX");
		extendedInfo.setAdditionalTerminalInfo(additionalInfo);
		
		Check check = helper.getCheck();
		check.setCheckType("POINT_OF_SALE");
		check.setVerification("NONE");
		request.setCheck(check);
		
		request.setExtendedInformation(extendedInfo);
		request.setAmount(11d);
		request.setDeveloperApplication(helper.getDeveloperApplication());
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());
	}

	/**
	 * Successful response returned from an ACH Add Billing Address Charge
	 * request.
	 * https://apidocs.securenet.com/docs/ach.html?lang=csharp#billaddress
	 */
	@Test
	public void aCHaddbillingaddresschargerequestreturnssuccessfully()
			throws Exception {
		// Arrange
		ChargeRequest request = new ChargeRequest();
		
		Check check = helper.getCheck();
		check.setAddress(helper.getAddress());
		
		request.setCheck(check);
		request.setAmount(11d);
		request.setDeveloperApplication(helper.getDeveloperApplication());
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
	}

	/**
	 * Successful response returned from an ACH Paying By Check with
	 * Verification Charge request.
	 * https://apidocs.securenet.com/docs/ach.html?lang=csharp#verification
	 */
	@Test
	public void aCHpayingbycheckwithverificationchargerequestreturnssuccessfully()
			throws Exception {
		// Arrange
		ChargeRequest request = new ChargeRequest();
		
		Check check = helper.getCheck();
		check.setVerification("ACH_PROVIDER");
		
		request.setCheck(check);
		request.setAmount(11d);
		request.setDeveloperApplication(helper.getDeveloperApplication());
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
	}

	/**
	 * Successful response returned from a Credit An Account request.
	 * https://apidocs.securenet.com/docs/credits.html?lang=csharp
	 */
	@Test
	public void creditscreditanaccountrequestreturnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = false;
		CreditRequest request = helper.getACreditRequest(containCATIndicator);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		CreditResponse  response = (CreditResponse) controller.processRequest(apiContext, request,CreditResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());
	}

	/**
	 * Successful response returned from a Credit An Account request Include CAT field.
	 * https://apidocs.securenet.com/docs/credits.html?lang=csharp
	 */
	@Test
	public void creditscreditanaccountrequestWithCATIndicatorreturnssuccessfully()
			throws Exception {
		// Arrange
		boolean containCATIndicator = true;
		CreditRequest request = helper.getACreditRequest(containCATIndicator);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		CreditResponse  response = (CreditResponse) controller.processRequest(apiContext, request,CreditResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());
	}


	/**
	 * Unit Tests for a Charge request and a subsequent Refund request. Tests
	 * combined in one method to pass the required transaction identifier and
	 * guaranteee the order of operation.
	 */
	@Test
	public void refundsChargeandrefundrequestsreturnssuccessfully()
			throws Exception {
		int transactionId = refundschargerequestreturnssuccessfully();
		RefundRequest request = helper.getARefundRequest(transactionId);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		RefundResponse response = (RefundResponse) controller.processRequest(apiContext, request,RefundResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
	}

	/**
	 * Successful response returned from a Credit Card Present Charge request.
	 * https
	 * ://apidocs.securenet.com/docs/creditcardpresent.html?lang=JSON#charge
	 */
	private int refundschargerequestreturnssuccessfully() throws Exception {
		// Arrange
		boolean containCATIndicator = false;
		ChargeRequest request = helper.getAChargeRequest(containCATIndicator);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());

		return response.getTransaction().getTransactionId();
	}

	/**
	 * Unit Tests for a Charge request and a subsequent Refund request Include CAT field. Tests
	 * combined in one method to pass the required transaction identifier and
	 * guaranteee the order of operation.
	 */
	@Test
	public void refundsChargeandrefundrequestWithCATIndicatorsreturnssuccessfully()
			throws Exception {
		int transactionId = refundschargerequestWithCATIndicatorreturnssuccessfully();
		RefundRequest request = helper.getARefundRequest(transactionId);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		RefundResponse response = (RefundResponse) controller.processRequest(apiContext, request,RefundResponse.class);
		// Assert
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
	}

	/**
	 * Successful response returned from a Credit Card Present Charge request Include CAT field.
	 * https
	 * ://apidocs.securenet.com/docs/creditcardpresent.html?lang=JSON#charge
	 */
	private int refundschargerequestWithCATIndicatorreturnssuccessfully() throws Exception {
		// Arrange
		boolean containCATIndicator = true;
		ChargeRequest request = helper.getAChargeRequest(containCATIndicator);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());

		return response.getTransaction().getTransactionId();
	}

	/**
	 * Unit Tests for a Chrage request and a subsequent Void request. Tests
	 * combined in one method to pass the required transaction identifier and
	 * guaranteee the order of operation.
	 */
	@Test
	public void voidschargeandvoidrequestsreturnssuccessfully()
			throws Exception {
		// Arrange
		int transactionId = voidschargerequestreturnssuccessfully();
		VoidRequest request = helper.getAVoidRequest(transactionId);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		VoidResponse response = (VoidResponse) controller.processRequest(apiContext, request,VoidResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
	}

	/**
	 * Successful response returned from a Credit Card Present Charge request.
	 * https
	 * ://apidocs.securenet.com/docs/creditcardpresent.html?lang=JSON#charge
	 */
	private int voidschargerequestreturnssuccessfully() throws Exception {
		// Arrange
		boolean containCATIndicator = false;
		ChargeRequest request = helper.getAChargeRequest(containCATIndicator);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());
		return response.getTransaction().getTransactionId();
	}

	/**
	 * Unit Tests for a Chrage request and a subsequent Void request Include CAT field. Tests
	 * combined in one method to pass the required transaction identifier and
	 * guaranteee the order of operation.
	 */
	@Test
	public void voidschargeandvoidrequestsWithCATIndicatorreturnssuccessfully()
			throws Exception {
		// Arrange
		int transactionId = voidschargerequestWithCATIndicatorreturnssuccessfully();
		VoidRequest request = helper.getAVoidRequest(transactionId);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		VoidResponse response = (VoidResponse) controller.processRequest(apiContext, request,VoidResponse.class);
		// Assert
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
	}

	/**
	 * Successful response returned from a Credit Card Present Charge request Include CAT field.
	 * Successful response returned from a Credit Card Present Charge request Include CAT field.
	 * https
	 * ://apidocs.securenet.com/docs/creditcardpresent.html?lang=JSON#charge
	 */
	private int voidschargerequestWithCATIndicatorreturnssuccessfully() throws Exception {
		// Arrange
		boolean containCATIndicator = true;
		ChargeRequest request = helper.getAChargeRequest(containCATIndicator);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		// Assert
		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getCATIndicator(), helper.getCATIndicator());
		return response.getTransaction().getTransactionId();
	}

//	private Address getAddress() {
//		Address address = new Address();
//		address.setCity("Austin");
//		address.setCountry("US");
//		address.setLine1("123 Main St.");
//		address.setState("TX");
//		address.setZip("78759");
//		return address;
//	}
//
//	private DeveloperApplication getDeveloperApplication() {
//		DeveloperApplication devApp = new DeveloperApplication();
//		devApp.setDeveloperId(Integer.parseInt(config.getProperty("developerId")));
//		devApp.setVersion(config.getProperty("versionId"));
//		return devApp;
//	}
//
//	private Card getCard(){
//		Card card = new Card();
//		card.setAddress(getAddress());
//		card.setCvv("123");
//		card.setExpirationDate("07/2018");
//		card.setNumber("4111111111111111");
//		return card;
//	}
//
//	private Check getCheck() {
//		Check check = new Check();
//		check.setFirstName("Bruce");
//		check.setLastName("Wayne");
//		check.setRoutingNumber("222371863");
//		check.setAccountNumber("123456");
//		return check;
//	}
//
//	private ExtendedInformation getExtendedInformation() {
//		ExtendedInformation extendedInfo = new ExtendedInformation();
//		extendedInfo.setSoftDescriptor(helper.getRequestSoftDescriptor());
//		extendedInfo.setDynamicMCC(helper.getRequestDynamicMCC());
//		AdditionalTerminalInfo additionalInfo = new AdditionalTerminalInfo();
//		additionalInfo.setCATIndicator(helper.getRequestCATIndicator());
//		extendedInfo.setAdditionalTerminalInfo(additionalInfo);
//		return extendedInfo;
//	}
}
