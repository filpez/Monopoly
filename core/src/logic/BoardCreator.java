package logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * Creates a board.
 * Initializes all the spaces and decks.
 */

public class BoardCreator {
    private static Space[] spaces;

    /**
     * Creates a new board
     * @param players - players that will participate on the game
     * @return new board
     */
    public static Board createBoard(ArrayList<Player> players){
        spaces = createSpaces();
        Deck community = createCommunityDeck();
        Deck chance = createChanceDeck();
        return new Board(players, spaces, community, chance);

    }

    /**
     * Converts an ArrayList in an Array
     * @param integers - ArrayList of integers
     * @return - Array of int
     */
    public static int[] convertIntegers(ArrayList<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

    /**
     * Initializes the spaces of the board.
     * Reads the file spaces.txt that contains info regarding the groups and spaces.
     *
     * @return array of spaces
     */
    private static Space[] createSpaces() {
        Space[] spaces = new Space[40];
        ArrayList<Group> groups = new ArrayList<Group>();
        BufferedReader tmp_buffer;
        Scanner s = null;

        FileHandle file = Gdx.files.internal("data/spaces.txt");

        if (file.exists()) {
            try {
                tmp_buffer = new BufferedReader(new InputStreamReader(file.read()), 2048);
                s = new Scanner(tmp_buffer).useDelimiter(";");

                for (int i = 0; i < 10 && s.hasNextLine(); i++) {
                    String ignore = s.next();
                    String name = s.next();
                    int houseValue = s.nextInt();

                    Group g = new Group(name, houseValue);
                    groups.add(g);
                }

                int i = 0;

                while (s.hasNext()) {
                    String ignore = s.next();
                    String type = s.next();
                    String name = s.next();

                    if (i < 40) {
                        if (type.equals("GoToJail")) {
                            spaces[i] = new GoToJailSpace(name);
                        } else if (type.equals("DrawChance")) {
                            spaces[i] = new DrawChanceSpace(name);
                        } else if (type.equals("DrawCommunity")) {
                            spaces[i] = new DrawCommunitySpace(name);
                        } else {
                            int value = s.nextInt();

                            if (type.equals("Transaction")) {
                                TransactionSpace transaction = new TransactionSpace(name, value);
                                spaces[i] = transaction;
                            } else {
                                String group = s.next();
                                int i_group = -1;

                                for(int index = 0; index < groups.size(); index++) {
                                    if ((groups.get(index).getName()).equals(group)) {
                                        i_group = index;
                                    }
                                }

                                if (i_group != -1){
                                    if (type.equals("Stations"))
                                        spaces[i] = new Stations(name, groups.get(i_group),value);
                                    else if (type.equals("Service"))
                                        spaces[i] = new Service(name, groups.get(i_group),value);
                                    else {
                                        ArrayList<Integer> array = new ArrayList<Integer>();
                                        String values = s.next();

                                        Scanner scanner = new Scanner(values).useDelimiter(" ");
                                        while(scanner.hasNextInt()){
                                            int tmp = scanner.nextInt();
                                            array.add(tmp);
                                        }
                                        int[] rents = convertIntegers(array);

                                        spaces[i] = new BuildingLot(name, groups.get(i_group), value, rents);
                                    }
                                } else {
                                    System.out.println("Group not found!\n");
                                    return null;
                                }
                            }
                        }
                    }
                    i++;
                }
            }
            finally {
                if (s != null) {
                    s.close();
                }
            }
        } else{
            System.out.println("File not found\n");
            return null;
        }

        return spaces;
    }

    /**
     * Initializes the chance deck.
     * Reads the file chance.txt.
     *
     * @return deck of chance cards
     */
    private static Deck createChanceDeck(){
        Deck chance;
        BufferedReader tmp_buffer;
        Scanner s = null;
        ArrayList<Card> cards = new ArrayList<Card>();

        FileHandle file = Gdx.files.internal("data/chance.txt");

        if (file.exists()) {
            try {
                tmp_buffer = new BufferedReader(new InputStreamReader(file.read()), 2048);
                s = new Scanner(tmp_buffer).useDelimiter(";");

                while (s.hasNext()){
                    String ignore;
                    String effect;
                    String text;
                    String value;

                    ignore = s.next();
                    effect = s.next();
                    text = s.next();
                    value = s.next();

                    if(effect.equals("GoTo")){
                        int index = -1;

                        for(int i = 0; i < spaces.length; i++) {
                            String sp = spaces[i].getName();
                            if (sp.equals(value))
                                index = i;
                        }

                        if (index != -1){
                            Card goTo = new Card(text,effect,index);
                            cards.add(goTo);
                        } else {
                            System.out.println("Space not found!\n");
                            return null;
                        }
                    } else if(effect.equals("GoToJail")){
                        int tmp = Integer.parseInt(value);
                        Card jail = new Card(text,effect,tmp);
                        cards.add(jail);
                    } else if(effect.equals("Move")){
                        int tmp = Integer.parseInt(value);
                        Card move = new Card(text, effect, tmp);
                        cards.add(move);
                    } else if(effect.equals("PayTax")){
                        int tmp = Integer.parseInt(value);
                        Card pay = new Card(text, effect, tmp);
                        cards.add(pay);
                    }
                }


            } finally {
                if (s != null) {
                    s.close();
                }
            }
        } else{
            System.out.println("File not found!\n");
            return null;
        }

        chance = new Deck(cards);

        return chance;
    }

    /**
     * Initializes the community deck.
     * Reads the file community.txt.
     *
     * @return deck of community cards
     */
    private static Deck createCommunityDeck(){
        Deck community;
        BufferedReader tmp_buffer;
        Scanner s = null;
        ArrayList<Card> cards = new ArrayList<Card>();

        FileHandle file = Gdx.files.internal("data/community.txt");

        if (file.exists()) {
            try {
                tmp_buffer = new BufferedReader(new InputStreamReader(file.read()), 2048);
                s = new Scanner(tmp_buffer).useDelimiter(";");

                while (s.hasNext()) {
                    String ignore;
                    String effect;
                    String text;
                    String value;

                    ignore = s.next();
                    effect = s.next();
                    text = s.next();
                    value = s.next();

                    if(effect.equals("GoTo")){
                        int index = -1;

                        for(int i = 0; i < spaces.length; i++) {
                            String sp = spaces[i].getName();
                            if (sp.equals(value))
                                index = i;
                        }

                        if (index != -1){
                            Card goTo = new Card(text,effect,index);
                            cards.add(goTo);
                        } else {
                            System.out.println("Space not found!\n");
                            return null;
                        }
                    } else if(effect.equals("GoToJail")){
                        int tmp = Integer.parseInt(value);
                        Card jail = new Card(text,effect,tmp);
                        cards.add(jail);
                    } else if(effect.equals("PayTax")){
                        int tmp = Integer.parseInt(value);
                        Card pay = new Card(text, effect, tmp);
                        cards.add(pay);
                    }
                }
            } finally {
                if (s != null) {
                    s.close();
                }
            }
        } else{
            System.out.println("File not found!\n");
            return null;
        }

        community = new Deck(cards);
        return community;
    }

}


