package test;

public enum PosCardholderActivatedTerminal
{
    NotCatTransaction(0),
    AtmWithPin(1),
    SelfServiceTerminal(2),
    LimitedAmountTerminal(3),
    InFlightCommerce(4),
    ElectronicCommerce(6),
    TransponderTransactio(7);

    private final int catIndicatorValue;

    PosCardholderActivatedTerminal(int CATValue){
        catIndicatorValue = CATValue;
    }


public String getCATIndicatorValue(){
        return  String.valueOf(catIndicatorValue);
    }
}

