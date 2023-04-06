package za.co.makgwalet.bankingapplication.exception;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String message){ super(message); }
}
