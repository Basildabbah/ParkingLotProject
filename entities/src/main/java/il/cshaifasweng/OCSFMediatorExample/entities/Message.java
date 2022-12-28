package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalTime;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8224097662914849956L;
	
	private String message;
	private LocalTime time;
	private Object list;

	public void setList(Object list) {
		this.list = list;
	}

	public Object getList() {
		return list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Message(String message) {
		this.message = message;
		this.time = LocalTime.now();
	}

	public LocalTime getTime() {
		return time;
	}
}