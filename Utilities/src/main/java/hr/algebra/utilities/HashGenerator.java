/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.utilities;

/**
 *
 * @author Ardian
 */
public class HashGenerator {
   
    public static void main(String[] args) {
        String hash = PasswordHasher.hash("admin123".toCharArray());
        System.out.println(hash);
    
}
}