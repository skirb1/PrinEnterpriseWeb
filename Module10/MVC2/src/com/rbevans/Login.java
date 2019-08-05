package com.rbevans;

public class Login {
	  private String name = null;
	    private String password = null;
	    private boolean success = false;
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	        if (password.equals("foo")) {
	           success = true;            
	        }
	    }
	    
	    public boolean getSuccess() {
	        return success;
	    }
}
