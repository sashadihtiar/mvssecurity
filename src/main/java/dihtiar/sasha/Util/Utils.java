package dihtiar.sasha.Util;

import dihtiar.sasha.model.Users;

public class Utils {
    public static int check(Users us) {
        int deep = 0;
        for (Users user : us.getChildes()) {
            deep = Integer.max(deep, check(user));
        }
        return deep + 1;
    }
}
