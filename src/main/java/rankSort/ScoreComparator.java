package rankSort;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Users> {
    @Override
    public int compare(Users o1, Users o2) {
        return o2.score-o1.score;
    }
}
