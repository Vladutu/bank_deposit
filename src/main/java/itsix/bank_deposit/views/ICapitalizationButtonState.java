package itsix.bank_deposit.views;

public interface ICapitalizationButtonState {

    ICapitalizationButtonState nextState();

    void execute();

    void setNextState(ICapitalizationButtonState state);

    ICapitalizationButtonState reset();

    void setInitialState(ICapitalizationButtonState initialState);
}
