package com.example.pivovarmath;


import android.util.Log;


public class berechnungen extends MainActivity{

    public static double[] getY(double a, double b, double c){
        double[] Y_Werte = new double[2];
        if(a != 1.0){
            b /= a;
            c /= a;
        }
        final double sqrt = Math.sqrt((b / 2) * (b / 2) - c);
        Y_Werte[0] = -b/2 + sqrt;
        Y_Werte[1] = -b/2 - sqrt;
        if(Double.isNaN(Y_Werte[0]) || Double.isNaN(Y_Werte[1])){
           Log.i("TAG","Keine Nullstellen");
            return null;
        }else{
            return Y_Werte;
        }

    }
}
