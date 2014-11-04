package com.vanstone.dal.id;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.vanstone.dal.util.Base58;

/**
 * @author shipeng
 */
public class IDBuilder {
	
	/**
	 * 普通uuid，默认36位
	 * @return
	 */
	public static String uuidId() {
		return UUID.randomUUID().toString();
	}
	
    /**
     * 经过base64位处理的22位id
     * @return
     */
    public static String base64Uuid() {
        return base64Uuid(UUID.randomUUID());
    }
    
    /**
     * Base58编码方式处理id,不存在-等字符
     * @return
     */
    public static String base58Uuid() {
        UUID uuid = UUID.randomUUID();
        return base58Uuid(uuid);
    }
    
    private static String base64Uuid(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return Base64.encodeBase64URLSafeString(bb.array());
    }

    protected static String base58Uuid(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return Base58.encode(bb.array());
    }

    public static String encodeBase58Uuid(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        return base58Uuid(uuid);
    }

    public static String decodeBase58Uuid(String base58uuid) {
        byte[] byUuid = Base58.decode(base58uuid);
        ByteBuffer bb = ByteBuffer.wrap(byUuid);
        UUID uuid = new UUID(bb.getLong(), bb.getLong());
        return uuid.toString();
    }
    
    public static void main(String[] args) {
    	for (int i=0;i<20;i++) {
    		System.out.println(base58Uuid());
    	}
    	System.out.println("===>>>");
    	for (int i=0;i<20;i++) {
    		System.out.println(base64Uuid());
    	}
    }
}
