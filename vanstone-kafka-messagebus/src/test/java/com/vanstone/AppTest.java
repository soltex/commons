package com.vanstone;

import org.junit.Test;

import com.vanstone.kafka.messagebus.conf.KafkaConf;

public class AppTest {
	
	@Test
	public void testTpoic() {
		KafkaConf kafkaConf = KafkaConf.getKafkaConf();
		System.out.println(kafkaConf);
	}
}
