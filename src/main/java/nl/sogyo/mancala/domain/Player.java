package nl.sogyo.mancala.domain;

class Player {
    private boolean myTurn;
    private final Player opponent;
    private String name;

    public Player getOpponent(){
        return opponent;
    }

    public Player(String name, String opponentName){
        this.myTurn = true;
        this.name = name;
        this.opponent = new Player(this, opponentName);
    }

    public Player(Player opponent, String name){
        this.opponent = opponent;
        this.name = name;
        this.myTurn = !this.opponent.isMyTurn();
    }

    public Player(){
        this.myTurn = true;
        this.name = "Player 1";
        this.opponent = new Player(this, "Player 2");
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public String toString() {return name;}

    public void switchTurn(){
        this.flipSelf();
        this.opponent.flipSelf();
    }

    public void flipSelf() {
        myTurn = !myTurn;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName){
        this.name = newName;
    }
}