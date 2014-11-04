package com.vanstone.common.component.queue;

/**
 * 优先级接口
 * 若要实现优先级队列，请将要入队的对象实现此接口，
 * 并实现compareTo方法来定制自己的比较策略
 * @author liqiang
 */
public interface Priority extends Comparable<Priority> {

}
