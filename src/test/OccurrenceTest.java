/*
 * OccurrenceTest.java                                      TODO DATE
 */
package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import huffman.Occurrence;

public class OccurrenceTest {

    @Test
    public void testGetCaractere() {
	Occurrence occurrence = new Occurrence('a', 5, 0.25);
	assertEquals('a', occurrence.getCaractere());
    }

    @Test
    public void testGetOccurrences() {
	Occurrence occurrence = new Occurrence('a', 5, 0.25);
	assertEquals(5, occurrence.getOccurrences());
    }

    @Test
    public void testGetFrequence() {
	Occurrence occurrence = new Occurrence('a', 5, 0.25);
	assertEquals(0.25, occurrence.getFrequence(), 0.0001);
    }
}
