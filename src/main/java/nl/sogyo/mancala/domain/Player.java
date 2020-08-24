package nl.sogyo.mancala.domain;

import org.junit.Assert;

class Player {
    private boolean myTurn;
    private Player opponent;
    private String name;

    public Player getOpponent(){
        return opponent;
    }

    public Player(boolean myTurn, String name){

        this.myTurn = myTurn;
        this.name = name;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public String toString() {return name;}

    public void makeOpponents(Player player2){
        if(myTurn != player2.myTurn){
            player2.opponent = this;
            this.opponent = player2;
        } else {
            System.out.println("Error: two players in same turn state");
        }
    }

    public void flipOpponent(){
        opponent.flipSelf();
    }

    public void flipSelf() {
        myTurn = !myTurn;
    }
}