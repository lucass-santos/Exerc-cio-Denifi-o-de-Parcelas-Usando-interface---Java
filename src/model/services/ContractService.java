package model.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
		
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	
	
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		super();
		this.onlinePaymentService = onlinePaymentService;
	}

	
	
	public OnlinePaymentService getOnlinePaymentService() {
		return onlinePaymentService;
	}

	public void setOnlinePaymentService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer months) {
		
		Date date = contract.getDate();
		Double amount = contract.getTotalValue() / months;
		cal.setTime(date);
		
		for(int i = 1; i<= months; i++ ) {
			cal.add(Calendar.MONTH, 1);
			date = cal.getTime();
			Double result = getOnlinePaymentService().interest(amount, i); 
			result = getOnlinePaymentService().paymentFee(result);
			
			contract.addInstallment(new Installment(date, result));
			
		}	
		}
	}
