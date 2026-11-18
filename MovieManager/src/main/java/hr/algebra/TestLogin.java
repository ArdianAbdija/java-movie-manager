/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra;

import hr.algebra.auth.AuthService;

/**
 *
 * @author Ardian
 */
public class TestLogin {

    public static void main(String[] args) throws Exception {
        AuthService auth = new AuthService();

        System.out.println(
            auth.login("admin", "admin123".toCharArray())
        );
    }
}

