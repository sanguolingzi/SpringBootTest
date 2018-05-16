package com.yinhetianze.security.custom.userdetails;

public interface HttpUserDetails extends UserDetails
{
    String getIpAddress();
}
