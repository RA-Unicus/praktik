package com.unicus.sv.ga.praktik.tauut.chapter4.bankService.exceptions;

/**
 * Thrown whenever there are insufficient funds in an account to process a given transaction.
 * @author Tariq King
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Insufficient funds for transaction.");
    }

}