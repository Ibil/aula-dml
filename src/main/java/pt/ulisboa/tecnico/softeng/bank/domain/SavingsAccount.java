package pt.ulisboa.tecnico.softeng.bank.domain;

import pt.ist.fenixframework.FenixFramework;

public class SavingsAccount extends SavingsAccount_Base {
    
    public SavingsAccount(Bank bank) {
        super();
        initClass(bank);
    }
    
//    Igual ao de Account
//    public void delete() {
//		// apaga ligacao
//		setBank(null);
//
//		//apaga obj da bd ie bidirecional
//		deleteDomainObject();
//	}
    
    @Override
    public void deposit(int ammount){
    	if(ammount %100 == 0){
    		super.setBalance(super.getBalance() + ammount); 
    	}
    }
    
    @Override
    public void withdraw(int ammount) throws Exception{
    	int balance = super.getBalance();
		if(balance == ammount){
    		super.setBalance(balance - ammount);
    	}
    }
    
}
