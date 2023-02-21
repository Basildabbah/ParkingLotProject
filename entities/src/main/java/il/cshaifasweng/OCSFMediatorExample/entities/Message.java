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
	private Object object8;
	private Object object9;
	private Object object10;
	private Object object11;
	private Object object12;
	private Object object13;
	private Object object14;
	private Object object15;
	private Object object16;
	private Object object17;
	private Object object18;
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

	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6 ,Object obj7,Object obj8) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
		this.object7 = obj7;
		this.object8 = obj8;
	}

	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6 ,Object obj7,Object obj8,Object obj9) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
		this.object7 = obj7;
		this.object8 = obj8;
		this.object9= obj9;
	}

	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6 ,Object obj7,Object obj8,Object obj9,Object obj10) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
		this.object7 = obj7;
		this.object8 = obj8;
		this.object9= obj9;
		this.object10 = obj10;
	}

	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6 ,Object obj7,Object obj8,Object obj9,Object obj10,Object obj11) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
		this.object7 = obj7;
		this.object8 = obj8;
		this.object9= obj9;
		this.object10 = obj10;
		this.object11 = obj11;
	}

	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6 ,Object obj7,Object obj8,Object obj9,Object obj10,Object obj11,Object obj12) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
		this.object7 = obj7;
		this.object8 = obj8;
		this.object9= obj9;
		this.object10 = obj10;
		this.object11 = obj11;
		this.object12 = obj12;
	}

	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6 ,Object obj7,Object obj8,Object obj9,Object obj10,Object obj11,Object obj12,Object obj13) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
		this.object7 = obj7;
		this.object8 = obj8;
		this.object9= obj9;
		this.object10 = obj10;
		this.object11 = obj11;
		this.object12 = obj12;
		this.object13 = obj13;
	}

	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6 ,Object obj7,Object obj8,Object obj9,Object obj10,Object obj11,Object obj12,Object obj13,Object obj14) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
		this.object7 = obj7;
		this.object8 = obj8;
		this.object9= obj9;
		this.object10 = obj10;
		this.object11 = obj11;
		this.object12 = obj12;
		this.object13 = obj13;
		this.object14 = obj14;
	}

	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6 ,Object obj7,Object obj8,Object obj9,Object obj10,Object obj11,Object obj12,Object obj13,Object obj14,Object obj15) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
		this.object7 = obj7;
		this.object8 = obj8;
		this.object9= obj9;
		this.object10 = obj10;
		this.object11 = obj11;
		this.object12 = obj12;
		this.object13 = obj13;
		this.object14 = obj14;
		this.object15 = obj15;
	}
	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6 ,Object obj7,Object obj8,Object obj9,Object obj10,Object obj11,Object obj12,Object obj13,Object obj14,Object obj15,Object obj16) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
		this.object7 = obj7;
		this.object8 = obj8;
		this.object9= obj9;
		this.object10 = obj10;
		this.object11 = obj11;
		this.object12 = obj12;
		this.object13 = obj13;
		this.object14 = obj14;
		this.object15 = obj15;
		this.object16 = obj16;
	}
	public Message(String msg, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6 ,Object obj7,Object obj8,Object obj9,Object obj10,Object obj11,Object obj12,Object obj13,Object obj14,Object obj15,Object obj16,Object obj17,Object obj18) {
		this.message = msg;
		this.object1 = obj1;
		this.object2 = obj2;
		this.object3 = obj3;
		this.object4 = obj4;
		this.object5=obj5;
		this.object6=obj6;
		this.object7 = obj7;
		this.object8 = obj8;
		this.object9= obj9;
		this.object10 = obj10;
		this.object11 = obj11;
		this.object12 = obj12;
		this.object13 = obj13;
		this.object14 = obj14;
		this.object15 = obj15;
		this.object16 = obj16;
		this.object17 = obj17;
		this.object18 = obj18;
	}
	public LocalTime getTime() {
		return time;
	}

	public Object getObject1() {
		return object1;
	}

	public void setObject1(Object object1) {
		this.object1 = object1;
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

	public Object getObject8() {
		return object8;
	}

	public void setObject8(Object object8) {
		this.object8 = object8;
	}

	public Object getObject9() {
		return object9;
	}

	public void setObject9(Object object9) {
		this.object9 = object9;
	}

	public Object getObject10(){ return object10; }

	public void setObject10(Object object10) {
		this.object10 = object10;
	}

	public Object getObject11(){ return object11; }

	public void setObject11(Object object11) {
		this.object11 = object11;
	}

	public Object getObject12(){ return object12; }

	public void setObject12(Object object12) {
		this.object12 = object12;
	}

	public Object getObject13(){ return object13; }

	public void setObject13(Object object13) {
		this.object13 = object13;
	}

	public Object getObject14(){ return object14; }

	public void setObject14(Object object14) {
		this.object14 = object14;
	}
	public Object getObject17(){ return object17; }
	public Object getObject18(){ return object18; }
	public Object getObject15(){ return object15; }
	public Object getObject16(){ return object16; }
	public void setObject15(Object object15) {
		this.object15 = object15;
	}
	public void setObject17(Object object17) {
		this.object17 = object17;
	}
	public void setObject18(Object object18) {
		this.object18 = object18;
	}
	public String toString() {
		return this.message;
	}
}
