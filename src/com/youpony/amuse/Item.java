package com.youpony.amuse;

import java.io.Serializable;

public class Item implements Serializable{
	
	String name, author, year, description, mostra ;
	int e_id;
	Item(){
	}
	
	@Override
	public String toString(){
		return name;
	}
	

}
