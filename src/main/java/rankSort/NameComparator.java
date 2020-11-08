package rankSort;

import java.util.Comparator;

public class NameComparator implements Comparator<Users> {
    @Override
    public int compare(Users o1, Users o2) {
        return o1.name.compareTo(o2.name);
    }
}
