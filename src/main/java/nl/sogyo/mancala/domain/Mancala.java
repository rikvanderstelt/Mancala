package nl.sogyo.mancala.domain;

import java.util.ArrayList;

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

    public static ArrayList<Container> buildBoard() {
        ArrayList<Container> output = new ArrayList<Container>(14);
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        Kalaha kalaha2 = new Kalaha();
        Pit pit0 = new Pit();
        Pit pit1 = new Pit();
        output.add(new Pit());
        return output;
    }
}

class Pit extends Container {
    public Pit(){
        super();
        int i = 0;
        this.addBead(4);
        this.setNextContainer(new Pit(i));
        this.linkChain();
    }

    public Pit(int i){
        super();
        if (i<14) {
            this.addBead(i);
            this.setNextContainer(new Pit(i + 1));
        }
    }
    public Pit(Container nextContainer,Player owner){
        super(nextContainer,owner);
        this.addBead(4);
    }

    public void linkChain(){
        // Links a chain of containers that starts with the container it is called upon
        Container nextContainer = this.getNextContainer();

        for(int i=0; i<13; i++){
            nextContainer = nextContainer.getNextContainer();
        }
        nextContainer.setNextContainer(this);
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