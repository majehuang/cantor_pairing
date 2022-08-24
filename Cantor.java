import java.util.Arrays;
import java.math.*;
public class Main {
	public static void main(String[] args) {
		        /*Two individual numbers used to produce a single unquiqe 
         number that no other pair of individual numbers can produce*/
        BigDecimal num1 = new BigDecimal("99999999900");
        BigDecimal num2 = new BigDecimal("99999999");

        /*Converting and storing the result as a hex value as pairing in base10 
         can result in very large numbers.  This is mainly for convience. As it's 
         easier to compare shorter values when testing. (hex also looks cooler)*/
         BigDecimal v=pair(num1, num2);
        String result =  v.toString();
        System.out.println(result);
        BigDecimal[] dresult=depair(v);
        System.out.println(dresult[0].toString());
        System.out.println(dresult[1].toString());

        //Outputing the result
        
	}
	public static BigDecimal pair(BigDecimal a, BigDecimal b) {

        BigDecimal l1 = new BigDecimal("-1");
        //Cantors pairing function only works for positive integers
        if (a.compareTo(l1)>0 || b.compareTo(l1)>0) {
            //Creating an array of the two inputs for comparison later
            BigDecimal[] input = {a, b};

            //Using Cantors paring function to generate unique number
            //0.5 * (a + b) * (a + b + 1) + b;
            BigDecimal result = a.add(b).divide(new BigDecimal("2")).multiply(b.add(a).add(new BigDecimal("1"))).add(b);
            /*Calling depair function of the result which allows us to compare
             the results of the depair function with the two inputs of the pair
             function*/
            return result;
        } else {
            return new BigDecimal("-1"); //Otherwise return rouge value
        }
    }

    public static BigDecimal[] depair(BigDecimal z) {
        /*Depair function is the reverse of the pairing function. It takes a
         single input and returns the two corespoding values. This allows
         us to perform a check. As well as getting the orignal values*/
            MathContext mc 
            = new MathContext(10);
        //Cantors depairing function:
        //long t = (int) (Math.floor((Math.sqrt(8 * z + 1) - 1) / 2));
        BigDecimal t=z.multiply(new BigDecimal("8")).add(new BigDecimal("1"))
        .sqrt(mc).subtract(new BigDecimal("1")).divide(new BigDecimal("2"),0, BigDecimal.ROUND_DOWN);
        //long x = t * (t + 3) / 2 - z;
        BigDecimal x=t.multiply(t.add(new BigDecimal("3"))).divide(new BigDecimal("2")).subtract(z);
        //long y = z - t * (t + 1) / 2;
        BigDecimal y=z.subtract(t.multiply(t.add(new BigDecimal("1"))).divide(new BigDecimal("2")));
        return new BigDecimal[]{x, y}; //Returning an array containing the two numbers
    }

}
