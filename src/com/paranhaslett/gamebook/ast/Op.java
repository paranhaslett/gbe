package com.paranhaslett.gamebook.ast;

public class Op implements Arithmetic {
	Value value;
	String operation;
	Arithmetic next;
}
