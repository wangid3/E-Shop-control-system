package com.wangid3.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Goods {
	private Integer id;
	private String name;
	private Double price;
	private String image;
	private String gdesc;
	private Integer is_hot;
	private Integer cid;
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", gdesc=" + gdesc
				+ ", is_hot=" + is_hot + ", cid=" + cid + "]";
	}


	
}
