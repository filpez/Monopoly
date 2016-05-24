package logic;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

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



        }
        catch (FileNotFoundException e) {
        }
        finally{

        }


    }
}
