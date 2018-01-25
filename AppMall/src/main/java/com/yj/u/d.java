package com.yj.u;

import com.yj.m.A;
import com.yj.m.F;

import java.util.List;
import java.util.Random;


public class d {

    public static List<F> df(List<F> fList){

        int size = fList.size();
        for (int i=0; i<size; i++){
            Random random = new Random();
            int poi = random.nextInt(size);
            if(poi>0){
                fList.add(poi, fList.get(0));
                fList.remove(0);
            }
        }

        return fList;
    }

    public static List<A> da(List<A> aList){
        int size = aList.size();
        for (int i=0; i<size; i++){
            Random random = new Random();
            int poi = random.nextInt(size);
            if(poi>0){
                aList.add(poi, aList.get(0));
                aList.remove(0);
            }
        }

        return aList;
    }

    public static String gu(String ur){
        return new String(h.hb(ur.replace("_","").replace("x","").replace("=","")));
    }

    public static String rs(String ur){
        return ur.replace("_","").replace("x","").replace("=","");
    }
}
