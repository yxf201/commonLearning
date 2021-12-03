package learning.leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 488. 祖玛游戏
 * https://leetcode-cn.com/problems/zuma-game/
 */
public class ZumaGame {
    public static void main(String[] args) {
        //int res = new ZumaGame().findMinStep("WRRBBW","RB");
        int res = new ZumaGame().findMinStep("RRWWRRBBRR","WB");
        System.out.println("result is:" + res);
    }

    public int findMinStep(String board, String hand) {
        LinkedList<Pair> list = new LinkedList<>();
        list.add(new Pair(board, hand));

        Set<Pair> set = new HashSet<>();
        set.add(list.get(0));

        int ans = 0;
        W:while( true ){
            int size = list.size();
            ans++;

            if( 0 <= ans && ans < 10 ){
                System.out.println(ans);
                System.out.println(list);
            }

            for( int i = 0; i < size; i++ ){
                Pair p = list.removeFirst();

                if( p.hand.length() == 0 ){
                    break W;
                }

                for( int j = 0; j < p.hand.length(); j++ ){
                    for( int k = 0; k <= p.board.length(); k++ ){
                        Pair newPair = insert(p.hand, j, p.board, k);
                        if( newPair.board.length() == 0 ){
                            return ans;
                        }
                        if( set.add(newPair) ){
                            list.addLast(newPair);
                        }
                    }
                }
            }
        }
        return -1;
    }

    private Pair insert(String hand, int j, String board, int k) {
        String newHand = hand.substring(0, j) + hand.substring(j + 1);
        StringBuilder newBoard = new StringBuilder()
                .append(board.substring(0, k))
                .append(hand.charAt(j))
                .append(board.substring(k));
        String newBoardStr = removeSameColor(newBoard);
        return new Pair(newBoardStr, newHand);
    }

    @Test
    public void t1(){
        System.out.println(removeSameColor(new StringBuilder("WRRRBBW")));
    }

    private String removeSameColor(StringBuilder newBoard) {
        while (true){
            int start = -1;
            int count = 1;
            for( int i = 1; i < newBoard.length(); i++ ){
                if( newBoard.charAt(i) == newBoard.charAt(i-1) ){
                    if( start == -1 ){
                        start = i-1;
                    }
                    count++;
                }else{
                    if( count >= 3 ){
                        newBoard.replace(start, i, "");
                        count = -1;
                        break;
                    }else{
                        start = -1;
                        count = 1;
                    }
                }
            }
            if( count >= 3 ){
                newBoard.replace(start, newBoard.length(), "");
            }else if( count != -1 ){
                break;
            }
        }
        return newBoard.toString();
    }

    private class Pair {
        String board;
        String hand;

        Pair(String board, String hand) {
            this.board = board;
            this.hand = hand;
        }

        public boolean equals(Object o) {
            if (o instanceof Pair p) {
                return this.board.equals(p.board) && this.hand.equals(p.hand);
            }
            return false;
        }

        public int hashCode() {
            return this.board.hashCode() ^ this.hand.hashCode();
        }

        public String toString(){
            return "(" + this.board + "," + this.hand + ")";
        }
    }
}
