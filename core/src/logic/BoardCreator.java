package logic;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

import logic.effects.GoToJail;

/**
 * Created by up201404493 on 17-05-2016.
 */
public class BoardCreator {
    public static Board createBoard(ArrayList<Player> players){
        //Deck community = createCommunityDeck();
        //Deck chance = createChanceDeck();
        Space[] spaces = createSpaces();
        return new Board(players, spaces);
    }

    private static Space[] createSpaces(){
        Space[] spaces = new Space[40];
        ArrayList<Group> groups = new ArrayList<Group>();
        Scanner s;

        try {
            s = new Scanner(new BufferedReader(new FileReader("spaces.txt"))).useDelimiter(";");

            for (int i = 0; i < 10 && s.hasNextLine();i++) {
                String name = s.next();
                int houseValue = s.nextInt();

                Group g = new Group(name, houseValue);
                groups.add(g);
            }

            s.skip("\n");

            int i = 0;

            while (s.hasNextLine()) {
                String type = s.next();
                String name = s.next();

                if (i < 40)
                    if (type.equals("GoToJailSpace")) {
                        GoToJailSpace gtj = new GoToJailSpace(name);
                        spaces[i] = gtj;
                    } else {
                        int value = s.nextInt();

                        if (type.equals("TransactionSpace")) {
                            TransactionSpace t = new TransactionSpace(name, value);
                            spaces[i] = t;

                        } else {
                        String group = s.next();
                        int i_group = -1;

                        for (int index = 0; index < groups.size(); index++) {
                            if ((groups.get(index).getName()).equals(group)) {
                                i_group = index;
                            }
                        }

                        if (i_group != -1) {

                            if (type.equals("Stations")) {
                                Stations st = new Stations(name, groups.get(i_group), value);
                                spaces[i] = st;
                            } else if (type.equals("Service")){
                                Service ser = new Service(name, groups.get(i_group), value);
                                spaces[i] = ser;
                            } //else if (type.equals())
                            // else too many spaces
                        } // else non-existent group!
                    }

                    }
                i++;
            }

        }
        catch (FileNotFoundException e) {

        }
        finally{

        }

        return spaces;
    }
}
