package dataprovider;

import java.util.HashMap;

import org.testng.annotations.DataProvider;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

public class SignUpDataProvider {
	HashMap<Integer,String> monthMap = new HashMap<Integer,String>();
	String[] sex = {"female","male"};
	
	public SignUpDataProvider(){
		monthMap.put(1,"Jan");
		monthMap.put(2,"Feb");
		monthMap.put(3,"Mar");
		monthMap.put(4,"Apr");
		monthMap.put(5,"May");
		monthMap.put(6,"Jun");
		monthMap.put(7,"Jul");
		monthMap.put(8,"Aug");
		monthMap.put(9,"Sep");
		monthMap.put(10,"Oct");
		monthMap.put(11,"Nov");
		monthMap.put(12,"Dec"); 
	}

	@DataProvider(name="SignUpTestData")
	public Object[][] getSignUpTestData(){
	    return new Object[][] 
	        	{
	    			getPersonData(),
	    			getPersonData()
	    	};
	}
	
	public Object[] getPersonData()
	{
		Fairy fairy = Fairy.create();
		Person person = fairy.person();
		
		return new Object[]{
				person.getFirstName(), 
				person.getLastName(), 
				person.getEmail(), 
				person.getPassword(),
				this.monthMap.get(person.getDateOfBirth().getMonthOfYear()),
				new Integer(person.getDateOfBirth().getDayOfMonth()).toString(),
				new Integer(person.getDateOfBirth().getYear()).toString(),
				person.getSex().toString().toLowerCase()};
	}

	public static void main(String[] args){
		for(int i=0; i< 10;i++)
		{
			Fairy fairy = Fairy.create();
			Person person = fairy.person();
			System.out.print(person.getFirstName());
			System.out.print("\t");
			System.out.print(person.getLastName());
			System.out.print("\t");
			System.out.print(person.getEmail());
			System.out.print("\t");
			System.out.print(person.getPassword());
			System.out.print("\t");
			System.out.print(person.getDateOfBirth().getMonthOfYear());
			System.out.print("\t");
			System.out.print(new Integer(person.getDateOfBirth().getDayOfMonth()).toString());
			System.out.print("\t");
			System.out.print(new Integer(person.getDateOfBirth().getYear()).toString());
			System.out.print("\t");
			System.out.print(person.getSex().toString().toLowerCase());
			System.out.println();
		}
	}
}
