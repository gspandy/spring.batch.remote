
/**spring batch 读取jms信息,
 * 由于读取jms有个过期机制，即多少时间读不到消息，可认为
 * 该reader结束，从而这个job  结束，
 * 通过增加jobExecutionListener 调用启动新的job的方式
 * 来保活。 
 * @author sunff
 *
 */
package c.z.spring.batch.remote.jms;