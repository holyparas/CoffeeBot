
import java.util.Scanner;
public class CoffeeBot
{

	public static boolean validateArgs(String[] args){
		if(args.length == 0)// No command line args entered
		{
			System.out.println("No arguments. System terminating.");
			System.exit(0);
		}
		else if(args.length == 1)// Only 1 command line args entered
		{
			System.out.println("Not enough arguments. System terminating.");
			System.exit(0);
		}
		else if(args.length > 2)// Too many command line args entered
		{
			System.out.println("Too many arguments. System terminating.");
			System.exit(0);
		}
		return true;
	}

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		validateArgs(args);

		int coffeeCups = Integer.parseInt(args[0]);
		int coffeeShots = Integer.parseInt(args[1]);

		// If coffee cups entered is less than 0
		if(coffeeCups < 0 && coffeeShots > 0)
		{
			System.out.println("Negative supply of coffee cups. System terminating.");
			return;
		}
		// If coffee shots entered is less than 0
		else if(coffeeShots < 0 && coffeeCups > 0)
		{
			System.out.println("Negative supply of coffee shots. System terminating.");
			return;
		}
		// Both coffee cups and shots are less than 0
		else if(coffeeCups < 0 && coffeeShots < 0)
		{
			System.out.println("Negative supply chain. System terminating.");
			return;
		}
		else
		{
			System.out.print("Hello, what's your name? ");
		}
			String name = keyboard.nextLine();
			System.out.print("Would you like to order some coffee, " + name + "? (y/n) " );
			String orderingResponse = keyboard.next();
			while( !(orderingResponse.equalsIgnoreCase("y") || orderingResponse.equalsIgnoreCase("n")))
			{
				System.out.println("Invalid response. Try again.");
			 	orderingResponse = keyboard.next();
			}
			if(orderingResponse.equalsIgnoreCase("n"))
			{
				System.out.println("Come back next time, " + name + ".");
				return;
			}
			// This occurs if they say "y" for ordering coffee
			else
			{
				System.out.println("Great! Let's get started.\n");
				System.out.println("Order selection");
				System.out.println("---------------");
				System.out.println("");
					if(coffeeCups == 1 && !(coffeeShots == 1))
					{
						// Singular coffee cup if coffeeCups == 1
						System.out.println("There is " + coffeeCups + " coffee cup in stock and each costs $2.00.");
						System.out.println("There are " + coffeeShots + " coffee shots in stock and each costs $1.00.");
						System.out.print("How many cups of coffee would you like? ");
					}
					else if(coffeeShots == 1 && !(coffeeCups == 1))
					{
						// Plural coffee cups if coffeeCups > 1
						System.out.println("There are " + coffeeCups + " coffee cups in stock and each costs $2.00.");
						System.out.println("There is " + coffeeShots + " coffee shot in stock and each costs $1.00.");
						System.out.print("How many cups of coffee would you like? ");
					}
					else if(coffeeShots == 1 && coffeeCups == 1)
					{
						// If both coffeeShots and coffeeCups are one then both are made singular.
						System.out.println("There is " + coffeeCups + " coffee cup in stock and each costs $2.00.");
						System.out.println("There is " + coffeeShots + " coffee shot in stock and each costs $1.00.");
						System.out.print("How many cups of coffee would you like? ");
					}
					else// If both Cups and Shots are > 1
					{
						System.out.println("There are " + coffeeCups + " coffee cups in stock and each costs $2.00.");
						System.out.println("There are " + coffeeShots + " coffee shots in stock and each costs $1.00.");
						System.out.println("");
						System.out.print("How many cups of coffee would you like? ");
					}
			}
			//TODO: WHat if user enters non-int?
		int cups = keyboard.nextInt();// Storing number of cups USER wishes to have

		if(cups == 0)
		{
			System.out.println("No cups, no coffee. Goodbye.");
			return;
		}
		else if(cups < 0)
		{
			System.out.println("Does not compute. System terminating.");
			return;
		}
		else if(cups > coffeeCups)
		{
			System.out.println("Not enough stock. Come back later.");
			return;
		}
		// Formed an array for storing number of shots for each cup,currently empty without values.
		int[] numberOfShots = new int[cups];

		/* Blank line for space between "how many cups of coffee would you like"
		& "how many coffee shots in cup ... " */
		System.out.println("");

		int remainingShots = coffeeShots;// Remaining shots is the remaining after the deduction of shots in each cup.
		for(int i = 1; i <= cups; i++)
		{
			System.out.print("How many coffee shots in cup " + i + "? ");
			numberOfShots[i-1] = keyboard.nextInt();
			while(numberOfShots[i-1] < 0)
			{
				System.out.println("Does not compute. Try again.");
				System.out.print("How many coffee shots in cup " + i + "? ");
				numberOfShots[i-1] = keyboard.nextInt();
			}
			while(numberOfShots[i-1] > remainingShots)
			{
				if(remainingShots == 1)
				{
					System.out.println("There is only 1 coffee shot left. Try again");
					System.out.print("How many coffee shots in cup " + i + "? ");
					numberOfShots[i-1] = keyboard.nextInt();
				}
				else
				{
					System.out.println("There are only " + remainingShots + " coffee shots left. Try again.");
					System.out.print("How many coffee shots in cup " + i + "? ");
					numberOfShots[i-1] = keyboard.nextInt();
				}
			}
		remainingShots = remainingShots - numberOfShots[i-1];
		}

		System.out.println("");
		System.out.println("Order summary");
		System.out.println("-------------");
		System.out.println("");

		int totalCost = 0;
		for(int j = 1; j <= cups; j++)
		{
			if(numberOfShots[j-1] == 1)
			{
				System.out.println("Cup " + j + " has " + numberOfShots[j-1] + " shot and will cost $" + (2 + numberOfShots[j-1]) + ".00");
				totalCost = (totalCost + (numberOfShots[j-1] * 1) + 2); // +2 = price of each coffee cup; [j-1] since index starts with 0
																	 //	*1 = multiplying by 1 for the price of each coffee shot
			}
			else
			{
				System.out.println("Cup " + j + " has " + numberOfShots[j-1]
				 								 	+ " shots and will cost $" +
													(2 + numberOfShots[j-1]) + ".00"
													);
				totalCost = (totalCost + numberOfShots[j-1] + 2);
			}
		}
		if(cups == 1)
		{
			System.out.println("");
			System.out.println(cups + " coffee to purchase.");
			System.out.println("Purchase price is $" + totalCost + ".00");
			System.out.print("Proceed to payment? (y/n) ");
		}
		else
		{
			System.out.println("");
			System.out.println(cups + " coffees to purchase.\n");
			System.out.println("Purchase price is $" + totalCost + ".00");
			System.out.print("Proceed to payment? (y/n) ");
		}
		String paymentResponse = keyboard.next();

		if(paymentResponse.equalsIgnoreCase("n"))
		{
			System.out.println("Come back next time, " + name + ".");
			return;
		}
		else
		{
			System.out.println("Order payment");
			System.out.println("-------------");
			System.out.println("");

		}

		String[] money = {"$100.00","$50.00","$20.00","$10.00","$5.00","$2.00","$1.00",
						  "$0.50","$0.20","$0.10","$0.05"};
		double[] moneyDbl = {100.00, 50.00, 20.00, 10.00, 5.00, 2.00, 1.00, 0.50, 0.20, 0.10, 0.05};
		int[] moneyCtr = {0,0,0,0,0,0,0,0,0,0,0};
		double remainingCost = (double)totalCost;
		// System.out.println("remaining cost: " + remainingCost);
		String payment = keyboard.nextLine();

		while(remainingCost > 0)
		{
			System.out.printf("$%.2f remains to be paid. Enter coin or note: "
												, remainingCost);
			payment = keyboard.nextLine();
			boolean validDenom = false;

			for(int k = 0; k < money.length; k++)
			{
				if((payment.equals(money[k])))
				{
					validDenom = true;
					String moneyNumber = "";
					for(int i = 1; i < payment.length(); i++)
					{
						moneyNumber = moneyNumber + payment.charAt(i);
					}
					Double realMoney = Double.parseDouble(moneyNumber);
					// System.out.println("converted customer note/coin: " + realMoney);
					remainingCost = remainingCost - realMoney;
					// System.out.println("")
					break;
				}

			}
			if(validDenom == false)
			{
				System.out.println("Invalid coin or note. Try again.");
			}

		}
		if( remainingCost == 0)
		{
			System.out.println("You gave " + payment);
			System.out.println("Perfect! No change given.\n");
			System.out.println("Thank you, " + name +  ".");
			System.out.println("See you next time.");
		}
		//returning their change
		else if(remainingCost < 0)
		{
			double scale = Math.pow(10, 2);
			// System.out.println("remaining cost < 0");
			double change = Math.abs(remainingCost);
			// System.out.println("change: " + change);
			while(change > 0){
				for(int i = 0; i < moneyDbl.length; i++){
					if(moneyDbl[i] <= change){
						moneyCtr[i]+=1;
						change = change - moneyDbl[i];
						change = Math.ceil(change*scale)/scale;
						// System.out.println("moneyDbl: " + moneyDbl[i] + " " + "moneyCtr: " + moneyCtr[i]);
						break;
					}
				}
			}
			System.out.println("\nYou gave " + payment);
			System.out.println("Your change:");
			for(int l = 0; l < moneyCtr.length; l++){
				if(moneyCtr[l] == 0 ){
					continue;
				}
				System.out.println(moneyCtr[l] + " x " + money[l]);
			}


		}
		System.out.printf("%nThank you, %s.%nSee you next time.", name);

	}
}
