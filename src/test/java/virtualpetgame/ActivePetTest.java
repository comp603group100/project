/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import virtualpetgame.pets.*;

public class ActivePetTest {
    
    ActivePet activePet;
    
    public ActivePetTest() {
    }
    
    @Before
    public void setUp() {
        this.activePet = new ActivePet(new Cat());
    }

    /**
     * Test of getPetType method, of class ActivePet.
     */
    @Test
    public void testGetPetType() {
        System.out.println("getPetType");
        ActivePet instance = this.activePet;
        String expResult = "Cat";
        String result = instance.getPetType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHunger method, of class ActivePet.
     */
    @Test
    public void testGetHunger() {
        System.out.println("getHunger");
        ActivePet instance = this.activePet;
        int expResult = 0;
        int result = instance.getHunger();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBoredom method, of class ActivePet.
     */
    @Test
    public void testGetBoredom() {
        System.out.println("getBoredom");
        ActivePet instance = this.activePet;
        int expResult = 0;
        int result = instance.getBoredom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCleanliness method, of class ActivePet.
     */
    @Test
    public void testGetCleanliness() {
        System.out.println("getCleanliness");
        ActivePet instance = this.activePet;
        int expResult = 100;
        int result = instance.getCleanliness();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMoney method, of class ActivePet.
     */
    @Test
    public void testGetMoney() {
        System.out.println("getMoney");
        ActivePet instance = this.activePet;
        int expResult = 15;
        int result = instance.getMoney();
        assertEquals(expResult, result);
    }

    /**
     * Test of increaseMoney method, of class ActivePet.
     */
    @Test
    public void testIncreaseMoney() {
        System.out.println("increaseMoney");
        ActivePet instance = this.activePet;
        instance.increaseMoney();
        assertEquals(instance.getMoney(), 
                new ActivePet(new Cat()).getMoney() + 3);
        
        instance.spend(3);
        instance.increaseBoredom(20); //20 x 5 = 100 percent boredom, cat will be in neutral state
        instance.updateState();
        instance.increaseMoney();
        
        assertEquals(instance.getMoney(), 
                new ActivePet(new Cat()).getMoney() + 2);
    }

    /**
     * Test of increaseHunger method, of class ActivePet.
     */
    @Test
    public void testIncreaseHunger() {
        System.out.println("increaseHunger");
        int factor = 5; //cat has hunger factor of 2
        ActivePet instance = this.activePet;
        instance.increaseHunger(factor);
        assertEquals(instance.getHunger(), 
                new ActivePet(new Cat()).getHunger() + 10);
    }

    /**
     * Test of increaseBoredom method, of class ActivePet.
     */
    @Test
    public void testIncreaseBoredom() {
        System.out.println("increaseBoredom");
        int factor = 2; //boredom factor 5
        ActivePet instance = this.activePet;
        instance.increaseBoredom(factor);
        assertEquals(instance.getBoredom(),
                new ActivePet(new Cat()).getHunger() + 10);
    }

    /**
     * Test of decreaseCleanliness method, of class ActivePet.
     */
    @Test
    public void testDecreaseCleanliness() {
        System.out.println("decreaseCleanliness");
        int factor = 10; //clean factor 1
        ActivePet instance = this.activePet;
        instance.decreaseCleanliness(factor);
        assertEquals(instance.getCleanliness(),
                new ActivePet(new Cat()).getCleanliness() - 10);
    }

    /**
     * Test of spend method, of class ActivePet.
     */
    @Test
    public void testSpend() {
        System.out.println("spend");
        int n = 0;
        ActivePet instance = this.activePet;
        System.out.println("Spending 15");
        instance.spend(15);
        assertEquals(instance.getMoney(), 0);
        
        System.out.println("Spending 20 (below zero test)");
        instance.spend(20);
        assertEquals(instance.getMoney(), 0);

        System.out.println("Increasing to 99 money");
        for (int i = 0; i < 33; i++)
            instance.increaseMoney();
        assertEquals(instance.getMoney(), 99);
        
        System.out.println("testing 100 spend (should remain at 99)");
        instance.spend(100);
        assertEquals(instance.getMoney(), 99);
    }

    /**
     * Test of feed method, of class ActivePet.
     */
    @Test
    public void testFeed() {
        System.out.println("feed");
        ActivePet instance = this.activePet;
        instance.increaseHunger(5);
        instance.feed();
        
        assertEquals(instance.getHunger(), 5);
    }

    /**
     * Test of play method, of class ActivePet.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        ActivePet instance = this.activePet;
        instance.increaseBoredom(2);
        instance.play();
        
        assertEquals(instance.getBoredom(), 5);
    }

    /**
     * Test of clean method, of class ActivePet.
     */
    @Test
    public void testClean() {
        System.out.println("clean");
        ActivePet instance = this.activePet;
        instance.decreaseCleanliness(10);
        instance.clean();

        assertEquals(instance.getCleanliness(), 95);
    }
}
