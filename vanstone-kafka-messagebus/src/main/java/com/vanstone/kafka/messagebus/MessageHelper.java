/**
 * 
 */
package com.vanstone.kafka.messagebus;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

/**
 * @author shipeng
 */
public class MessageHelper {
	
	/**
	 * 解析KafkaStream为MessageObject
	 * @param stream
	 * @return
	 */
	public static MessageObject singleMessage(KafkaStream<byte[], byte[]> stream) {
		if (stream == null || stream.isEmpty()) {
			return null;
		}
		ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
		while (iterator.hasNext()) {
			MessageAndMetadata<byte[], byte[]> messageAndMetadata = iterator.next();
			MessageObject object = new MessageObject(messageAndMetadata.key() != null ? new String(messageAndMetadata.key()) : null, messageAndMetadata.message() != null ? new String(messageAndMetadata.message()) : null);
			return object;
		}
		return null;
	}
	
}
