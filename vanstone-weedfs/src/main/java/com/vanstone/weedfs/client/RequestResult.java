package com.vanstone.weedfs.client;

public class RequestResult {
    
    private boolean success;
    private int size;
    private String fid;
    private String name;
    
    @Override
    public String toString() {
        return "WriteRequestResult [success=" + success + ", size=" + size + ", fid=" + fid + ", name=" + name + "]";
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public RequestResult () {
    }
    
    public RequestResult (boolean success, String fid) {
        this.success = success;
        this.fid = fid;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getFid() {
        return fid;
    }
    
    public void setFid(String fid) {
        this.fid = fid;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
