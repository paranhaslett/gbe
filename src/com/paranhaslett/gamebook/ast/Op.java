package com.paranhaslett.gamebook.ast;

class Op implements Arithmetic {
	Value value;
	String operation;
	Arithmetic next;
}
