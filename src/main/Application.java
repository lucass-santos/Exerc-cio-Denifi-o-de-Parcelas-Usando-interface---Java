package main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import org.omg.CORBA.LocalObject;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter contract data:");
		System.out.print("Number: ");
		Integer number = sc.nextInt();
		sc.nextLine();
		System.out.print("Date (dd/MM/yyyy): ");
		
		Date date = null;
		try {
		date = sdf.parse(sc.nextLine());
		}
		catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		System.out.print("Contract Value: ");
		Double totalValue = sc.nextDouble();
		sc.nextLine();
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.print("Enter number of installments: ");
		Integer quantity = sc.nextInt();
		
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, quantity);
		
		for(Installment installment: contract.getInstallments()) {
			System.out.println(installment);
		}
		
		sc.close();
		
	}

}
