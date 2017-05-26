package pt.ulisboa.tecnico.softeng.bank.domain;

import pt.ist.fenixframework.FenixFramework;

public class Bank extends Bank_Base {

	public Bank(String name, String code) {
		setName(name);
		setCode(code);

		FenixFramework.getDomainRoot().addBank(this);
	}

	public void delete() {
		// apaga ligacao
		setRoot(null);

		// TODO for each account delete
		for(Account ac : getAccountSet()){
			ac.delete();
		}
		//apaga obj da bd ie bidirecional
		deleteDomainObject();
	}
	
	public int totalBalance(){
		int sum_balance = 0;
		for( Account ac : this.getAccountSet()){
			sum_balance += ac.getBalance();
		}
		return sum_balance;
	}

	public static Bank getBankByCode(String code) {
		for (Bank bank : FenixFramework.getDomainRoot().getBankSet()) {
			if (bank.getCode().equals(code)) {
				return bank;
			}
		}
		return null;
	}

}
