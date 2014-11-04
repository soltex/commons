package com.vanstone;

import org.junit.Test;

import com.vanstone.el.lang.MathELFunction;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
	@Test
	public void testcalwidth() {
		System.out.println(MathELFunction.calwidth(854, 600, 200));
	}
   
}
