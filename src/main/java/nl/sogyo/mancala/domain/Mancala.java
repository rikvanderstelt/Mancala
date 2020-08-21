package nl.sogyo.mancala.domain;

abstract class Container{
    private int numberOfBeads;
    private Container nextContainer;
    protected Player owner;
    protected Container opposite;

    public Container(){
        numberOfBeads = 0;
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
    public Container getOpposite() {
        return opposite;
    }

    public abstract void emptyOpposite();

    public abstract void passBeads(int beadsPassed);

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
        this.setOpposites();
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

    public void linkChain(){
        // Links a chain of 14 containers that starts with the container it is called upon.
        Container nextContainer = this.getNextContainer();

        for(int i=0; i<12; i++){
            nextContainer = nextContainer.getNextContainer();
        }
        nextContainer.setNextContainer(this);
    }

    public void setOpposites(){
        this.opposite = this.getNextContainer(12);
        for (int i = 1; i<13; i++){
            this.getNextContainer(i).opposite =this.getNextContainer(12-i);
        }
    }

    public void emptyOpposite(){
        int beadsTransfered = this.opposite.emptyPit();
        this.opponentKalaha().addBead(beadsTransfered);
    }

    public Kalaha opponentKalaha(){
        int j = 0;

        for (int i=8;i<14;i++){               // The opponents kalaha is between 8 and 14 steps away from a pit
            if(this.getNextContainer(i) instanceof Kalaha){
                j=i;
            }
        }
        return (Kalaha) this.getNextContainer(j);  //
    }

    public void passBeads(int beadsPassed){
        if (beadsPassed > 1){
            this.addBead();
            this.getNextContainer().passBeads(beadsPassed-1);
        } else{
            this.addBead();
        }
    }

    public void playPit(){
        int beadsPassed = this.emptyPit();
        this.getNextContainer().passBeads(beadsPassed);
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

    public void emptyOpposite(){}

    public void passBeads(int beadsPassed){

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