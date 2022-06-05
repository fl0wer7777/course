package refactoring;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class RefactoringTest extends TestCase {

    @Test
    public void test() {
        Movie movie1 = new Movie("新片", Movie.NEW_RELEASE);
        Movie movie2 = new Movie("儿童片", Movie.CHILDRENS);
        Movie movie3 = new Movie("普通片", Movie.REGULAR);

        Customer customer1 = new Customer("A");
        Customer customer2 = new Customer("B");
        Customer customer3 = new Customer("C");

        customer1.addRental(new Rental(movie1, 4));
        customer1.addRental(new Rental(movie3, 2));
        customer2.addRental(new Rental(movie2, 5));

        String stateForCustomer1 =
                "Rental Record for A\n" +
                        "\t新片\t12.0\n" +
                        "\t普通片\t2.0\n" +
                        "Amount owed is 14.0\n" +
                        "You earned 3 frequent renter points";

        String stateForCustomer2 =
                "Rental Record for B\n" +
                        "\t儿童片\t4.5\n" +
                        "Amount owed is 4.5\n" +
                        "You earned 1 frequent renter points";

        String stateForCustomer3 =
                "Rental Record for C\n" +
                        "Amount owed is 0.0\n" +
                        "You earned 0 frequent renter points";

        assertEquals(customer1.statement(), stateForCustomer1);
        assertEquals(customer2.statement(), stateForCustomer2);
        assertEquals(customer3.statement(), stateForCustomer3);
        System.out.println("Test has passed in " + Movie.class.getPackage());
    }
}
