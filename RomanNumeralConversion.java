/*
 * Converts numbers to their Roman numeral version
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeralConversion {
	public static void main(String[] args) {
		RomanNumeralConversion rnc = new RomanNumeralConversion(2012);
		System.out.println("Number: " + rnc.getNum() + "\nRoman Numeral: " + rnc.getRomanNum() + "\n\n");
	}

	int num;
	String romanNum = "";
	Map<Integer, String> numeralMap = new LinkedHashMap<Integer, String>();

	//constructor takes an int to convert
	public RomanNumeralConversion(int number) {
		initialize();
		setNum(number);
	}

	//get number
	public int getNum() {
		return this.num;
	}

	//get roman numeral
	public String getRomanNum() {
			return this.romanNum;
	}

	//set number and change roman numeral to match
	public void setNum(int number) {
		this.num = number;
		convert();
	}


	//convert number to roman numeral
	public void convert() {
		this.romanNum = "";
		int tempNum = this.num;
		while (tempNum > 0) {
			for (Integer i : numeralMap.keySet()) {
				if(tempNum >= i) {
					tempNum -= i;
					this.romanNum += numeralMap.get(i);
					break;
				}
			}
		}

	}

	//initialize roman numeral map with values
	public void initialize() {
		this.numeralMap.put(1000, "M");
		this.numeralMap.put(500, "D");
		this.numeralMap.put(400, "CD");
		this.numeralMap.put(100, "C");
		this.numeralMap.put(90, "XC");
		this.numeralMap.put(50, "L");
		this.numeralMap.put(40, "XL");
		this.numeralMap.put(10, "X");
		this.numeralMap.put(9, "IX");
		this.numeralMap.put(5, "V");
		this.numeralMap.put(4, "IV");
		this.numeralMap.put(1, "I");
	}
}