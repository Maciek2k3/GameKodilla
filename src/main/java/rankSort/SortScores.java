package rankSort;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SortScores {

    public SortScores() throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader("rank.txt"));
        ArrayList<Users> usersScoreList=new ArrayList<Users>();
        String currentLine=reader.readLine();
        while (currentLine!=null) {
            String[] userDetail = currentLine.split(">");
                String name = userDetail[0];
                int score = Integer.valueOf(userDetail[1]);
                usersScoreList.add(new Users(name, score));
                currentLine = reader.readLine();

        }
        Collections.sort(usersScoreList,new ScoreComparator());
        BufferedWriter writer=new BufferedWriter(new FileWriter("rankSort.txt"));
        for (Users users:usersScoreList){
            writer.write(users.name);
            writer.write(">"+users.score);
            writer.newLine();
        }
        reader.close();
        writer.close();

    }
}
