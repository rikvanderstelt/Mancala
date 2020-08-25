package nl.sogyo.mancala.domain;

class Player {
    private boolean myTurn;
    private final Player opponent;
    private final String name;

    public Player getOpponent(){
        return opponent;
    }

    public Player(){
        this.myTurn = true;
        this.name = "Player 1";
        this.opponent = new Player(this);
    }

    public Player(Player opponent){
        this.opponent = opponent;
        this.name = "Player 2";
        this.myTurn = !this.opponent.isMyTurn();
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public String toString() {return name;}

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