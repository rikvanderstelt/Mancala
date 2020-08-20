package nl.sogyo.mancala.domain;

import java.util.ArrayList;

abstract class Container{
    private int numberOfBeads;
    private Container nextContainer;
    protected Player owner;

    public Container(){
        numberOfBeads = 0;
    }

    public Container(Container nextContainer, Player owner){
        numberOfBeads = 0;
        this.nextContainer = nextContainer;
        this.owner = new Player(true);
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

    public Container getNextContainer(int numberOfSteps){
        Container output = this.getNextContainer();
        for (int j=0; j<(numberOfSteps -1); j++) {
            output = output.getNextContainer();
        }
        return output;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isOwnersTurn() {
        return owner.isMyTurn();
    }

    public void setNextContainer(Container container){
        this.nextContainer = container;
    }

    public int emptyPit(){
        int output = numberOfBeads;
        numberOfBeads = 0;
        return output;
    }
}

class Pit extends Container {
    public Pit(){
        super();
        int i = 0;
        this.addBead(4);
        owner = new Player(true);
        this.setNextContainer(new Pit(i+1,owner));
        this.linkChain();
    }

    public Pit(int i, Player owner){
        super();
        this.owner = owner;
        this.addBead(4);
        if (i<5) {
            this.setNextContainer(new Pit(i + 1,owner));
        } else {
            this.setNextContainer(new Kalaha(owner));
        }
    }
    public Pit(Container nextContainer,Player owner){
        super(nextContainer,owner);
        this.addBead(4);
    }

    public void linkChain(){
        // Links a chain of 14 containers that starts with the container it is called upon
        Container nextContainer = this.getNextContainer();

        for(int i=0; i<12; i++){
            nextContainer = nextContainer.getNextContainer();
        }
        nextContainer.setNextContainer(this);
    }
}

class Kalaha extends Container{
    public Kalaha(Player owner){
        super();
        this.owner = owner;
        if(this.isOwnersTurn()){
            Player player2 = new Player(false);
            owner.makeOpponents(player2);
            this.setNextContainer(new Pit(0,player2));
        }
    }
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