package edu.kau.fcit.cpit252;

import static org.junit.Assert.*;
import org.junit.Test;

import java.lang.reflect.Constructor;


public class Lab3Test
{
    // Asset Car implements cloneable
    @Test
    public void assertCarIsCloneable(){
       assertEquals(Car.class.getInterfaces()[0].getName(), java.lang.Cloneable.class.getName());
    }

    // Asset Car overrides the clone method
    @Test
    public void assertCarHasCloneMethod() throws NoSuchMethodException, SecurityException{
        assertNotNull(Car.class.getMethod("clone"));
    }

    // Assert a clone is not identical to the original instance
    @Test
    public void assertClonesNotEqual(){
        Car c1 = new Car("Honda", "Accord", 2019);
        // Make a clone
        Car c2 = c1.clone();
        assertNotEquals(c1, c2);
    }

    // The Car class should have a constructor with Car type
    @Test
    public void assertImplementsCloneable(){
        boolean hasConstructorWithCarParameter = false;
        for (Constructor c : Car.class.getConstructors()) {
            if(c.getParameterTypes().length == 1){
                if (c.getParameterTypes()[0] == Car.class){
                    hasConstructorWithCarParameter = true;
                }
            }
        }
        assertTrue(hasConstructorWithCarParameter);
    }

    // Should fetch recalls
    @Test
    public void shouldFetchFromAPI(){
        Car c1 = new Car("Honda", "Accord", 2019);
        assertNotNull(c1.getRecalls());
        assertTrue(c1.getRecalls().size() > 0);
    }

}
