package com.example.a7practica;

import java.util.Arrays;

public class listado {
    String[] item;
    String[] item0;
    String[] item1;
    String[] item2;
    String[] item3;

    public listado() {
        item = new String[4];

        item0 = new String[1];
        item1 = new String[4];
        item2 = new String[4];
        item3 = new String[4];

        item[0] = "";
        item[1] = "Puebla";
        item[2] = "Queretaro";
        item[3] = "Quintana Roo";

        item0[0]="";

        item1[0] = "santa fe";
        item1[1] = "los cuartos";
        item1[2] = "grande";
        item1[3] = "los cedros";

        item2[0] = "pie verde";
        item2[1] = "el porvenir";
        item2[2] = "pozo del coco";
        item2[3] = "san salvador del bajio";

        item3[0] = "la bufa";
        item3[1] = "maravillas";
        item3[2] = "loma azul";
        item3[3] = "las canteras";
    }

    public String[] getItem(){return item;}

    public String[] itemBuscar(int id){
        if(id == 1){
            return item1;
        }else if(id == 2){
            return item2;
        }else if(id == 3){
            return item3;
        }
        return item0;
    }
}
