package pt.ulisboa.tecnico.softeng.bank.domain;

import java.util.Random;


public abstract class Account extends Account_Base {
    
	protected Account(){
		super();
	}
	
    public Account(Bank bank) {
        super();
        
        setIdentifier(new Random().nextInt());
        setBalance(0);
        
        setBank(bank);
    }
    
    // construtor do savings account
    public void initClass(Bank bank){
    	//  //super();
        
        setIdentifier(new Random().nextInt());
        setBalance(0);
        
        setBank(bank);
		//super.get
		
		//setName(bank.getName());
		//setCode(bank.getCode());

		//FenixFramework.getDomainRoot().addBank(this);
    	
    }
    
    public void delete() {
		// apaga ligacao
		setBank(null);

		//apaga obj da bd ie bidirecional
		deleteDomainObject();
	}
    
    public void deposit(int ammount){
    	super.setBalance(super.getBalance() + ammount); 
    }
    
    public void withdraw(int ammount) throws Exception{
    	int balance = super.getBalance();
		if(balance >= ammount){
    		super.setBalance(balance - ammount);
    	}
    	else{
    		System.out.println("not enough money to withdraw");
    		throw new Exception("not enough money to withdraw");
    	}
    }
    
	
}
