/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;

/**
 *
 * @author acer
 */
public class OrderUtil {

    public static String getStatusText(int status) {
        switch (status) {
            case 1:
                return "Pending";
            case 2:
                return "Processing";
            case 3:
                return "Complete";
            case 4:
                return "Cancel";
            default:
                return "Unknown";
        }
    }

    public static String getTypeOfFoodText(int typeOfFood) {
        switch (typeOfFood) {
            case 1:
                return "Món Mặn";
            case 2:
                return "Món Chay";
            case 3:
                return "Món Nước";
            default:
                return "Khác";
        }
    }
}
