package GUI.Person;

import DBconnection.Account;
import DBconnection.TGoods;

import java.util.ArrayList;

public class PerformSearch {
    private ArrayList<Account> accounts;
    private String keyword;

    public PerformSearch(ArrayList<Account> accounts, String keyword) {
        this.accounts = accounts;
        this.keyword = keyword;
    }

    public ArrayList<Account> search() {
        ArrayList<Account> results = new ArrayList<>();
        System.out.println(keyword);
        for (Account account : accounts) {
            if (account.getId().contains(keyword) ||
                    account.getName().contains(keyword) ||
                    String.valueOf(account.getAge()).contains(keyword) ||
                    account.getRole().contains(keyword) ||
                    account.getPhoneNum().contains(keyword) ||
                    account.getEmail().contains(keyword)) {
                results.add(account);
            }
        }
        return results;
    }
}