package GUI.Supplier;

import DBconnection.Account;
import DBconnection.TGoods;

import java.util.ArrayList;

public class performSearch {
    private ArrayList<TGoods> supplier;
    private String keyword;

    public performSearch(ArrayList<TGoods> supply, String keyword) {
        this.supplier = supply;
        this.keyword = keyword;
    }

    public ArrayList<TGoods> search() {
        ArrayList<TGoods> results = new ArrayList<>();
        for (TGoods supp : supplier) {

            System.out.println(supp.getName().contains(keyword));
            System.out.println(keyword);
            if (supp.getName().contains(keyword) ||
                    supp.getNumber().contains(keyword) ||
                    supp.getSto().contains(keyword) ||
                    String.valueOf(supp.getNum()).contains(keyword) ||
                    String.valueOf(supp.getPrice()).contains(keyword) ) {
                results.add(supp);
            }
        }
        return results;
    }
}