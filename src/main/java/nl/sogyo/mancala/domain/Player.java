package nl.sogyo.mancala.domain;

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
        this.opponent = new Player(this,name);
    }

    public Player(Player opponent, String name){
        this.opponent = opponent;
        this.myTurn = !this.opponent.isMyTurn();
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

    public void switchTurn(){
        this.flipSelf();
        this.flipOpponent();
    }
    public void flipOpponent(){
        opponent.flipSelf();
    }

    public void flipSelf() {
        myTurn = !myTurn;
    }
}