package br.com.jessa.mapper.test.thread;

import java.util.Random;

public class PersonObj {
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public PersonObj generate() {
		PersonObj o = new PersonObj();
		o.setAge(new Random().nextInt());
		return o;
	}
	

}
