/**
 * 
 */
package com.vanstone.el.lang;

import java.math.BigDecimal;

/**
 * @author shipeng
 *
 */
public class MathELFunction {
	
	/**
	 * 计算高度
	 * @param origwidth
	 * @param origheight
	 * @param width
	 * @return
	 */
	public static Integer calheight(Integer origwidth, Integer origheight, Integer width) {
		return new BigDecimal(width).divide(new BigDecimal(origwidth),10, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(origheight)).setScale(10, BigDecimal.ROUND_HALF_EVEN).intValue();
	}
	
	/**
	 * 计算宽度
	 * @param origwidth
	 * @param origheight
	 * @param height
	 * @return
	 */
	public static Integer calwidth(Integer origwidth, Integer origheight, Integer height) {
		return new BigDecimal(height).divide(new BigDecimal(origheight),10, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(origwidth)).setScale(10, BigDecimal.ROUND_HALF_EVEN).intValue();
	}
	
	public static void main(String[] args) {
		int owidth = 1024;
		int oheight = 630;
		System.out.println(MathELFunction.calheight(owidth, oheight, 200));
	}
}
