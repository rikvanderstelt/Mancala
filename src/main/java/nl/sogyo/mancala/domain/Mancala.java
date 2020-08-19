package nl.sogyo.mancala.domain;

import sun.jvm.hotspot.utilities.Assert;

abstract class Container{
    private int numberOfBeads;
    private Container nextContainer;
    private Player owner;

    public Container(){
        numberOfBeads = 0;
    }

    public Container(Container nextContainer, Player owner){
        numberOfBeads = 0;
        this.nextContainer = nextContainer;
        this.owner = owner;
    }

    public void addBead(){
        numberOfBeads++ ;
    }

    public void addBead(int n){
        numberOfBeads += n;
    }

    public int getNumberOfBeads(){
        return numberOfBeads;
    }

    public Container getNextContainer() {
        return nextContainer;
    }

    public boolean isOwnersTurn() {
        return owner.isMyTurn();
    }
}

class Pit extends Container {
    public Pit(){
        super();
        this.addBead(4);
    }
    public Pit(Container nextContainer,Player owner){
        super(nextContainer,owner);
        this.addBead(4);
    }
}

class Kalaha extends Container{

}


class Player {
    private boolean myTurn;
    private Player opponent;

    public Player(boolean myTurn){
        this.myTurn = myTurn;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

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