package com.spring.basics.learning.bean;

public class Book {
	private int id;
	private String author;
	private String title;
	public int getId() {
		return id;
	}
	public String getAuthor() {
		return author;
	}
	public String getTitle() {
		return title;
	}
	public Book(int id, String author, String title) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
	}
}
