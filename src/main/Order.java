package main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by: Ian Hildebrand
 * Date: 08-May-17.
 */
public class Order
{
	private int orderId;

	private String name, surName, address, zipcode, place, date;

	private ArrayList <Integer> articleId = new ArrayList<>();

	// Getters
	public int getOrderId()
	{
		return orderId;
	}

	public String getName()
	{
		return name;
	}

	public String getSurName()
	{
		return surName;
	}

	public String getAddress()
	{
		return address;
	}

	public String getZipcode()
	{
		return zipcode;
	}

	public String getPlace()
	{
		return place;
	}

	public String getDate()
	{
		return date;
	}

	public ArrayList <Integer> getArticleId()
	{
		return articleId;
	}

	// Setters
	void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}

	void setName(String name)
	{
		this.name = name;
	}

	void setSurName(String surName)
	{
		this.surName = surName;
	}

	void setAddress(String address)
	{
		this.address = address;
	}

	void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	void setPlace(String place)
	{
		this.place = place;
	}

	void setDate(String date)
	{
		this.date = date;
	}

	void setArticleId(ArrayList <Integer> articleId)
	{
		this.articleId = articleId;
	}

	// ToString
	@Override
	public String toString()
	{
		return "Order{" +
				"orderId=" + orderId +
				", name='" + name + '\'' +
				", surName='" + surName + '\'' +
				", address='" + address + '\'' +
				", zipcode='" + zipcode + '\'' +
				", place='" + place + '\'' +
				", date='" + date + '\'' +
				", articleId=" + articleId +
				'}';
	}
}
