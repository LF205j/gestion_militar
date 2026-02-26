package com.defensa.gestion_militar;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarPass
{
    public static void main(String[] args) {

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(encoder.encode("SUBOFICIAL"));
        //$2a$10$HX9EP3uCNFtGsdz5OWi6BexeZf6Mw7RPXdya41pwOsn5iNQeJE/XO

    }
}
