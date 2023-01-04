package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalTime;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8224097662914849956L;
	
	private String message;
	private Object object1;
	private Object object2;
	private Object object3;
	private Object object4;
	private Object object5;
	private Object object6;
	private Object object7;
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

	public Message(String msg, Object obj1) {
		this.message = msg;
		this.object1 = obj1;
		this.time = LocalTime.now();
	}

	public Message(String msg, Object obj1,Object obj2) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
	}

	public Message(String msg, Object obj1,Object obj2, Object obj3) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
	}

	public Message(String msg, Object obj1,Object obj2, Object obj3, Object obj4) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
	}
	public Message(String msg, Object obj1,Object obj2, Object obj3, Object obj4,Object obj5) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
	}



	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
	}

	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6 ,Object obj7) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
		this.object7 = obj7;
	}

	public LocalTime getTime() {
		return time;
	}

	public Object getObject1() {
		return object1;
	}

	public Object getObject2() {
		return object2;
	}

	public void setObject2(Object object2) {
		this.object2 = object2;
	}

	public Object getObject3() {
		return object3;
	}

	public void setObject3(Object object3) {
		this.object3 = object3;
	}

	public Object getObject4() {
		return object4;
	}

	public void setObject4(Object object4) {
		this.object4 = object4;
	}

	public Object getObject5() {
		return object5;
	}

	public void setObject5(Object object5) {
		this.object5 = object5;
	}

	public Object getObject6() {
		return object6;
	}

	public void setObject6(Object object6) {
		this.object6 = object6;
	}

	public Object getObject7() {
		return object7;
	}

	public void setObject7(Object object7) {
		this.object7 = object7;
	}

	public void setObject1(Object object1) {
		this.object1 = object1;
	}
	public String toString() {
		return this.message;
	}
}
