package checkers.Move;

import java.util.ArrayList;
import java.util.List;

public abstract class MoveParser {
    public static List<Integer> parseMove (String[] moveString, Integer n) {
        List<Integer> parMove = new ArrayList<>();
        for(int i = n; i < moveString.length; i++) {
            try {
                parMove.add(Integer.parseInt(moveString[i]));
            } 
            catch (Exception e) {};
        }
        return parMove;
    }
}
