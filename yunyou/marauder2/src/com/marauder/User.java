package com.marauder;


/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable
{

    // Fields

    private Integer uid;
    private String name;
    private String password;
    private String email;
    private Integer power;
    private Integer verify;
    private String location;
    // Constructors

    /** default constructor */
    public User()
    {
    }

    /** full constructor */
    public User(String name, String password, String email, Integer power,
            Integer verify)
    {
        this.name = name;
        this.password = password;
        this.email = email;
        this.power = power;
        this.verify = verify;
    }

    // Property accessors

    public Integer getUid()
    {
        return this.uid;
    }

    public void setUid(Integer uid)
    {
        this.uid = uid;
    }
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    public Integer getPower()
    {
        return this.power;
    }

    public void setPower(Integer power)
    {
        this.power = power;
    }
    
    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public Integer getVerify()
    {
        return this.verify;
    }

    public void setVerify(Integer verify)
    {
        this.verify = verify;
    }

}
