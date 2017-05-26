package pt.ulisboa.tecnico.softeng.bank.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



import org.junit.After;
import org.junit.Test;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;

public class BankPersistenceTest {
	
	
	
	
	
	@Test
	public void success() {
		atomicProcess();
		atomicAssert();
	}
	@Atomic(mode = TxMode.WRITE)
	public void atomicProcess() {
		new Bank("Money", "BK01");
	}
	@Atomic(mode = TxMode.READ)
	public void atomicAssert() {
		Bank bank = Bank.getBankByCode("BK01");

		assertEquals("Money", bank.getName());
	}
	
	
	// Account is now abstract
//	@Test
//	@Atomic(mode = TxMode.WRITE)
//	public void addAccountToBank(){
//		Bank bank = new Bank("CGD", "PT50");
//		Account account = new Account(bank);
//		assertEquals(0, account.getBank().getCode().compareTo(bank.getCode()));
//		account.deposit(100);
//		
//		Bank b1 = FenixFramework.getDomainRoot().getBankSet().iterator().next();
//		assertEquals(100, b1.getAccountSet().iterator().next().getBalance());
//		
//		try {
//			account.withdraw(75);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		assertEquals(25, b1.getAccountSet().iterator().next().getBalance());
//		
//	}
	
	// Account is now abstract
//	@Test
//	@Atomic(mode = TxMode.WRITE)
//	public void sumAccountBalance(){
//		Bank bank = new Bank("CGD", "PT50");
//		
//		Account account = new Account(bank);
//		account.deposit(100);
//		
//		
//		Account ac2 = new Account(bank);
//		ac2.deposit(75);
//		
//		assertEquals(175, bank.totalBalance());
//	}
	
	@Test
	@Atomic(mode = TxMode.WRITE)
	public void addSvingsAccountToBank(){
		Bank bank = new Bank("CGD", "PT50");
		SavingsAccount sa1 = new SavingsAccount(bank);
		
		sa1.deposit(200);
		
		Bank b1 = FenixFramework.getDomainRoot().getBankSet().iterator().next();
		assertEquals(200, b1.getAccountSet().iterator().next().getBalance());
		
		try {
			sa1.deposit(100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(300, b1.getAccountSet().iterator().next().getBalance());
		
		try {
			sa1.withdraw(300);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(0, b1.getAccountSet().iterator().next().getBalance());
		
		
		
	}

	
	
	
	@After
	@Atomic(mode = TxMode.WRITE)
	public void tearDown() {
		for (Bank bank : FenixFramework.getDomainRoot().getBankSet()) {
			bank.delete();
		}
	}

}
