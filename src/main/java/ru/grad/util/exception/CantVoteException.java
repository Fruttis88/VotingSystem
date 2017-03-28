package ru.grad.util.exception;

public class CantVoteException extends RuntimeException {
    public CantVoteException(String msg){
        super(msg);
    }
}
