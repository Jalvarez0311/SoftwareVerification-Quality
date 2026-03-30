// Introduction to Software Testing, edition 2
// Authors: Paul Ammann & Jeff Offutt
// Chapter 3
// JUnit for Thermostat.java
// These tests satisfy CACC on the main predicate in method turnHeaterOn()

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kesina Baral & Jeff Offutt
 *
 */

public class ThermostatTest
{
   private Thermostat thermo;
   private ProgrammedSettings settings;

   @BeforeEach // TODO  Set up - Called before every test method.
   public void setUp() {
  
   }

   // if (((curTemp < dTemp - thresholdDiff) || (Override && curTemp < overTemp - thresholdDiff)) && (timeSinceLastRun > minLag))
   // Predicate: (a || (b && c)) && d

   @Test
   public void testTTTT() // d is active clause
   {
      settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
      thermo.setPeriod(Period.MORNING);
      thermo.setDay(DayType.WEEKDAY);
      // Clause a: curTemp < dTemp - thresholdDiff : true
      thermo.setCurrentTemp(63);
	  //set thresholdDiff
	  thermo.setThresholdDiff (5); 
      // Clause b: Override : true
      thermo.setOverride(true);
      // Clause c: curTemp < overTemp - thresholdDiff : true
      thermo.setOverTemp(72);
      // Clause d: timeSinceLastRun.greaterThan (minLag) : true
      thermo.setTimeSinceLastRun(12);
      thermo.setMinLag(10);
      assertTrue(thermo.turnHeaterOn(settings));
   }

	//TODO d is active clause

   @Test
   public void testTTTF() { 
   }

   @Test
   public void testTTFT() { // a is active clause
      settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
      thermo.setPeriod(Period.MORNING);
      thermo.setDay(DayType.WEEKDAY);
      // Clause a: curTemp < dTemp - thresholdDiff : true
      thermo.setCurrentTemp(63);
      thermo.setThresholdDiff(5);
      // Clause b: Override : true
      thermo.setOverride(true);
      // Clause c: curTemp < overTemp - thresholdDiff : false
      thermo.setOverTemp(67);
      // Clause d: timeSinceLastRun.greaterThan (minLag) : true
      thermo.setMinLag(10);
      thermo.setTimeSinceLastRun(12);
      assertTrue(thermo.turnHeaterOn(settings));
   }

   @Test
   public void testFTFT() { // a is active clause, c is active clause
      settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
      thermo.setPeriod(Period.MORNING);
      thermo.setDay(DayType.WEEKDAY);
      // Clause a: curTemp < dTemp - thresholdDiff : false
      thermo.setCurrentTemp(66);
      thermo.setThresholdDiff(5);
      // Clause b: Override : true
      thermo.setOverride(true);
      // Clause c: curTemp < overTemp - thresholdDiff : false
      thermo.setOverTemp(67);
      // Clause d: timeSinceLastRun.greaterThan (minLag) : true
      thermo.setMinLag(10);
      thermo.setTimeSinceLastRun(12);
      assertFalse(thermo.turnHeaterOn(settings));
   }

   @Test
   public void testFTTT() { // b is active clause, c is active clause
      settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
      thermo.setPeriod(Period.MORNING);
      thermo.setDay(DayType.WEEKDAY);
      // Clause a: curTemp < dTemp - thresholdDiff : false
      thermo.setCurrentTemp(66);
      thermo.setThresholdDiff(5);
      // Clause b: Override : true
      thermo.setOverride(true);
      // Clause c: curTemp < overTemp - thresholdDiff : true
      thermo.setOverTemp(72);
      // Clause d: timeSinceLastRun.greaterThan (minLag) : true
      thermo.setMinLag(10);
      thermo.setTimeSinceLastRun(12);
      assertTrue(thermo.turnHeaterOn(settings));
   }

   @Test
   public void testFFTT() { // b is active clause
      settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
      thermo.setPeriod(Period.MORNING);
      thermo.setDay(DayType.WEEKDAY);
      // Clause a: curTemp < dTemp - thresholdDiff : false
      thermo.setCurrentTemp(66);
      thermo.setThresholdDiff(5);
      // Clause b: Override : false
      thermo.setOverride(false);
      // Clause c: curTemp < overTemp - thresholdDiff : true
      thermo.setOverTemp(72);
      // Clause d: timeSinceLastRun.greaterThan (minLag) : true
      thermo.setMinLag(10);
      thermo.setTimeSinceLastRun(12);
      assertFalse(thermo.turnHeaterOn(settings));
   }

   @Test //TODO this is an extra test (not part of CACC coverage) 
   public void testFFFF() {

   }
}
