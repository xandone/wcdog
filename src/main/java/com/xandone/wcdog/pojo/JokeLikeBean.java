package com.xandone.wcdog.pojo;

public class JokeLikeBean {

	private String joke_id;
	private String joke_user_id;
	
	public JokeLikeBean() {}
	
	public JokeLikeBean(String joke_id,String joke_user_id) {
		this.joke_id=joke_id;
		this.joke_user_id=joke_user_id;
	}

	public String getJoke_id() {
		return joke_id;
	}

	public void setJoke_id(String joke_id) {
		this.joke_id = joke_id;
	}

	public String getJoke_user_id() {
		return joke_user_id;
	}

	public void setJoke_user_id(String joke_user_id) {
		this.joke_user_id = joke_user_id;
	}

}
