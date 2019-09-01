package com.moyan.example.j2se.reflect;

import java.lang.reflect.Field;


public class UserDto {
	
	public static void main(String[] args) throws Exception {
		
		Class clazz = Class.forName("com.xlbai.base.UserDto");
		Object obj = clazz.newInstance();
		System.out.println(obj);
		Field field = clazz.getDeclaredField("id");
		field.setAccessible(true);
		field.set(obj, 21);
		System.out.println(obj);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((work == null) ? 0 : work.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (work == null) {
			if (other.work != null)
				return false;
		} else if (!work.equals(other.work))
			return false;
		return true;
	}

	private int id;
	
	private String name;
	
	private String sex;
	
	private String work;

	
	public UserDto() {
		super();
	}

	
	public UserDto(int id, String name, String sex, String work) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.work = work;
	}


	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		System.out.println("setid...");
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}
}
