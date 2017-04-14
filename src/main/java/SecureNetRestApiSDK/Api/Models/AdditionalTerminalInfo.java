//
//  9/17/2015 1:32:18 PM
//

package SecureNetRestApiSDK.Api.Models;


/**
* Additional infomation for terminal details.
*/
public class AdditionalTerminalInfo   
{
    /**
    * Terminal identifier.
    */
    private String terminalId = new String();
    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String value) {
        terminalId = value;
    }

    /**
    * City where the terminal is located.
    */
    private String terminalCity = new String();
    public String getTerminalCity() {
        return terminalCity;
    }

    public void setTerminalCity(String value) {
        terminalCity = value;
    }

    /**
    * State where the terminal is located.
    */
    private String terminalState = new String();
    public String getTerminalState() {
        return terminalState;
    }

    public void setTerminalState(String value) {
        terminalState = value;
    }

    /**
    * Additional terminal location information.
    */
    private String terminalLocation = new String();
    public String getTerminalLocation() {
        return terminalLocation;
    }

    public void setTerminalLocation(String value) {
        terminalLocation = value;
    }

    /**
    * Store number where the terminal is located.
    */
    private String storeNumber = new String();
    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String value) {
        storeNumber = value;
    }
    /**
     * Represents the value for a Cardholder Activated Terminal type of transaction.
     */
    private String catIndicator = new String();
    public String getCAIndicator() {
        return catIndicator;
    }

    public void setCATIndicator(String value) {
        catIndicator = value;
    }

}


