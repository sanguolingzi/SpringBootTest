package com.yinhetianze.security.custom.userdetails;

public interface UserDetailsService
{
    UserDetails getUserDetails(String userId);

    void saveUserDetails(UserDetails userDetails);

    void deleteUserDetails(String userId);

    void updateUserDetails(UserDetails userDetails);
}
