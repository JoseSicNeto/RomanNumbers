import java.util.InputMismatchException;
import java.util.Scanner;
																//Decide What Operation to Do.
class NumberType{
	Scanner scan = new Scanner(System.in);
	public void TypeInt() {
		int option;
		while(true) {
			try {
				System.out.print("What will you do?\n1 - Integer to Roman\n2 - Roman to Integer\nEnter: ");
				option = scan.nextInt();
				break;
			}catch(InputMismatchException e) {
				System.out.println("\nError: Enter an Integer Value\n");
				scan.next();
			}
		}
		ChangeOption move = new ChangeOption();					
		move.OptionConversor(option);
	}
}

																//It Checks to See What Operation It Will Do.
class ChangeOption{
	RomanValidator validation = new RomanValidator();
	ConvertorToInteger ValueInteger = new ConvertorToInteger();						//Get Methods from Other Classes.
	ConvertorToRoman ValueRoman = new ConvertorToRoman();							
	Scanner scan = new Scanner(System.in);
	
	public void OptionConversor(int option) {
		switch (option) {
		case 1:
			int integer;
			while(true) {										//Checks if the Typing is Correct and Performs the Operations.
				try {
					System.out.print("Enter your Integer Value: ");
					integer = scan.nextInt();
					break;
				}catch(InputMismatchException e) {
					System.out.println("This Value is not an Integer Value");
					scan.nextInt();
				}
			}
			String StringValue = ValueRoman.IntToRoman(integer);
			System.out.println("Roman Value: " + StringValue);
			break;
		case 2:
			System.out.print("Enter your Roman Value: ");
			String roman = scan.next();
			if (validation.isValidRoman(roman)) {
				int intValue = ValueInteger.RomanToInt(roman);	//Checks if the Typing is Correct and Performs the Operations.
		        System.out.println("Integer value: " + intValue);
			}else {
				System.out.println("Invalid Roman Number");
			}
			break;
		default:
			System.out.println("Okay, No Valid Option Entered Please Try Again.");
			break;
		}
		scan.close();	
	}
}
											//Checks if the Written Words are Within the Rules of Roman Numbers.

class RomanValidator {
    public boolean isValidRoman(String roman) {
        String validChars = "IVXLCDM";

        for (char c : roman.toCharArray()) {
            if (!validChars.contains(String.valueOf(c))) {
                return false;
            }
        }
        														//What Symbols Are Allowed.
        for (int i = 0; i < roman.length() - 1; i++) {
            int currentValue = getRomanValue(roman.charAt(i));
            int nextValue = getRomanValue(roman.charAt(i + 1));

            if (nextValue > currentValue) {
                if (!isValidSubtraction(currentValue, nextValue)) {
                    return false;
                }
                i++; 
            } else if (nextValue == currentValue) {
                if (!isValidRepetition(roman.substring(i))) {
                    return false;
                }
            }
        }

        return true;
    }
    															//One the Rules Preventing Subtraction.
    private boolean isValidSubtraction(int currentValue, int nextValue) {
        if (nextValue / currentValue == 5 || nextValue / currentValue == 10) {

        	if ((currentValue == 1 && (nextValue == 5 || nextValue == 10)) ||
                (currentValue == 10 && (nextValue == 50 || nextValue == 100)) ||
                (currentValue == 100 && (nextValue == 500 || nextValue == 1000))) {
                return true;
            }
        }
        return false;
    }
    															//One of the Rules Preventing Repetition.
    private boolean isValidRepetition(String remainingRoman) {	
        return !remainingRoman.matches(".*(IIII|XXXX|CCCC|VV|LL|DD).*");
    }

    private int getRomanValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
																//Convert Integer to Roman.

class ConvertorToRoman{
	public String IntToRoman(int integer) {
		String[] romanos = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int[] valores = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        
		StringBuilder roman = new StringBuilder();				//Make a "Array" to Place the Chars Inside Together.
		for (int i = 0; i < valores.length; i++) {
			while (integer >= valores[i]) {
				roman.append(romanos[i]);
				integer -= valores[i];
            	}
        	}
        return roman.toString();
	}
}
																//Convert Roman to Integer.

class ConvertorToInteger{
	public int RomanToInt(String roman) {
		String[] romanos = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int[] valores = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		int integer = 0;
	    int i = 0;
	    
	    while (i < roman.length()) {
	        for (int j = 0; j < romanos.length; j++) {
	            if (i + romanos[j].length() <= roman.length() && roman.substring(i, i + romanos[j].length()).equals(romanos[j])) {
	                integer += valores[j];
	                i += romanos[j].length();
	                break;
	            }
	        }
	    }
	return integer;
	}
}
																//Initialize the Program.
public class RomanNumbers {
	public static void main(String[] args) {
		NumberType start = new NumberType();
		start.TypeInt();
	}
}
